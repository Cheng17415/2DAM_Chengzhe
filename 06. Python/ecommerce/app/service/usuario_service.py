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
        session.close()
    
def existe_dni(dni: str):
    session = SessionLocal()
    try:
        return session.query(Usuario).filter(
          Usuario.dni == dni.strip().upper()
          ).first() is not None
    finally:
        session.close()
        
def obtener_usu_id(usuario_id):
  session = SessionLocal()
  try:
    return session.query(Usuario).filter_by(usuario_id=usuario_id).first()
  finally:
    session.close()

def obtener_usu_email(email):
  session = SessionLocal()
  try:
    return session.query(Usuario).filter_by(email=email).first()
  finally:
    session.close()

def obtener_vendedores():
    session = SessionLocal()
    try:
        usuarios = session.query(Usuario).filter_by(rol_id=3, activo= True).all()
        return usuarios
    finally:
        session.close()

def obtener_vendedor_nombre(nombre):
    session = SessionLocal()
    try:
        usuarios = session.query(Usuario).filter(
            Usuario.nombre.ilike(f"%{nombre}%")
        ).filter_by(rol_id=3, activo= True).all()
        return usuarios
    finally:
        session.close()
