from app.models.usuario import Usuario
from app.db.database import SessionLocal

def get_all_usuarios():
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
  usuarios = get_all_usuarios()
  if not usuarios:
    print("No existen usuarios en la BBDD")
    return
  
  print("\tLista de Usuarios\n")
  print("\tID|Nombre|Email")
  for usuario in usuarios:
    print(usuario)
