from pydantic import BaseModel, Field

class AddProductoCarrito(BaseModel):
    producto_id: int
    cantidad: int = Field(..., gt=0)
