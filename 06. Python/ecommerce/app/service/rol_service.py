from app.db.database import SessionLocal
from app.models.rol import Rol
def obtener_nombre_rol(id):
  session = SessionLocal()
  try:
    rol = session.query(Rol).filter_by(rol_id = id).first()
    if rol:
      return rol.nombre
    return None
  finally:
    session.close()