from enum import Enum
import os

from codigos_postales import obtenerProvincia, verificarCP
from util import comprobarDNI, is_valid_email

RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
FICHERO_CLIENTES = os.path.join(RUTA_ACTUAL, "base_datos/clientes.txt")

clientes = []

class Cliente:
    def __init__(self, dni, nombre, apellidos, telefono, email, cp, area_negocio, ingresos):
        self.dni = dni.strip().upper()
        self.nombre = nombre
        self.apellidos = apellidos
        self.telefono = telefono
        self.email = email
        self.cp = cp
        self.provincia = obtenerProvincia(cp)
        try:
            if isinstance(area_negocio, AREA_NEGOCIO):
                self.area_negocio = area_negocio
            else:
                self.area_negocio = AREA_NEGOCIO(area_negocio)
        except ValueError:
            print(f"Area de negocio desconocida: {area_negocio}. Se asignara 'Restauracion'.")
            self.area_negocio = AREA_NEGOCIO.RESTAURACION
        self.ingresos = float(ingresos)
        self.estado = ESTADO.ACTIVO

    def to_file(self) -> str:
        valores = [
            self.dni,
            self.nombre,
            self.apellidos,
            self.telefono,
            self.email,
            self.cp,
            self.area_negocio.value,
            self.ingresos,
            self.estado.value,
        ]
        return ";".join(map(str, valores))

    def to_str(self) -> str:
        return (
            f"DNI: {self.dni}\n"
            f"Nombre: {self.nombre} {self.apellidos}\n"
            f"Telefono: {self.telefono}\n"
            f"Email: {self.email}\n"
            f"Codigo Postal: {self.cp} ({self.provincia})\n"
            f"Area de negocio: {self.area_negocio.value}\n"
            f"Ingresos: {self.ingresos:.2f}\n"
            f"Estado: {self.estado.value.capitalize()}\n"
            f"{'-' * 40}"
        )


class AREA_NEGOCIO(Enum):
    RESTAURACION = "Restauracion"
    SANIDAD = "Sanidad"
    ADMINISTRACION = "Administracion"


class ESTADO(Enum):
    BAJA = "Baja"
    ACTIVO = "Activo"


def guardar_cambios():
    with open(FICHERO_CLIENTES, "w", encoding="utf-8") as f:
        for cliente in clientes:
            f.write(cliente.to_file() + "\n")


def existeDNI(dni: str) -> bool:
    dni_normalizado = dni.strip().upper()
    return any(cliente.dni.strip().upper() == dni_normalizado for cliente in clientes)


def leer_fichero():
    clientes.clear()
    try:
        with open(FICHERO_CLIENTES, "r", encoding="utf-8") as f:
            for linea in f:
                partes = linea.strip().split(";")
                if len(partes) != 9:
                    continue

                try:
                    dni = partes[0]
                    nombre = partes[1]
                    apellidos = partes[2]
                    telefono = partes[3]
                    email = partes[4]
                    cp = partes[5]
                    try:
                        area_negocio = AREA_NEGOCIO(partes[6])
                    except ValueError:
                        print(f"Area de negocio desconocido: {partes[6]}. Se asignara 'Sanidad'.")
                        area_negocio = AREA_NEGOCIO.SANIDAD
                    ingresos = float(partes[7])
                    try:
                        estado = ESTADO(partes[8])
                    except ValueError:
                        print(f"Estado desconocido: {partes[8]}. Se asignara 'ACTIVO'.")
                        estado = ESTADO.ACTIVO

                    cliente_nuevo = Cliente(dni, nombre, apellidos, telefono, email, cp, area_negocio, ingresos)
                    cliente_nuevo.estado = estado
                    clientes.append(cliente_nuevo)
                except Exception as e:
                    print(f"Error procesando linea: {e}")
    except FileNotFoundError:
        print(f"Archivo '{FICHERO_CLIENTES}' no encontrado.")


def obtener_area_negocio():
    while True:
        print("Seleccione area de negocio:")
        for i, area_negocio in enumerate(AREA_NEGOCIO, 1):
            print(f"\t{i}. {area_negocio.value.capitalize()}")
        num = input("-> ").strip()
        if not num.isdigit():
            continue
        num = int(num)
        if num < 1 or num > len(AREA_NEGOCIO):
            continue
        return list(AREA_NEGOCIO)[num - 1]


