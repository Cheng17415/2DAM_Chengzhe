from rich.console import Console
from rich.table import Table
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
    ok, mensaje = carrito_service.anadir_producto_a_carrito(usuario_id, producto_id, cantidad)
    color = "green" if ok else "red"
    console.print(f"[{color}]{mensaje}[/{color}]")
    return ok


def listar_productos_carrito(usuario_id: int):
    console = Console()
    carrito = carrito_service.obtener_carrito_por_usuarioID(usuario_id)

    if not carrito:
        console.print("[red]No tienes un carrito activo")
        return False

    productos = carrito_service.obtener_productos_por_usuarioID(usuario_id)
    if not productos:
        console.print("[yellow]No hay productos en el carrito[/yellow]")
        return False

    tabla = Table(title="Productos en tu carrito")
    tabla.add_column("ID", justify="right")
    tabla.add_column("Nombre")
    tabla.add_column("Cantidad", justify="right")
    tabla.add_column("Precio", justify="right")
    tabla.add_column("Subtotal", justify="right")

    total = 0.0
    for producto, cantidad in productos:
        precio = float(producto.precio)  # type: ignore
        subtotal = precio * int(cantidad)
        total += subtotal
        tabla.add_row(
            str(producto.producto_id),
            str(producto.nombre),
            str(cantidad),
            f"{precio:.2f}",
            f"{subtotal:.2f}",
        )

    console.print(tabla)
    console.print(f"[bold]Total: {total:.2f}[/bold]")
    return True


def procesar_compra(usuario_id: int):
    console = Console()

    opciones_envio = carrito_service.obtener_opciones_envio()
    if not opciones_envio:
        console.print("[red]No hay opciones de envio disponibles[/red]")
        return False

    console.print("[bold]Opciones de envio:[/bold]")
    for opcion in opciones_envio:
        console.print(f"{opcion.opciones_id}. {opcion.dias} dias - {opcion.precio}")

    try:
        opcion_envio_id = int(input("Seleccione la opcion de envio: "))
    except ValueError:
        console.print("[red]ID de envio no valido[/red]")
        return False

    ok, mensaje = carrito_service.procesar_compra(usuario_id, opcion_envio_id)
    color = "green" if ok else "red"
    console.print(f"[{color}]{mensaje}[/{color}]")
    return ok