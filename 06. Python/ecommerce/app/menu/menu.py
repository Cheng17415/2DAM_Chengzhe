import app.crud.usuario_CRUD as usuario_CRUD
from app.service.usuario_service import obtener_usu_id
from rich.console import Console
from rich.panel import Panel
from rich.align import Align

console = Console()

def mostrar_menu(opciones:list, titulo: str = "MENU") -> int:
  menu = ""
  for i, opcion in enumerate(opciones,1):
    menu +=  f"[bold cyan]{i}.[/bold cyan] {opcion}\n"
  
  menu += "[bold cyan]0.[/bold cyan] Salir"

  panel = Panel(
      Align.center(menu),
      title=f"[bold yellow]{titulo}[/bold yellow]",
      border_style="bright_blue",
      width=50
  )

  console.clear()
  console.print(panel)

  opcion = int(console.input("[bold green]Seleccione una opcion: [/bold green]"))
  return opcion

def menu_usuario():
  opciones = ["Crear usuario", "Listar usuarios", "Editar usuario", "Eliminar usuario"]
  while True:
    num_opcion = mostrar_menu(opciones, "MENU USUARIOS")
    match num_opcion:
      case 1:
        usuario_CRUD.crear_usuario()
      case 2:
        usuario_CRUD.imprimir_usuarios()
      case 3:
        id = int(input("ID del usuario a editar: "))
        usuario = obtener_usu_id(id)
        if usuario is None:
          print(f"No existe usuario con ID {id}")
          continue
        nombre = input(f"Nuevo nombre ({usuario.nombre}): ")
        apellido = input(f"Nuevo apellido ({usuario.apellido}): ")
        direccion = input(f"Nueva direccion ({usuario.direccion or 'Vacio'}): ")
        codigo_postal = input(f"Nuevo codigo postal ({usuario.codigo_postal or 'Vacio'}): ")
        telefono = input(f"Nuevo numero de telefono ({usuario.telefono or 'Vacio'}): ")
        email = input(f"Nuevo email ({usuario.email}): ")
        usuario_CRUD.editar_usuario(id, nombre, apellido,
                                    direccion, codigo_postal,
                                    telefono, email)
      case 4:
        id = int(input("ID del usuario a desactivar: "))
        usuario_CRUD.desactivar_usuario(id)
      case 0:
        console.print("[bold cyan]Volviendo al menu anterior[/bold cyan]")
        break
    input("Pulse enter para continuar...")