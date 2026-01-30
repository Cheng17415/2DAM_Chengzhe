import os
import sys

RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
PARENT_DIR = os.path.abspath(os.path.join(RUTA_ACTUAL, ".."))
sys.path.append(PARENT_DIR)


from app.models.usuario import Usuario
from app.crud.usuarioCRUD import imprimir_usuarios


if __name__ == "__main__":
    imprimir_usuarios()