from pydantic import BaseModel, EmailStr, Field
from typing import Optional
from datetime import datetime

class UsuarioBase(BaseModel):
    nombre: str
    apellido: str
    email: EmailStr
    telefono: Optional[str]

class UsuarioCreate(UsuarioBase):
    password: str = Field(..., min_length=8)

class UsuarioResponse(UsuarioBase):
    usuario_id: int
    activo: bool
    fecha_creacion: datetime

    class Config:
        orm_mode = True

