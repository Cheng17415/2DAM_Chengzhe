from app.models.rol import Rol
from sqlalchemy import Column, Integer, String, Boolean, ForeignKey, TIMESTAMP
from sqlalchemy.orm import relationship
from app.db.base import Base

class Usuario(Base):
    __tablename__ = "usuario"

    usuario_id = Column(Integer, primary_key=True, index=True)
    dni = Column(String(9), unique=True)
    nombre = Column(String(50), nullable=False)
    apellido = Column(String(50), nullable=False)
    direccion = Column(String(60))
    codigo_postal = Column(String(10))
    telefono = Column(String(20))
    email = Column(String(70), unique=True, nullable=False)
    password_hash = Column(String(255), nullable=False)
    activo = Column(Boolean, default=True)
    fecha_creacion = Column(TIMESTAMP)

    rol_id = Column(Integer, ForeignKey("rol.rol_id"))
    rol = relationship("Rol")
    
    def __str__(self):
        return f"\t{self.usuario_id:<3}|{self.nombre:<3}|{self.email:<3}"
