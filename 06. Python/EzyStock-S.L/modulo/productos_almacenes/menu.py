# modules/productos_almacenes/menu.py
import sys
import os

# Agregar la carpeta actual al path para que Python encuentre los módulos
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from productos import crear_producto, listar_productos, actualizar_producto, desactivar_producto
from almacenes import crear_almacen, listar_almacenes, actualizar_almacen, desactivar_almacen

def menu_principal():
    while True:
        print("\n" + "="*40)
        print("     MÓDULO PRODUCTOS & ALMACENES")
        print("="*40)
        print("1. Productos")
        print("2. Almacenes")
        print("0. Salir")
        print("-"*40)

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "1":
            menu_productos()
        elif opcion == "2":
            menu_almacenes()
        elif opcion == "0":
            print("\n¡Gracias por usar el sistema!")
            break
        else:
            print("️ Opción inválida. Intente nuevamente.")

# -------------------------------
# Submenú Productos
# -------------------------------
def menu_productos():
    while True:
        print("\n" + "-"*30)
        print("   PRODUCTOS")
        print("-"*30)
        print("1. Registrar producto")
        print("2. Listar productos")
        print("3. Modificar producto")
        print("4. Desactivar producto")
        print("0. Volver al menú principal")
        print("-"*30)

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "1":
            crear_producto()
        elif opcion == "2":
            listar_productos()
        elif opcion == "3":
            actualizar_producto()
        elif opcion == "4":
            desactivar_producto()
        elif opcion == "0":
            break
        else:
            print("Opción inválida. Intente nuevamente.")

# -------------------------------
# Submenú Almacenes
# -------------------------------
def menu_almacenes():
    while True:
        print("\n" + "-"*30)
        print("ALMACENES")
        print("-"*30)
        print("1.Registrar almacén")
        print("2.Listar almacenes")
        print("3.Modificar almacén")
        print("4.Desactivar almacén")
        print("0.Volver al menú principal")
        print("-"*30)

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "1":
            crear_almacen()
        elif opcion == "2":
            listar_almacenes()
        elif opcion == "3":
            actualizar_almacen()
        elif opcion == "4":
            desactivar_almacen()
        elif opcion == "0":
            break
        else:
            print("Opción inválida. Intente nuevamente.")


# -------------------------------
# Ejecutar menú principal
# -------------------------------
if __name__ == "__main__":
    print("="*50)
    print("     SISTEMA DE GESTIÓN DE INVENTARIO")
    print("="*50)
    menu_principal()