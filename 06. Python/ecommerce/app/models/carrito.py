from sqlalchemy import Column, Integer, Enum, ForeignKey, TIMESTAMP
from sqlalchemy.orm import relationship
from app.db.base import Base

class Carrito(Base):
    __tablename__ = "carrito"

    carrito_id = Column(Integer, primary_key=True, index=True)
    estado = Column(Enum("ACTIVO", "CERRADO"))
    fecha_creacion = Column(TIMESTAMP)

    usuario_id = Column(Integer, ForeignKey("usuario.usuario_id"))
    usuario = relationship("Usuario")

class EstadoCarrito(Enum):
    ACTIVO = "ACTIVO"
    CERRADO = "CERRADO"