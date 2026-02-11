from rich.console import Console
from app.db.database import SessionLocal
from app.models.carrito import Carrito, EstadoCarrito
from app.models.envio import OpcionesEnvio
from app.models.pedido import Pedido
from app.models.pedido_detalle import Pedido_detalle
from app.models.producto import Producto
from app.models.producto_carrito import Producto_carrito
from app.service.producto_service import obtener_producto_id
from datetime import datetime
from decimal import Decimal


def obtener_carrito_por_usuarioID(usuario_id: int) -> Carrito | None:
    console = Console()
    session = SessionLocal()
    try:
        return (
            session.query(Carrito)
            .filter_by(usuario_id=usuario_id, estado=EstadoCarrito.ACTIVO)
            .first()
        )
    except Exception as e:
        console.print(f"[red]Error al obtener carrito: {e}[/red]")
    finally:
        session.close()


def obtener_productos_por_usuarioID(usuario_id: int):
    console = Console()
    carrito = obtener_carrito_por_usuarioID(usuario_id)
    if not carrito:
        console.print("[red]No tienes productos en el carrito[/red]")
        return None

    session = SessionLocal()
    try:
        lista_prod_carr = session.query(Producto_carrito).filter_by(carrito_id=carrito.carrito_id).all()
        lista = []
        for producto_carrito in lista_prod_carr:
            producto = obtener_producto_id(producto_carrito.producto_id)
            if not producto:
                console.print(f"[red]Producto con id {producto_carrito.producto_id} no existe[/red]")
                continue
            lista.append((producto, producto_carrito.cantidad))
        return lista
    finally:
        session.close()


def obtener_opciones_envio() -> list[OpcionesEnvio]:
    session = SessionLocal()
    try:
        return session.query(OpcionesEnvio).all()
    finally:
        session.close()


def anadir_producto_a_carrito(usuario_id: int, producto_id: int, cantidad: int) -> tuple[bool, str]:
    session = SessionLocal()
    try:
        producto = session.query(Producto).filter_by(producto_id=producto_id).first()
        if not producto:
            return False, "Producto no encontrado"
        if not producto.activo:
            return False, "El producto no esta activo"
        if int(producto.stock) < cantidad:
            return False, "No hay stock suficiente"

        carrito = (
            session.query(Carrito)
            .filter_by(usuario_id=usuario_id, estado="ACTIVO")
            .first()
        )
        if not carrito:
            carrito = Carrito(estado="ACTIVO", fecha_creacion=datetime.now(), usuario_id=usuario_id)
            session.add(carrito)
            session.flush()

        linea = (
            session.query(Producto_carrito)
            .filter_by(carrito_id=carrito.carrito_id, producto_id=producto_id)
            .first()
        )
        if linea:
            linea.cantidad += cantidad  # type: ignore
        else:
            session.add(
                Producto_carrito(
                    carrito_id=carrito.carrito_id,
                    producto_id=producto_id,
                    cantidad=cantidad,
                )
            )

        session.commit()
        return True, "Producto anadido al carrito"
    except Exception as e:
        session.rollback()
        return False, f"Error al anadir al carrito: {e}"
    finally:
        session.close()


def procesar_compra(usuario_id: int, opcion_envio_id: int) -> tuple[bool, str]:
    session = SessionLocal()
    try:
        carrito = (
            session.query(Carrito)
            .filter_by(usuario_id=usuario_id, estado="ACTIVO")
            .first()
        )
        if not carrito:
            return False, "No tienes un carrito activo"

        lineas_carrito = (
            session.query(Producto_carrito)
            .filter_by(carrito_id=carrito.carrito_id)
            .all()
        )
        if not lineas_carrito:
            return False, "No hay productos en el carrito"

        opcion_envio = (
            session.query(OpcionesEnvio)
            .filter_by(opciones_id=opcion_envio_id)
            .first()
        )
        if not opcion_envio:
            return False, "La opcion de envio no existe"

        productos_ids = [linea.producto_id for linea in lineas_carrito]
        productos = (
            session.query(Producto)
            .filter(Producto.producto_id.in_(productos_ids))
            .all()
        )
        productos_por_id = {producto.producto_id: producto for producto in productos}

        total_productos = Decimal("0")
        detalles = []

        for linea in lineas_carrito:
            producto = productos_por_id.get(linea.producto_id)
            if not producto:
                return False, f"Producto {linea.producto_id} no encontrado"

            cantidad = int(linea.cantidad)
            if int(producto.stock) < cantidad:
                return False, f"Stock insuficiente para {producto.nombre}"

            precio_unitario = Decimal(str(producto.precio))
            total_productos += precio_unitario * cantidad
            detalles.append((producto, cantidad, precio_unitario))

        pedido = Pedido(
            total=total_productos + Decimal(str(opcion_envio.precio)),
            estado="PAGADO",
            fecha=datetime.now(),
            usuario_id=usuario_id,
            opcion_envio_id=opcion_envio.opciones_id,
        )
        session.add(pedido)
        session.flush()

        for producto, cantidad, precio_unitario in detalles:
            session.add(
                Pedido_detalle(
                    pedido_id=pedido.pedido_id,
                    producto_id=producto.producto_id,
                    precio_unitario=precio_unitario,
                    cantidad=cantidad,
                )
            )
            producto.stock -= cantidad  # type: ignore

        carrito.estado = "CERRADO"
        session.commit()
        return True, "Compra procesada correctamente"
    except Exception as e:
        session.rollback()
        return False, f"Error al procesar la compra: {e}"
    finally:
        session.close()
