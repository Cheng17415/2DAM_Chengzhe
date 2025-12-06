import sys
import os

# Add the parent directory of 'modulo' to the Python path
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from modulo.usuarios_grupos.menu_empleados import menu, opciones
from modulo.terceros.menu_terceros import menu_tercero, opciones_terceros
from modulo.productos_almacenes.menu import menu_prodAlm,opciones_prodAlm

opciones_principal = (
    "1. Usuarios y grupos", "2. Terceros", "3. Productos / Almacenes",
    "4. Comercial / Pedidos", "5. Salir"
)

def menu_principal(titulo, opciones_menu):
    ESPACIOS_INICIO = " " * 10
    MAX_WIDTH = 50
    salir = len(opciones_menu)
    
    def abrir_submenu_empleados():
        menu("Menú empleados", opciones)

    def abrir_submenu_terceros():
        menu_tercero("Menú terceros", opciones_terceros)
    
    def abrir_submenu_productos():
        menu_prodAlm("Menú productos y almacenes", opciones_prodAlm)

    #def abrir_submenu_comercial():
    #    menu_comercial("Menú comercial", opciones_comercial)
    
    acciones_menu = {
        1: abrir_submenu_empleados,
        2: abrir_submenu_terceros,
        3: abrir_submenu_productos,
        #4: abrir_submenu_comercial
    }

    while True:
        print(ESPACIOS_INICIO + "┌" + "─" * MAX_WIDTH + "┐")
        print(ESPACIOS_INICIO + "│" + titulo.center(MAX_WIDTH) + "│")
        print(ESPACIOS_INICIO + "├" + "─" * MAX_WIDTH + "┤")
        for opcion in opciones_menu:
            print(ESPACIOS_INICIO + "│ " + opcion.ljust(MAX_WIDTH - 1) + "│")
        print(ESPACIOS_INICIO + "└" + "─" * MAX_WIDTH + "┘")

        try:
            opc = int(input("\n\tElija una opción: "))
        except ValueError:
            print("\tOpción inválida")
            continue

        if opc == salir:
            print("\tGracias por utilizar el programa!")
            break

        accion = acciones_menu.get(opc)
        if accion:
            accion()
        else:
            print('\tOpción no disponible')

menu_principal("MENÚ PRINCIPAL", opciones_principal)