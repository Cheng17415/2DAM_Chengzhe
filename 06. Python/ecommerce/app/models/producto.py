from sqlalchemy import Column, Integer, String, DECIMAL, Boolean, TIMESTAMP, ForeignKey
from sqlalchemy.orm import relationship
from app.db.base import Base

class Producto(Base):
    __tablename__ = "producto"

    producto_id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String(50), nullable=False)
    descripcion = Column(String(500))
    precio = Column(DECIMAL(10, 2), nullable=False)
    stock = Column(Integer, nullable=False)
    activo = Column(Boolean, default=True)
    fecha_creacion = Column(TIMESTAMP)
    usuario_id = Column(Integer, ForeignKey("usuario.usuario_id"))
    usuario = relationship("Usuario")

    def __str__(self):
        return f"{self.producto_id}|{self.nombre}|{self.precio}"

    def obtener_producto_completo(self) -> tuple[str, ...]:
        return (
            str(self.producto_id),
            str(self.nombre),
            str(self.descripcion or ""),
            str(self.precio),
            str(self.stock),
            str(self.activo),
            str(self.fecha_creacion),
            str(self.usuario_id)
        )
    def obtener_producto(self) -> tuple[str, ...]:
        return (
            str(self.producto_id),
            str(self.nombre),
            str(self.descripcion or ""),
            str(self.precio),
            str(self.stock),
            str(self.usuario.nombre)
        )
