from app.models.usuario import Usuario
import app.util.utileria as util
from app.db.database import SessionLocal
from rich.console import Console
from rich.table import Table
from datetime import datetime
def obtener_usuarios():
    #Crea una nueva sesion del database
    session = SessionLocal()
    try:
        #SELECT * FROM usuario
        usuarios = session.query(Usuario).all()
        return usuarios
    finally:
        #Cerrar sesion
        session.close()   

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
    util.comprobarDNI,
    "Email no valido"
  )
  contrasena = util.pedir_contrasena("Introduzca la contrasena: ")
  contrasena_hash = util.hash_contrasena(contrasena)
  usuario = Usuario(dni= dni, nombre = nombre, apellido = apellido,
                    direccion = direccion, codigo_postal = codigo_postal,
                    telefono = telefono, email = email,
                    password_hash = contrasena_hash, 
                    activo = True, fecha_creacion = datetime.now(), rol_id = 2)
  
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
  