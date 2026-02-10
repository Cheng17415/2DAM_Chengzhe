from rich.console import Console
from app.db.database import SessionLocal
from app.models.carrito import Carrito, EstadoCarrito
from app.models.producto import Producto
from app.models.producto_carrito import ProductoCarrito

def obtener_carrito_por_usuarioID(usuario_id: int)-> Carrito | None:
    console = Console()
    try:
        session = SessionLocal()
        return session.query(Carrito).filter_by(usuario_id = usuario_id, estado = EstadoCarrito.ACTIVO).first()
    except Exception as e:
        console.print(f"[red]Error al obtener carrito: {e}[/red]")
    finally:
        session.close()