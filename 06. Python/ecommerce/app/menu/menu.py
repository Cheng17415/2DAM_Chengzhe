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
        uid = int(input("ID del usuario a editar: "))
        if obtener_usu_id(uid) is None:
          print(f"No existe usuario con ID {uid}")
          continue
        nombre = input("Nuevo nombre (enter para no cambiar): ")
        apellido = input("Nuevo apellido (enter para no cambiar): ")
        direccion = input("Nueva direccion (enter para no cambiar): ")
        codigo_postal = input("Nuevo codigo postal (enter para no cambiar): ")
        telefono = input("Nuevo numero de telefono (enter para no cambiar): ")
        email = input("Nuevo email (enter para no cambiar): ")
        usuario_CRUD.editar_usuario(uid, nombre, apellido,
                                    direccion, codigo_postal,
                                    telefono, email)
      #case 4:
        #usuario_CRUD.eliminar_usuario
      case 0:
        console.print("[bold cyan]Volviendo al menu anterior[/bold cyan]")
        break