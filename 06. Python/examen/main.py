import sys

from cliente import agregarCliente, modificar, listar, ingresos_por_area, dni_incorrectos


def menu():
    while True:
        print("\nMENU CLIENTE")
        print("1. Alta")
        print("2. Modificar")
        print("3. Listar clientes activos")
        print("4. Ingresos por area de negocio")
        print("5. DNIs incorrectos")
        print("0. Salir")
        opcion = input("Seleccione una opcion: ").strip()

        match opcion:
            case "1":
                agregarCliente()
            case "2":
                modificar()
            case "3":
                listar()
            case "4":
                ingresos_por_area()
            case "5":
                dni_incorrectos()
            case "0":
                print("\nSaliendo de la agenda... Hasta pronto!")
                break
            case _:
                print("\nOpcion no valida. Intente de nuevo.")
        if opcion != "0":
            input("Pulse enter para continuar...")


if __name__ == "__main__":
    menu()
