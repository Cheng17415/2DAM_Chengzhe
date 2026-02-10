from rich.console import Console
from app.db.database import SessionLocal
from app.models.carrito import Carrito, EstadoCarrito
from app.models.producto import Producto
from app.models.producto_carrito import Producto_carrito
from app.service.producto_service import obtener_producto_id

def obtener_carrito_por_usuarioID(usuario_id: int)-> Carrito | None:
    console = Console()
    session = SessionLocal()
    try:
        return (
            session.query(Carrito)
            .filter_by(usuario_id = usuario_id, estado = EstadoCarrito.ACTIVO)
            .first()
        )
    except Exception as e:
        console.print(f"[red]Error al obtener carrito: {e}[/red]")
    finally:
        session.close()

def obtener_productos_por_usuaioID(usuario_id: int):
    console = Console()
    carrito = obtener_carrito_por_usuarioID(usuario_id)
    if not carrito:
        console.print("[red]No tienes productos en el carrito[/red]")
        return None
    session = SessionLocal()
    try:
        lista_prod_carr =  session.query(Producto_carrito).filter_by(carrito_id = carrito.carrito_id).all()
        lista = []
        for producto_carrito in lista_prod_carr:
            producto = obtener_producto_id(producto_carrito.producto_id)
            
            if not producto:
                console.print(f"[red]Producto con id {producto_carrito.producto_id} no existe[/red]")
                continue
            tupla = (producto, producto_carrito.cantidad)
            lista.append(tupla)
        return lista
            
    finally:
        session.close()