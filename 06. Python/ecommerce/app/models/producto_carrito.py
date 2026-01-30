from sqlalchemy import Column, Integer, ForeignKey
from app.db.base import Base

class ProductoCarrito(Base):
    __tablename__ = "producto_carrito"

    carrito_id = Column(Integer, ForeignKey("carrito.carrito_id"), primary_key=True)
    producto_id = Column(Integer, ForeignKey("producto.producto_id"), primary_key=True)
    cantidad = Column(Integer, nullable=False)
