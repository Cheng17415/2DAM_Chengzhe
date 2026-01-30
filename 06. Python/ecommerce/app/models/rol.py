from sqlalchemy import Column, Integer, String
from app.db.base import Base

class Rol(Base):
    __tablename__ = "rol"

    rol_id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String(50), unique=True, nullable=False)