def pedir_con_validacion(mensaje, funcion_validadora, mensaje_error):
    while True:
        valor = input(mensaje).strip()
        if funcion_validadora(valor):
            return valor
        print(mensaje_error)


def agregarCliente() -> None:
    dni = pedir_con_validacion(
        "Introduzca el DNI: ",
        lambda d: not existeDNI(d),
        "DNI ya existe",
    )
    nombre = input("Nombre: ").strip()
    apellidos = input("Apellidos: ").strip()
    telefono = input("Telefono: ").strip()
    email = pedir_con_validacion(
        "Email: ",
        is_valid_email,
        "Email no es valido",
    )
    codigoPostal = pedir_con_validacion(
        "Codigo Postal: ",
        verificarCP,
        "Codigo postal no es valido",
    )
    area_negocio = obtener_area_negocio()
    ingresos = input("Ingresos: ").strip()

    cliente = Cliente(dni, nombre, apellidos, telefono, email, codigoPostal, area_negocio, ingresos)
    clientes.append(cliente)

    print("Cliente anadido con exito!")
    guardar_cambios()


def buscar_cliente_por_dni(dni: str):
    dni = dni.strip().upper()
    for cliente in clientes:
        if cliente.dni == dni:
            return cliente
    return None


def obtener_estado():
    while True:
        print("Seleccione estado:")
        for i, estado in enumerate(ESTADO, 1):
            print(f"\t{i}. {estado.value}")
        num = input("-> ").strip()
        if not num.isdigit():
            continue
        num = int(num)
        if num < 1 or num > len(ESTADO):
            continue
        return list(ESTADO)[num - 1]


def modificar():
    if not clientes:
        print("No hay clientes registrados.")
        return

    dni = input("Introduzca el DNI del cliente a modificar: ").strip().upper()
    cliente = buscar_cliente_por_dni(dni)

    if not cliente:
        print("No existe un cliente con ese DNI.")
        return

    print("Que desea modificar?")
    print("1. Area de negocio")
    print("2. Ingresos")
    print("3. Estado")
    print("4. Todo")

    opcion = input("-> ").strip()

    if opcion in ("1", "4"):
        cliente.area_negocio = obtener_area_negocio()

    if opcion in ("2", "4"):
        while True:
            ingresos = input("Nuevos ingresos: ").strip()
            try:
                cliente.ingresos = float(ingresos)
                break
            except ValueError:
                print("Ingresos no validos")

    if opcion in ("3", "4"):
        cliente.estado = obtener_estado()

    if opcion not in ("1", "2", "3", "4"):
        print("Opcion no valida")
        return

    guardar_cambios()
    print("Cliente modificado con exito!")


def listar() -> None:
    hay_activos = False
    for area in AREA_NEGOCIO:
        clientes_area = [cliente for cliente in clientes if cliente.area_negocio == area and cliente.estado == ESTADO.ACTIVO]
        if not clientes_area:
            continue

        hay_activos = True
        print(f"\nAREA: {area.value}")
        print("=" * 40)
        for cliente in clientes_area:
            print(cliente.to_str())

    if not hay_activos:
        print("No hay clientes activos.")


def ingresos_por_area() -> None:
    print("Ingresos por area de negocio:")
    for area in AREA_NEGOCIO:
        total = 0
        for cliente in clientes:
            if cliente.area_negocio == area:
                total += cliente.ingresos
        print(f"- {area.value}: {total:.2f}")


def dni_incorrectos() -> None:
    dnis_validos = [c.dni for c in clientes if comprobarDNI(c.dni)]
    dnis_invalidos = [c.dni for c in clientes if not comprobarDNI(c.dni)]

    if not dnis_invalidos:
        print("No hay DNIs incorrectos.")
        return

    if not dnis_validos:
        print("No se puede listar: no hay ningun DNI correcto registrado.")
        return

    print("DNIs incorrectos:")
    for dni in dnis_invalidos:
        print(f"- {dni}")


leer_fichero()
