from datetime import datetime

from rich.console import Console

from app.db.database import SessionLocal
from app.models.carrito import Carrito
from app.models.producto import Producto
from app.models.producto_carrito import ProductoCarrito
import app.service.carrito_service as carrito_service


def pedir_cantidad(mensaje: str) -> int:
    while True:
        valor = input(mensaje).strip()
        if not valor.isdigit():
            print("Cantidad no valida.")
            continue
        cantidad = int(valor)
        if cantidad <= 0:
            print("La cantidad debe ser mayor que 0.")
            continue
        return cantidad


def anadir_producto_al_carrito(usuario_id: int):
    console = Console()
    try:
        producto_id = int(input("ID del producto: "))
    except Exception:
        console.print("[red]ID no valido[/red]")
        return False

    cantidad = pedir_cantidad("Cantidad: ")

    session = SessionLocal()
    try:
        producto = session.query(Producto).filter_by(producto_id=producto_id).first()
        if not producto:
            console.print("[red]Producto no encontrado[/red]")
            return False
        if not producto.activo:
            console.print("[red]El producto no esta activo[/red]")
            return False
        if producto.stock < cantidad:  # type: ignore
            console.print("[red]No hay stock suficiente[/red]")
            return False

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
            session.query(ProductoCarrito)
            .filter_by(carrito_id=carrito.carrito_id, producto_id=producto_id)
            .first()
        )
        if linea:
            linea.cantidad += cantidad  # type: ignore
        else:
            session.add(
                ProductoCarrito(
                    carrito_id=carrito.carrito_id,
                    producto_id=producto_id,
                    cantidad=cantidad,
                )
            )

        session.commit()
        console.print("[green]Producto añadido al carrito[/green]")
        return True
    except Exception as e:
        session.rollback()
        console.print(f"[red]Error al añadir al carrito: {e}[/red]")
        return False
    finally:
        session.close()

def listar_productos_carrito(usuario_id: int):
    console = Console()
    carrito = carrito_service.obtener_carrito_por_usuarioID(usuario_id)

    if not carrito:
        console.print("[red]No tienes un carrito activo")
        return False
    #TODO Terminar de listar los productos del carrito



def eliminar_carrito(usuario_id: int):
    ...