from sqlalchemy.orm import joinedload

from app.db.database import SessionLocal
from app.models.producto import Producto


def obtener_productos():
    session = SessionLocal()
    try:
        return session.query(Producto).all()
    finally:
        session.close()

def obtener_productos_activos():
    session = SessionLocal()
    try:
        return (
            session.query(Producto)
            .options(joinedload(Producto.usuario))
            .filter_by(activo=True)
            .all()
        )
    finally:
        session.close()

def obtener_producto_id(producto_id):
    session = SessionLocal()
    try:
        return session.query(Producto).filter_by(producto_id=producto_id).first()
    finally:
        session.close()

def buscar_productos_nombre(nombre: str):
    nombre = nombre.strip()
    if not nombre:
        return []
    session = SessionLocal()
    try:
        return (
            session.query(Producto)
            .options(joinedload(Producto.usuario))
            .filter(Producto.activo.is_(True))
            .filter(Producto.nombre.ilike(f"%{nombre}%"))
            .all()
        )
    finally:
        session.close()
