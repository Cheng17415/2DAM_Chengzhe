from app.db.database import SessionLocal
from app.models.producto import Producto


def obtener_productos():
    session = SessionLocal()
    try:
        return session.query(Producto).all()
    finally:
        session.close()


def obtener_producto_id(producto_id):
    session = SessionLocal()
    try:
        return session.query(Producto).filter_by(producto_id=producto_id).first()
    finally:
        session.close()
