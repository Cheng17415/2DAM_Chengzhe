import sys

from crud import alta, baja, modificar, listar, buscar_por_nombre, filtrar_por_categoria, exportar_csv, importar_csv


def main():
    while True:
        print("\nMENU")
        print("1. Alta")
        print("2. Baja")
        print("3. Modificar")
        print("4. Listar")
        print("5. Buscar por nombre")
        print("6. Filtrar por categoría")
        print("7. Importar archivo")
        print("8. Exportar a csv")
        print("0. Salir")
        opcion = input(f"Seleccione una opción: ").strip()

        match opcion:
            case "1":
                alta()
            case "2":
                baja()
            case "3":
                modificar()
            case "4":
                listar()
            case "5":
                buscar_por_nombre()
            case "6":
                filtrar_por_categoria()
            case "7":
                importar_csv()
            case "8":
                exportar_csv()
            case "0":
                print(f"\nSaliendo de la agenda... ¡Hasta pronto!")
                sys.exit()
            case _:
                print(f"\nOpción no válida. Intente de nuevo.")

if __name__ == '__main__':
    main()