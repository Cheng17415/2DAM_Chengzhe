from persona import (
    agregarPersona,
    leer_lista_personas,
    modificar_persona,
    eliminar_persona
)

def menu():
    while True:
        print("\n====== AGENDA DE PERSONAS ======")
        print("1. Añadir persona")
        print("2. Listar personas")
        print("3. Modificar persona")
        print("4. Eliminar persona")
        print("0. Salir")

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
                print("Saliendo del programa...")
                break
            case _:
                print("Opción no válida")


if __name__ == "__main__":
    menu()
