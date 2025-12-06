from modulo.usuarios_grupos import empleados as emp

opciones = (
  "1. Agregar empleado","2. Listar empleados","3. Modificar empleado","4. Eliminar empleado",
  "5. Encontrar empleado","6. Empleados por supervisor", "7. Empleados por tercero",
  "8. Salario mensual","9. Salario máximo y mínimo","10. Histograma de edades","11. Ir al menú principal", "12. Salir"
)

def menu(titulo, opciones):
    emp.leer_fichero()
    ESPACIOS_INICIO = " " * 10
    MAX_WIDTH = 50
    acciones = {
        1: emp.agregarEmpleado,
        2: emp.listarEmpleados,
        3: emp.modificarEmpleado,
        4: emp.eliminarEmpleado,
        5: emp.encontrarEmpleado,
        6: emp.empleadosPorSupervisor,
        7: emp.empleadoTercero,
        8: emp.preguntaSalarioMensual,
        9: emp.salarios,
        10: emp.histogramaEdades
    }
    OPC_VOLVER= len(opciones) -1
    OPC_SALIR = OPC_VOLVER +1

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
            opc = int(input("\n\tElija una opcion: "))
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
    menu("Menú Empleados", opciones)
