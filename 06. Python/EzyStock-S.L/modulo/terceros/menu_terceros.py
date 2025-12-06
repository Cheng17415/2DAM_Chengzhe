from modulo.terceros.nuevo_tercero import agregar_tercero_menu, listar_terceros_menu, buscar_tercero_por_id_menu, \
    buscar_tercero_por_nombre_menu, modificar_tercero_menu, eliminar_tercero_menu, mostrar_grafico_acciones_menu

opciones_terceros = (
    "1. Nuevo tercero", "2. Listado", "3. Buscar por ID","4. Buscar por nombre",
    "5. Modificar tercero", "6. Eliminar tercero" ,"7. Visualizar grafico",
    "8. Ir al menú principal", "9. Salir"
)


def menu_tercero(titulo, opciones):

    ESPACIOS_INICIO = " " * 10
    MAX_WIDTH = 50
    acciones = {
        1: agregar_tercero_menu,
        2: listar_terceros_menu,
        3: buscar_tercero_por_id_menu,
        4: buscar_tercero_por_nombre_menu,
        5: modificar_tercero_menu,
        6: eliminar_tercero_menu,
        7: mostrar_grafico_acciones_menu
    }

    OPC_VOLVER = 8
    OPC_SALIR = 9

    while True:
        print(ESPACIOS_INICIO + "┌" + "─" * MAX_WIDTH + "┐")
        print(ESPACIOS_INICIO + "│" + " " * MAX_WIDTH + "│")
        print(ESPACIOS_INICIO + "│" + titulo.center(MAX_WIDTH) + "│")
        print(ESPACIOS_INICIO + "├" + "─" * MAX_WIDTH + "┤")
        print(ESPACIOS_INICIO + "│" + " " * MAX_WIDTH + "│")

        for opcion in opciones:
            print(ESPACIOS_INICIO + "│ " + opcion.ljust(MAX_WIDTH - 1) + "│")

        print(ESPACIOS_INICIO + "│" + " " * MAX_WIDTH + "│")
        print(ESPACIOS_INICIO + "└" + "─" * MAX_WIDTH + "┘")

        try:
            opc = int(input("\n\tElija una opción: "))
        except ValueError:
            print("\tOpción inválida")
            continue

        if opc == OPC_VOLVER:
            break

        if opc == OPC_SALIR:
            print("Saliendo del programa...")
            exit()

        accion = acciones.get(opc)
        if accion:
            accion()
        else:
            print('\tOpción no disponible')
        input("\nPresione enter para volver al menu...")


if __name__ == "__main__":
    menu_tercero("Menú Terceros", opciones_terceros)