from persona import (
    agregarPersona,
    leer_lista_personas,
    modificar_persona,
    eliminar_persona
)
import preguntas

def menu_principal():
    while True:
        print("\n====== MENU PRINCIPAL ======")
        print("1. CRUD")
        print("2. Preguntas")
        print("0. Salir")

        opcion = input("Selecciona una opción: ")

        match opcion:
            case "1":
                menu_CRUD()
            case "2":
                menu_preguntas()
            case "0":
                print("Saliendo del programa...")
                break
            case _:
                print("Opción no válida")
        if opcion != "0":
            input("Presione una enter para continuar... ")
            
def menu_CRUD():
    while True:
        print("\n====== AGENDA DE PERSONAS ======")
        print("1. Añadir persona")
        print("2. Listar personas")
        print("3. Modificar persona")
        print("4. Eliminar persona")
        print("0. Volver")

        opcion = input("Selecciona una opción: ")

        match opcion:
            case "1":
                agregarPersona()
            case "2":
                leer_lista_personas()
            case "3":
                modificar_persona()
            case "4":
                eliminar_persona()
            case "0":
                print("Volviendo al menu principal...")
                break
            case _:
                print("Opción no válida")
        if opcion != "0":
            input("Presione una enter para continuar... ")

def menu_visualizar():
    while True:
        print("\n====== MENU VISUALIZAR ======")
        print("1. Cuántos usuarios hay de cada ciudad")
        print("2. Cuántos usuarios hay de cada provincia")
        print("0. Volver")

        opcion = input("Selecciona una opción: ")

        match opcion:
            case "1":
                preguntas.visualizar_usuarios_por_ciudad()
            case "2":
                preguntas.visualizar_usuarios_por_provincia()
            case "0":
                print("Volviendo al menu principal...")
                break
            case _:
                print("Opción no válida")
        if opcion != "0":
            input("Presione una enter para continuar... ")
def menu_filtar():
    while True:
        print("\n====== MENU FILTAR ======")
        print("1. Filtrar usuarios por descripción")
        print("2. Filtrar usuarios por nombre")
        print("3. Filtrar usuarios por teléfono")
        print("0. Volver")

        opcion = input("Selecciona una opción: ")

        match opcion:
            case "1":
                preguntas.visualizar_filtrar_por_descripcion()
            case "2":
                preguntas.visualizar_filtrar_por_nombre()
            case "3":
                preguntas.visualizar_filtrar_por_telefono()
            case "0":
                print("Volviendo al menu preguntas...")
                break
            case _:
                print("Opción no válida")
        if opcion != "0":
            input("Presione una enter para continuar... ")


def menu_preguntas():
    while True:
        print("\n====== PREGUNTAS ======")
        print("1. Visualizar")
        print("2. Filtrar usuarios")
        print("3. Exportar a CSV")
        print("0. Volver")

        opcion = input("Selecciona una opción: ")

        match opcion:
            case "1":
                menu_visualizar()
            case "2":
                menu_filtar()
            case "3":
                preguntas.exportar_csv()
            case "0":
                print("Volviendo al menu principal...")
                break
            case _:
                print("Opción no válida")
        if opcion != "0":
            input("Presione una enter para continuar... ")


if __name__ == "__main__":
    menu_principal()
