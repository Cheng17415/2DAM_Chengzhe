from sqlalchemy import Column, Integer, String, DECIMAL, Boolean, TIMESTAMP
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

    def __str__(self):
        return f"{self.producto_id}|{self.nombre}|{self.precio}"

    def obtenerProducto(self) -> tuple[str, ...]:
        return (
            str(self.producto_id),
            str(self.nombre),
            str(self.descripcion or ""),
            str(self.precio),
            str(self.stock),
            str(self.activo),
            str(self.fecha_creacion),
        )
