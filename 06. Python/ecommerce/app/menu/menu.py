import app.crud.usuario_CRUD as usuario_CRUD
import app.crud.producto_CRUD as producto_CRUD
import app.crud.carrito_CRUD as carrito_CRUD
import app.crud.pedidos_CRUD as pedidos_CRUD
from app.service.usuario_service import obtener_usu_id
from app.service.producto_service import obtener_producto_id
import app.service.auth_service as auth_service 
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
        producto_CRUD.imprimir_productos_admin()
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
        usuario = auth_service.iniciar_sesion_usuario()
        if not usuario:
          continue
        
        rol_nombre = usuario.rol.nombre
        menu_func = MENUS_POR_ROL.get(rol_nombre)

        if not menu_func:
          console.print("[red]Rol sin menu asignado[/red]")
          continue

        menu_func(usuario)
          
      case 2:
        usuario_CRUD.crear_usuario()
      case 3:
        usuario = auth_service.iniciar_sesion_invitado()
        if usuario:
          menu_invitado()
      case 0:
        console.print("[bold cyan]Saliendo del sistema[/bold cyan]")
        break
    if num_opcion != 0:
      input("Pulse enter para continuar...")
      
def menu_invitado(usuario=None):
  opciones = ["Listar productos", "Buscar vendedor", "Buscar productos"]
  while True:
    num_opcion = mostrar_menu(opciones, "MENU")
    match num_opcion:
      case 1:
        producto_CRUD.imprimir_productos()
      case 2:
        usuario_CRUD.buscar_por_vendedor()
      case 3:
        producto_CRUD.buscar_productos_por_nombre()
      case 0:
        console.print("[bold cyan]Volviendo al menu anterior[/bold cyan]")
        break
    input("Pulse enter para continuar...")

def menu_principal_usuario(usuario):
    opciones = [
        "Productos",
        "Mi carrito",
        "Mis pedidos",
        "Perfil",
        "Cerrar sesion",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU USUARIO")
        match num_opcion:
            case 1:
                menu_usuario_productos(usuario)
            case 2:
                menu_carrito(usuario)
            case 3:
                pedidos_CRUD.visualizar_mis_pedidos(usuario.usuario_id)
            case 4:
                menu_usuario_perfil(usuario)
            case 5:
              break
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_carrito(usuario):
   opciones = [
        "Listar productos del carrito",
        "Procesar compra"
    ]
   while True:
        num_opcion = mostrar_menu(opciones, "MENU USUARIO")
        match num_opcion:
            case 1:
                carrito_CRUD.listar_productos_carrito(usuario.usuario_id)
            case 2:
                carrito_CRUD.procesar_compra(usuario.usuario_id)
                break
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_vendedor(usuario):
    opciones = [
        "Productos",
        "Mi carrito",
        "Mis pedidos",
        "Mi perfil",
        "Gestion de mis productos",
        "Cerrar sesion",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU VENDEDOR")
        match num_opcion:
            case 1:
                menu_vendedor_productos(usuario)
            case 2:
                menu_carrito(usuario)
            case 3:
                pedidos_CRUD.visualizar_mis_pedidos(usuario.usuario_id)
            case 4:
                menu_vendedor_perfil(usuario)
            case 5:
                menu_vendedor_gestion_productos(usuario)
            case 6:
                break
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_usuario_productos(usuario):
    opciones = [
        "Ver productos",
        "Buscar vendedor",
        "Buscar productos",
        "Anadir producto al carrito",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU USUARIO - PRODUCTOS")
        match num_opcion:
            case 1:
                producto_CRUD.imprimir_productos()
            case 2:
                usuario_CRUD.buscar_por_vendedor()
            case 3:
                producto_CRUD.buscar_productos_por_nombre()
            case 4:
                carrito_CRUD.anadir_producto_al_carrito(usuario.usuario_id)
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_usuario_perfil(usuario):
    opciones = [
        "Modificar datos propios",
        "Desactivar mi cuenta",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU USUARIO - PERFIL")
        match num_opcion:
            case 1:
                usuario_CRUD.editar_datos_usuario(usuario)
            case 2:
                usuario_CRUD.desactivar_usuario(usuario.usuario_id)
                break
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_vendedor_productos(usuario):
    opciones = [
        "Ver productos",
        "Buscar vendedor",
        "Buscar productos",
        "Anadir producto al carrito",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU VENDEDOR - PRODUCTOS")
        match num_opcion:
            case 1:
                producto_CRUD.imprimir_productos()
            case 2:
                usuario_CRUD.buscar_por_vendedor()
            case 3:
                producto_CRUD.buscar_productos_por_nombre()
            case 4:
                carrito_CRUD.anadir_producto_al_carrito(usuario.usuario_id)
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_vendedor_perfil(usuario):
    opciones = [
        "Modificar datos propios",
        "Desactivar mi cuenta",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU VENDEDOR - PERFIL")
        match num_opcion:
            case 1:
                usuario_CRUD.editar_datos_usuario(usuario)
            case 2:
                usuario_CRUD.desactivar_usuario(usuario.usuario_id)
                break
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_vendedor_gestion_productos(usuario):
    opciones = [
        "Crear producto",
        "Listar mis productos",
        "Modificar producto",
        "Dar de baja producto",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU VENDEDOR - GESTION PRODUCTOS")
        match num_opcion:
            case 1:
                producto_CRUD.crear_producto(usuario.usuario_id)
            case 2:
                producto_CRUD.imprimir_productos_usuario(usuario.usuario_id)
            case 3:
                producto_CRUD.editar_producto_propietario(usuario.usuario_id)
            case 4:
                producto_CRUD.desactivar_producto_propietario(usuario.usuario_id)
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_administrador(usuario):
    opciones = [
        "Productos",
        "Mi carrito",
        "Perfil",
        "Gestion de mis productos",
        "Gestion de usuarios",
        "Cerrar sesion",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU ADMINISTRADOR")
        match num_opcion:
            case 1:
                menu_vendedor_productos(usuario)
            case 2:
                menu_carrito(usuario)
            case 3:
                menu_vendedor_perfil(usuario)
            case 4:
                menu_vendedor_gestion_productos(usuario)
            case 5:
                menu_administrador_usuarios()
            case 0:
                break
        input("Pulse enter para continuar...")

def menu_administrador_usuarios():
    opciones = [
        "Listar usuarios",
        "Cambiar informacion de usuarios",
    ]
    while True:
        num_opcion = mostrar_menu(opciones, "MENU ADMINISTRADOR - USUARIOS")
        match num_opcion:
            case 1:
                usuario_CRUD.imprimir_usuarios()
            case 2:
                id = int(input("ID del usuario a editar: "))
                usuario_edit = obtener_usu_id(id)
                if usuario_edit is None:
                    print(f"No existe usuario con ID {id}")
                    continue
                usuario_CRUD.editar_datos_usuario(usuario_edit)
            case 0:
                break
        input("Pulse enter para continuar...")

MENUS_POR_ROL = {
    "Invitado": menu_invitado,
    "Usuario": menu_principal_usuario,
    "Vendedor": menu_vendedor,
    "Administrador": menu_administrador,
}
