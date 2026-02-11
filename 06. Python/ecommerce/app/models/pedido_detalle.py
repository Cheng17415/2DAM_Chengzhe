from sqlalchemy import Column, Integer, DECIMAL, ForeignKey, CheckConstraint
from app.db.base import Base


class Pedido_detalle(Base):
    __tablename__ = "pedido_detalle"

    pedido_id = Column(Integer, ForeignKey("pedido.pedido_id"), primary_key=True, nullable=False)
    producto_id = Column(Integer, ForeignKey("producto.producto_id"), primary_key=True, nullable=False)
    precio_unitario = Column(DECIMAL(10, 2), nullable=False)
    cantidad = Column(Integer, nullable=False)

    __table_args__ = (
        CheckConstraint("cantidad >= 0", name="ck_pedido_detalle_cantidad_no_negativa"),
    )
