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
        return f"{self.usuario_id}|{self.nombre}|{self.email}"
    
    def obtener_usuario(self) -> tuple[str, ...]:
        return (
        str(self.usuario_id),
        str(self.dni or ""),
        str(self.nombre),
        str(self.apellido),
        str(self.direccion or ""),
        str(self.codigo_postal or ""),
        str(self.telefono or ""),
        str(self.email),
        str(self.password_hash),
        str(self.activo),
        str(self.fecha_creacion))
    
    def obtener_vendedor(self) -> tuple[str, ...]:
        return (
        str(self.dni or ""),
        str(self.nombre),
        str(self.apellido),
        str(self.direccion or ""),
        str(self.codigo_postal or ""),
        str(self.telefono or ""),
        str(self.email))


