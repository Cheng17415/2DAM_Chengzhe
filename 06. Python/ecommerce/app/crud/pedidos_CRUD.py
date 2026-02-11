from rich.console import Console
from rich.table import Table
from app.db.database import SessionLocal
from app.models.pedido import Pedido

def visualizar_mis_pedidos(usuario_id: int):
    console = Console()
    session = SessionLocal()
    try:
        pedidos = (
            session.query(Pedido)
            .filter_by(usuario_id=usuario_id)
            .order_by(Pedido.fecha.desc())
            .all()
        )

        if not pedidos:
            console.print("[yellow]No tienes pedidos registrados[/yellow]")
            return

        tabla = Table(title="Mis pedidos")
        tabla.add_column("ID", justify="right")
        tabla.add_column("Fecha")
        tabla.add_column("Estado")
        tabla.add_column("Total", justify="right")

        for pedido in pedidos:
            fecha = (
                pedido.fecha.strftime("%Y-%m-%d %H:%M")
                if getattr(pedido, "fecha", None)
                else "-"
            )
            tabla.add_row(
                str(pedido.pedido_id),
                fecha,
                str(pedido.estado),
                f"{float(pedido.total):.2f}",
            )

        console.print(tabla)
    finally:
        session.close()