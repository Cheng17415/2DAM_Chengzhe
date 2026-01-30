from sqlalchemy import Column, Integer, String, DECIMAL, Boolean, TIMESTAMP
from app.db.base import Base

class Producto(Base):
    __tablename__ = "producto"

    producto_id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String(50), nullable=False)
    descripcion = Column(String(500))
    precio = Column(DECIMAL(10, 2), nullable=False)
    stock = Column(Integer, nullable=False)
    imagen_url = Column(String(255))
    activo = Column(Boolean, default=True)
    fecha_creacion = Column(TIMESTAMP)
