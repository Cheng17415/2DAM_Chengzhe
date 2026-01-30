from sqlalchemy import Column, Integer, DECIMAL
from app.db.base import Base

class OpcionesEnvio(Base):
    __tablename__ = "opciones_envio"

    opciones_id = Column(Integer, primary_key=True)
    dias = Column(Integer, nullable=False)
    precio = Column(DECIMAL(10,2), nullable=False)
