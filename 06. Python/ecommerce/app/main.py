import os
import sys

# Add the parent directory to sys.path to resolve 'app' module
RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
PARENT_DIR = os.path.abspath(os.path.join(RUTA_ACTUAL, ".."))
sys.path.append(PARENT_DIR)

from app.db.database import SessionLocal
from app.models.usuario import Usuario

def get_all_usuarios():
    # Create a new database session
    session = SessionLocal()
    try:
        # Perform the SELECT * FROM usuario
        usuarios = session.query(Usuario).all()
        for usuario in usuarios:
            print(usuario)
    finally:
        # Close the session
        session.close()

if __name__ == "__main__":
    get_all_usuarios()