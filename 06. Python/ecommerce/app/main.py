import os
import sys

RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
PARENT_DIR = os.path.abspath(os.path.join(RUTA_ACTUAL, ".."))
sys.path.append(PARENT_DIR)

from app.menu.menu import menu_principal, menu_inicio_sesion

if __name__ == "__main__":
    menu_inicio_sesion()