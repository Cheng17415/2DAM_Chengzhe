from app.db.database import SessionLocal
from app.models.usuario import Usuario

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
         
def existe_dni(dni: str):
  #Crea una nueva sesion del database
    session = SessionLocal()
    try:
        #SELECT * FROM usuario
        return session.query(Usuario).filter(
          Usuario.dni == dni.strip()
          ).first() is not None
    finally:
        #Cerrar sesion
        session.close()
        
def obtener_usu_id(usuario_id):
  session = SessionLocal()
  try:
    return session.query(Usuario).filter_by(usuario_id=usuario_id).first()
  finally:
    session.close()