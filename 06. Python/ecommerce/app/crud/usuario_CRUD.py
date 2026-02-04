from app.models.usuario import Usuario
import app.util.utileria as util
from app.db.database import SessionLocal
from rich.console import Console
from rich.table import Table
from datetime import datetime
from app.service.usuario_service import obtener_usuarios, obtener_usu_email 
from app.util.utileria import verificar_contrasena

def imprimir_usuarios():
  usuarios = obtener_usuarios()
  if not usuarios:
    print("No existen usuarios en la BBDD")
    return
  table = Table(title="Usuarios")
  columnas = ["ID","DNI", "Nombre","Apellido","Direccion" ,"Codigo Postal", "Telefono","Email", "Contrasena", "Activo", "Fecha Creacion"]
  for columna in columnas:
    table.add_column(columna)
  for usuario in usuarios:
    table.add_row(*usuario.obtenerUsuario())
  console = Console()
  console.print(table)

def crear_usuario():
  console = Console()
  dni = util.pedir_con_validacion(
    "Introduzca el DNI: ",
    util.comprobarDNI,
    "DNI no es valido o ya existe")
  nombre = util.pedir_no_vacio("Introduzca el nombre: ")
  apellido = util.pedir_no_vacio("Introduzca el apellido: ")
  direccion = input("Introduzca la direccion (Opcional): ")
  codigo_postal = input("Introduzca el codigo postal (Opcional): ")
  telefono = input("Introduzca el telefono (Opcional): ")
  
  email = util.pedir_con_validacion(
    "Introduzca el email: ",
    util.comprobarEmail,
    "Email no valido"
  )
  try:
    rol_id = int(input("Introduzca su rol:\n\t1. Usuario\n\t2. Vendedor\n")) + 1
  except Exception as e:
      console.print("[red]Error al introducir el rol. Usando Usuario por defecto[/red]")
      rol_id = 2
  contrasena = util.pedir_contrasena_registro("Introduzca la contrasena: ")
  contrasena_hash = util.hash_contrasena(contrasena)
  usuario = Usuario(dni= dni, nombre = nombre, apellido = apellido,
                    direccion = direccion, codigo_postal = codigo_postal,
                    telefono = telefono, email = email,
                    password_hash = contrasena_hash, 
                    activo = True, fecha_creacion = datetime.now(), rol_id = rol_id)
  
  session = SessionLocal()
  console = Console()
  try:
    
    session.add(usuario)
    session.commit()
    console.print("[green]Usuario creado correctamente[/green]")
  except Exception as e:
    session.rollback()
    console.print(f"[red]Error al crear usuario: {e}[/red]")
  finally:
    session.close()

def editar_usuario(
    usuario_id: int,
    nombre: str | None = None,
    apellido: str | None = None,
    direccion: str | None = None,
    codigo_postal: str | None = None,
    telefono: str | None = None,
    email: str | None = None,
):

    nombre = None if nombre is None or nombre.strip() == "" else nombre
    apellido = None if apellido is None or apellido.strip() == "" else apellido
    direccion = None if direccion is None or direccion.strip() == "" else direccion
    codigo_postal = None if codigo_postal is None or codigo_postal.strip() == "" else codigo_postal
    telefono = None if telefono is None or telefono.strip() == "" else telefono
    email = None if email is None or email.strip() == "" else email

    session = SessionLocal()
    console = Console()
    try:
        usuario = session.query(Usuario).filter_by(usuario_id=usuario_id).first()
        
        if not usuario:
            console.print("[red]Usuario no encontrado[/red]")
            return False

        if nombre is not None:
            usuario.nombre = nombre# type: ignore
        if apellido is not None:
            usuario.apellido = apellido# type: ignore
        if direccion is not None:
            usuario.direccion = direccion# type: ignore
        if codigo_postal is not None:
            usuario.codigo_postal = codigo_postal# type: ignore
        if telefono is not None:
            usuario.telefono = telefono# type: ignore
        if email is not None:
            usuario.email = email# type: ignore

        session.commit()
        console.print("[green]Usuario actualizado correctamente[/green]")
        return True

    except Exception as e:
        session.rollback()
        console.print(f"[red]Error al editar usuario: {e}[/red]")
        return False
    finally:
        session.close()

def desactivar_usuario(usuario_id: int):
    session = SessionLocal()
    console = Console()
    try:
        usuario = session.query(Usuario).filter_by(usuario_id=usuario_id).first()

        if not usuario:
            console.print("[red]Usuario no encontrado[/red]")
            return False

        usuario.activo = False # type: ignore
        session.commit()

        console.print("[green]Usuario desactivado correctamente[/green]")
        return True

    except Exception as e:
        session.rollback()
        console.print(f"[red]Error al desactivar usuario: {e}[/red]")
        return False
    finally:
        session.close()