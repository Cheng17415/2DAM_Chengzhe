import app.crud.usuario_CRUD as usuario_CRUD
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
      #case 3:
        #usuario_CRUD.editar_usuario
      #case 4:
        #usuario_CRUD.eliminar_usuario
      case 0:
        console.print("[bold cyan]Volviendo al menu anterior[/bold cyan]")
        break