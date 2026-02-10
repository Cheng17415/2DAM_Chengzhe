from sqlalchemy import Column, Integer, DECIMAL, Enum, ForeignKey, TIMESTAMP
from sqlalchemy.orm import relationship
from app.db.base import Base

class Pedido(Base):
    __tablename__ = "pedido"

    pedido_id = Column(Integer, primary_key=True, index=True)
    total = Column(DECIMAL(10,2), nullable=False)
    estado = Column(Enum("PENDIENTE","PAGADO","ENVIADO","ENTREGADO","CANCELADO"))
    fecha = Column(TIMESTAMP)

    usuario_id = Column(Integer, ForeignKey("usuario.usuario_id"))
    opcion_envio_id = Column(Integer, ForeignKey("opciones_envio.opciones_id"))

    usuario = relationship("Usuario")
    envio = relationship("OpcionesEnvio")

class EstadoPedido(Enum):
    PENDIENTE = "PENDIENTE"
    PAGADO = "PAGADO"
    ENVIADO = "ENVIADO"
    ENTREGADO = "ENTREGADO"
    CANCELADO = "CANCELADO"