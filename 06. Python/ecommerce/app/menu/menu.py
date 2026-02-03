

import app.crud.usuario_CRUD as usuario_CRUD
import app.crud.producto_CRUD as producto_CRUD
from app.service.usuario_service import obtener_usu_id
from app.service.producto_service import obtener_producto_id
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

def menu_principal():
  opciones = ["Menu usuarios", "Menu productos"]
  while True:
    num_opcion = mostrar_menu(opciones, "MENU PRINCIPAL")
    match num_opcion:
      case 1:
        menu_usuario()
      case 2:
        menu_producto()
      case 0:
        console.print("[bold cyan]Saliendo del sistema[/bold cyan]")
        break
    if num_opcion != 0:
      input("Pulse enter para continuar...")

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

def menu_producto():
  opciones = ["Crear producto", "Listar productos", "Editar producto", "Eliminar producto"]
  while True:
    num_opcion = mostrar_menu(opciones, "MENU PRODUCTOS")
    match num_opcion:
      case 1:
        producto_CRUD.crear_producto()
      case 2:
        producto_CRUD.imprimir_productos()
      case 3:
        id = int(input("ID del producto a editar: "))
        producto = obtener_producto_id(id)
        if producto is None:
          print(f"No existe producto con ID {id}")
          continue
        nombre = input(f"Nuevo nombre ({producto.nombre}): ")
        descripcion = input(f"Nueva descripcion ({producto.descripcion or 'Vacio'}): ")
        precio = input(f"Nuevo precio ({producto.precio}): ")
        stock = input(f"Nuevo stock ({producto.stock}): ")
        producto_CRUD.editar_producto(
          id,
          nombre,
          descripcion,
          precio,
          stock
        )
      case 4:
        id = int(input("ID del producto a desactivar: "))
        producto_CRUD.desactivar_producto(id)
      case 0:
        console.print("[bold cyan]Volviendo al menu anterior[/bold cyan]")
        break
    input("Pulse enter para continuar...")

def menu_inicio_sesion():
  opciones = ["Iniciar sesion", "Registrarse", "Entrar como invitado"]
  while True:
    num_opcion = mostrar_menu(opciones, "MENU")
    match num_opcion:
      case 1:
        usuario_CRUD.iniciar_sesion()
      case 2:
        usuario_CRUD.crear_usuario()
      case 3:
        usuario_CRUD.iniciar_sesion_invitado()
      case 0:
        console.print("[bold cyan]Saliendo del sistema[/bold cyan]")
        break
    if num_opcion != 0:
      input("Pulse enter para continuar...")