from codigos_postales import obtenerProvincia, verificarCP
from enum import Enum
import os
import re
# Ruta del archivo de personas
RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
FICHERO_PERSONAS = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")

personas = []

class Persona:
    #Constructor
    def __init__(self, dni,nombre, apellidos, direccion, codigoPostal, ciudad, telefono, email, descripcion):
        self.dni = dni
        self.nombre = nombre
        self.apellidos = apellidos
        self.direccion = direccion
        self.codigoPostal = codigoPostal
        self.provincia = obtenerProvincia(codigoPostal)
        self.ciudad = ciudad
        self.telefono = telefono
        self.email = email
        self.descripcion = descripcion
    
    def to_file(self) ->str:
        valores = [
            self.dni,
            self.nombre,
            self.apellidos,
            self.direccion,
            self.codigoPostal,
            self.ciudad,
            self.telefono,
            self.email,
            self.descripcion.value
        ]
        return ";".join(map(str,valores))

class EstadoPersona(Enum):
    ACTIVO = "activo"
    ALTA = "alta"
    BAJA = "baja"
    AMIGO = "amigo"
    CONOCIDO = "conocido"

# Funciones de persistencia
def guardar_cambios():
    with open(FICHERO_PERSONAS, "w") as f:
        for persona in personas:
            f.write(persona.to_file() + "\n")

def leer_fichero():
    personas.clear()
    try:
        with open(FICHERO_PERSONAS, "r", encoding="utf-8") as f:
            for linea in f:
                partes = linea.strip().split(";")
                if len(partes) == 9:
                    print(len(partes), partes)
                    #TODO Arreglar leerFichero
    except FileNotFoundError:
        print(f"Archivo '{FICHERO_PERSONAS}' no encontrado.")

def comprobarDNI(dni: str) -> bool:
    if not dni:
        return False

    dni = dni.strip().upper()

    if len(dni) != 9:
        return False

    letras = "TRWAGMYFPDXBNJZSQVHLCKE"
    letrasNIE = {"X": "0", "Y": "1", "Z": "2"}

    numero = dni[:-1]
    letra = dni[-1]

    if not letra.isalpha():
        return False

    # NIE
    if numero[0].isalpha():
        if numero[0] not in letrasNIE:
            return False
        numero = letrasNIE[numero[0]] + numero[1:]

    if not numero.isdigit():
        return False

    return letras[int(numero) % 23] == letra 

def existeDNI(dni) -> bool:
    return any(persona.dni == dni for persona in personas)

def is_valid_email(email):

    """Check if the email is a valid format."""

    # Regular expression for validating an Email

    regex = r'^[a-z0-9]+[\._]?[a-z0-9]+[@]\w+[.]\w+$'

    # If the string matches the regex, it is a valid email

    if re.match(regex, email):
        return True
    else:
        return False

def obtener_descripcion():
    while True:
        print("Seleccione descripcion:")
        for i,estado in enumerate(EstadoPersona,1):
            print(f"\t{i}. {estado.value.capitalize()}")
        num = input("-> ").strip()
        if not num.isdigit():
            continue
        num = int(num)
        if num < 1 or num > len(EstadoPersona):
            continue
        return list(EstadoPersona)[num - 1]

def agregarPersona() -> None:
    dni = None
    codigoPostal = None
    while True:
        dni = input("Introduzca el DNI: ").strip()
        if not comprobarDNI(dni):
            print("DNI no es válido")
            continue
        if existeDNI(dni):
            print("DNI ya existe")
            continue
        break
    nombre = input("Nombre: ").strip()
    apellidos = input("Apellidos: ").strip()
    direccion = input("Direccion: ").strip()
    
    while True:
        codigoPostal = input("Codigo Postal: ").strip()
        if not verificarCP(codigoPostal):
           print("Codigo postal no es valido")
           continue
        break    

    ciudad = input("Ciudad: ").strip()
    telefono = input("Telefono: ").strip()
    
    while True:
        email = input("Email: ").strip()
        if not is_valid_email(email):
            print("Email no es valido")
            continue
        break
    descripcion = obtener_descripcion()
    personas.append(Persona(
        dni, nombre, apellidos, direccion,
        codigoPostal, ciudad, telefono,
        email, descripcion
        )
    )
    guardar_cambios()


leer_fichero()
if __name__ == "__main__":
    agregarPersona()

