from pydantic import BaseModel, Field
from typing import Optional

class ProductoBase(BaseModel):
    nombre: str
    descripcion: Optional[str]
    precio: float = Field(..., gt=0)

class ProductoCreate(ProductoBase):
    stock: int = Field(..., ge=0)

class ProductoResponse(ProductoBase):
    producto_id: int
    stock: int
    activo: bool

    class Config:
        orm_mode = True
