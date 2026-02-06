from datetime import datetime
from decimal import Decimal, InvalidOperation

from rich.console import Console
from rich.table import Table

from app.db.database import SessionLocal
from app.models.producto import Producto
from app.service.producto_service import (
    obtener_productos,
    obtener_productos_activos,
    buscar_productos_nombre,
    obtener_productos_usuario,
    obtener_producto_id,
)
import app.util.utileria as util


def pedir_precio(mensaje: str) -> Decimal:
    while True:
        valor = input(mensaje).strip().replace(",", ".")
        try:
            precio = Decimal(valor)
        except (InvalidOperation, ValueError):
            print("Precio no valido.")
            continue

        if precio < 0:
            print("El precio no puede ser negativo.")
            continue
        return precio


def pedir_stock(mensaje: str) -> int:
    while True:
        valor = input(mensaje).strip()
        if not valor.isdigit():
            print("Stock no valido.")
            continue
        stock = int(valor)
        return stock


def imprimir_productos_admin():
    productos = obtener_productos()
    if not productos:
        print("No existen productos en la BBDD")
        return

    table = Table(title="Productos")
    columnas = ["ID", "Nombre", "Descripcion", "Precio", "Stock", "Activo", "Fecha Creacion", "ID Usuario"]
    for columna in columnas:
        table.add_column(columna)

    for producto in productos:
        table.add_row(*producto.obtener_producto_completo())

    console = Console()
    console.print(table)

def imprimir_productos():
    productos = obtener_productos_activos()
    if not productos:
        print("No existen productos en la BBDD")
        return

    table = Table(title="Productos")
    columnas = ["ID", "Nombre", "Descripcion", "Precio", "Stock", "Distribuidor"]
    for columna in columnas:
        table.add_column(columna)

    for producto in productos:
        table.add_row(*producto.obtener_producto())

    console = Console()
    console.print(table)

def buscar_productos_por_nombre():
    nombre = util.pedir_no_vacio("Introduzca el nombre del producto: ")
    productos = buscar_productos_nombre(nombre)
    if not productos:
        print("No hay productos que coincidan con la busqueda")
        return

    table = Table(title="Productos")
    columnas = ["ID", "Nombre", "Descripcion", "Precio", "Stock", "Distribuidor"]
    for columna in columnas:
        table.add_column(columna)

    for producto in productos:
        table.add_row(*producto.obtener_producto())

    console = Console()
    console.print(table)

def crear_producto(usuario_id: int | None = None):
    nombre = util.pedir_no_vacio("Introduzca el nombre: ")
    descripcion = input("Introduzca la descripcion (Opcional): ")
    precio = pedir_precio("Introduzca el precio: ")
    stock = pedir_stock("Introduzca el stock: ")

    producto = Producto(
        nombre=nombre,
        descripcion=descripcion,
        precio=precio,
        stock=stock,
        activo=True,
        fecha_creacion=datetime.now(),
        usuario_id=usuario_id,
    )

    session = SessionLocal()
    console = Console()
    try:
        session.add(producto)
        session.commit()
        console.print("[green]Producto creado correctamente[/green]")
    except Exception as e:
        session.rollback()
        console.print(f"[red]Error al crear producto: {e}[/red]")
    finally:
        session.close()


def editar_producto(
    producto_id: int,
    nombre: str | None = None,
    descripcion: str | None = None,
    precio: str | float | Decimal | None = None,
    stock: str | int | None = None,
):
    nombre = None if nombre is None or str(nombre).strip() == "" else nombre
    descripcion = None if descripcion is None or str(descripcion).strip() == "" else descripcion

    precio_valor = None
    if precio is not None and str(precio).strip() != "":
        try:
            precio_valor = Decimal(str(precio).replace(",", "."))
        except (InvalidOperation, ValueError):
            print("Precio no valido.")
            return False
        if precio_valor < 0:
            print("El precio no puede ser negativo.")
            return False

    stock_valor = None
    if stock is not None and str(stock).strip() != "":
        if not str(stock).isdigit():
            print("Stock no valido.")
            return False
        stock_valor = int(stock)

    session = SessionLocal()
    console = Console()
    try:
        producto = session.query(Producto).filter_by(producto_id=producto_id).first()

        if not producto:
            console.print("[red]Producto no encontrado[/red]")
            return False

        if nombre is not None:
            producto.nombre = nombre  # type: ignore
        if descripcion is not None:
            producto.descripcion = descripcion # type: ignore
        if precio_valor is not None:
            producto.precio = precio_valor # type: ignore
        if stock_valor is not None:
            producto.stock = stock_valor # type: ignore

        session.commit()
        console.print("[green]Producto actualizado correctamente[/green]")
        return True

    except Exception as e:
        session.rollback()
        console.print(f"[red]Error al editar producto: {e}[/red]")
        return False
    finally:
        session.close()


def desactivar_producto(producto_id: int):
    session = SessionLocal()
    console = Console()
    try:
        producto = session.query(Producto).filter_by(producto_id=producto_id).first()

        if not producto:
            console.print("[red]Producto no encontrado[/red]")
            return False

        producto.activo = False # type: ignore
        session.commit()

        console.print("[green]Producto desactivado correctamente[/green]")
        return True

    except Exception as e:
        session.rollback()
        console.print(f"[red]Error al desactivar producto: {e}[/red]")
        return False
    finally:
        session.close()

def imprimir_productos_usuario(usuario_id: int):
    productos = obtener_productos_usuario(usuario_id, incluir_inactivos=True)
    if not productos:
        print("No tienes productos registrados")
        return

    table = Table(title="Mis productos")
    columnas = ["ID", "Nombre", "Descripcion", "Precio", "Stock", "Activo", "Fecha Creacion"]
    for columna in columnas:
        table.add_column(columna)

    for producto in productos:
        table.add_row(
            str(producto.producto_id),
            str(producto.nombre),
            str(producto.descripcion or ""),
            str(producto.precio),
            str(producto.stock),
            str(producto.activo),
            str(producto.fecha_creacion),
        )

    console = Console()
    console.print(table)

def editar_producto_propietario(usuario_id: int):
    try:
        producto_id = int(input("ID del producto a editar: "))
    except Exception:
        print("ID no valido.")
        return False

    producto = obtener_producto_id(producto_id)
    if not producto:
        print(f"No existe producto con ID {producto_id}")
        return False
    if producto.usuario_id != usuario_id:
        print("No tienes permiso para editar este producto.")
        return False

    nombre = input(f"Nuevo nombre ({producto.nombre}): ")
    descripcion = input(f"Nueva descripcion ({producto.descripcion or 'Vacio'}): ")
    precio = input(f"Nuevo precio ({producto.precio}): ")
    stock = input(f"Nuevo stock ({producto.stock}): ")
    return editar_producto(producto_id, nombre, descripcion, precio, stock)

def desactivar_producto_propietario(usuario_id: int):
    try:
        producto_id = int(input("ID del producto a desactivar: "))
    except Exception:
        print("ID no valido.")
        return False

    producto = obtener_producto_id(producto_id)
    if not producto:
        print(f"No existe producto con ID {producto_id}")
        return False
    if producto.usuario_id != usuario_id:
        print("No tienes permiso para desactivar este producto.")
        return False

    return desactivar_producto(producto_id)
