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
            self.descripcion
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
        with open(FICHERO_PERSONAS, "r",encoding="utf-8") as f:
            for linea in f:
                partes = linea.strip().split(";")
                if len(partes) == 9:
                    personas.append(Persona(*partes))
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
    valido = False
    while not valido:
        print("Seleccione descripcion:")
        for i,estado in enumerate(EstadoPersona,1):
            print(f"\t{i}. {estado.value.capitalize()}")
        num = input("-> ").strip()
        if num.isdigit():
            valido = True
            ...

def agregarPersona() -> None:
    valido = False
    dni = None
    codigoPostal = None
    while not valido:
        dni = input("Introduzca el DNI: ").strip()
        valido = True
        if not comprobarDNI(dni):
            print("DNI no es válido")
            valido = False
        if existeDNI(dni):
            print("DNI ya existe")
            valido = False
    nombre = input("Nombre: ").strip()
    apellidos = input("Apellidos: ").strip()
    direccion = input("Direccion: ").strip()
    
    valido = False
    
    while not valido:
        codigoPostal = input("Codigo Postal: ").strip()
        if verificarCP(codigoPostal):
           valido = True
        else:
            print("Codigo postal no es valido")

    ciudad = input("Ciudad: ").strip()
    telefono = input("Telefono: ").strip()
    
    valido = False
    while not valido:
        email = input("Email: ").strip()
        if is_valid_email(email):
            valido = True
        else:
            print("Email no es valido")
    
    descripcion = obtener_descripcion()

leer_fichero()
if __name__ == "__main__":
    obtener_descripcion()

