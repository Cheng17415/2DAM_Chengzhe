from codigos_postales import obtenerProvincia,verificarCP
from dni import obtener_DNI, comprobarDNI,existeDNI, anadir_o_cambiar_DNI, eliminarDNI, obtenerDNIs
import dni
from enum import Enum
import os
import re
# Ruta del archivo de personas
RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
FICHERO_PERSONAS = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")

# Ensure DNIs are loaded before accessing them
obtenerDNIs()

personas = []

class Persona:
    #ID global de las personas
    cont_id= 1
    #Constructor
    def __init__(self, nombre, apellidos, direccion, codigoPostal, ciudad, telefono, email, descripcion,
                 id=None):
        if id is None:
            self.id = Persona.cont_id
        else:
            self.id = int(id)
        Persona.cont_id = max(Persona.cont_id, self.id + 1)
        self.nombre = nombre
        self.apellidos = apellidos
        self.direccion = direccion
        self.codigoPostal = codigoPostal
        self.provincia = obtenerProvincia(codigoPostal)
        self.ciudad = ciudad
        self.telefono = telefono
        self.email = email
        try:
            self.descripcion = EstadoPersona(descripcion)
        except ValueError:
            print(f"Descripción desconocida: {descripcion}. Se asignará 'ACTIVO'.")
            self.descripcion = EstadoPersona.ACTIVO
    
    def to_file(self) ->str:
        valores = [
            self.id,
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
    
    def to_str(self) -> str:
        dni = obtener_DNI(self.id)
        if dni is None:
            print(f"Debug: DNI no se ha encontrado {self.id}")
        return (
        f"DNI: {dni}\n"
        f"Nombre: {self.nombre} {self.apellidos}\n"
        f"Dirección: {self.direccion}\n"
        f"Código Postal: {self.codigoPostal} ({self.provincia})\n"
        f"Ciudad: {self.ciudad}\n"
        f"Teléfono: {self.telefono}\n"
        f"Email: {self.email}\n"
        f"Descripción: {self.descripcion.value.capitalize()}\n"
        f"{'-'*40}"
        )

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
        with open(FICHERO_PERSONAS, "r", encoding="latin-1") as f:
            for linea in f:
                partes = linea.strip().split(";")
                if len(partes) == 9:
                    try:
                        # 1. Extraer los datos por posición según el archivo
                        id_fichero = partes[0]
                        nombre = partes[1]
                        apellidos = partes[2]
                        direccion = partes[3]
                        cp = partes[4]
                        ciudad = partes[5]
                        tel = partes[6]
                        email = partes[7]
                        
                        # 2. Validar el Enum
                        try:
                            estado = EstadoPersona(partes[8])
                        except ValueError:
                            print(f"Estado desconocido: {partes[8]}. Se asignará 'ACTIVO'.")
                            estado = EstadoPersona.ACTIVO
                        
                        # 3. Crear la instancia con los argumentos en el orden de __init__
                        persona_nueva = Persona(
                            nombre, apellidos, direccion, cp, ciudad, 
                            tel, email, estado, id=id_fichero
                        )
                        personas.append(persona_nueva)
                        
                    except Exception as e:
                        print(f"Error procesando línea: {e}")
    except FileNotFoundError:
        print(f"Archivo '{FICHERO_PERSONAS}' no encontrado.")



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

def pedir_con_validacion(mensaje, funcion_validadora, mensaje_error):
    '''1º arg es el mensaje inicial;
    2º arg es la funcion utilizada para validar;
    3º arg es el mensaje cuando no es exitoso la validacion'''
    while True:
        valor = input(mensaje).strip()
        if funcion_validadora(valor):
            return valor
        print(mensaje_error)
        
def agregarPersona() -> None:
    dni = pedir_con_validacion(
        "Introduzca el DNI: ",
        lambda d: comprobarDNI(d) and not existeDNI(d),
        "DNI no es válido o ya existe"
    )
    nombre = input("Nombre: ").strip()
    apellidos = input("Apellidos: ").strip()
    direccion = input("Direccion: ").strip()
    codigoPostal = pedir_con_validacion(
        "Codigo Postal: ",
        verificarCP,
        "Codigo postal no es valido"
    )
    ciudad = input("Ciudad: ").strip()
    telefono = input("Telefono: ").strip()
    email = pedir_con_validacion(
        "Email: ",
        is_valid_email,
        "Email no es valido"
    )
    descripcion = obtener_descripcion()
    persona = Persona(
        nombre, apellidos, direccion,
        codigoPostal, ciudad, telefono,
        email, descripcion
    )
    personas.append(persona)

    # Add the new DNI to the DNIs dictionary
    anadir_o_cambiar_DNI(persona.id, dni)

    print("Persona añadida con éxito!")
    guardar_cambios()

def leer_lista_personas():
    if not personas:
        print("No hay personas registradas.")
        return

    print("\nLISTADO DE PERSONAS")
    print("=" * 40)

    for persona in personas:
        print(persona.to_str())

def modificar_persona():
    dni_buscar = input("Introduce el DNI/NIE de la persona a modificar: ").upper()

    if not comprobarDNI(dni_buscar):
        print("DNI/NIE inválido")
        return

    if not existeDNI(dni_buscar):
        print("No existe ninguna persona con ese DNI")
        return

    # Buscar el ID a partir del DNI
    id_persona = None
    for key, value in dni.DNIs.items():
        if value == dni_buscar:
            id_persona = key
            break

    if id_persona is None:
        print("No se encontró el DNI en el sistema")
        return

    for persona in personas:
        if persona.id == id_persona:
            print("\nPersona encontrada:")
            print(persona.to_str())

            print("\n--- Introduce los nuevos datos (ENTER para mantener) ---")

            nuevo_nombre = input(f"Nombre ({persona.nombre}): ")
            if nuevo_nombre:
                persona.nombre = nuevo_nombre

            nuevos_apellidos = input(f"Apellidos ({persona.apellidos}): ")
            if nuevos_apellidos:
                persona.apellidos = nuevos_apellidos

            nueva_direccion = input(f"Dirección ({persona.direccion}): ")
            if nueva_direccion:
                persona.direccion = nueva_direccion

            nuevo_telefono = input(f"Teléfono ({persona.telefono}): ")
            if nuevo_telefono:
                persona.telefono = nuevo_telefono

            nuevo_email = input(f"Email ({persona.email}): ")
            if nuevo_email:
                persona.email = nuevo_email

            guardar_cambios()
            print("Persona modificada correctamente")
            return


def eliminar_persona():
    dni = input("Introduce el DNI/NIE de la persona a eliminar: ").upper()

    if not existeDNI(dni):
        print("No existe ninguna persona con ese DNI")
        return
    
    for persona in personas:
        if obtener_DNI(persona.id) == dni:
            print("\nPersona encontrada:")
            print(persona.to_str())

            confirmacion = input("¿Seguro que deseas eliminarla? (s/n): ").lower()

            if confirmacion == "s":
                personas.remove(persona)
                guardar_cambios()
                eliminarDNI(dni)
                print("Persona eliminada correctamente")
            else:
                print("Operación cancelada")
            return

    print("No se encontró ninguna persona con ese DNI")


leer_fichero()
if __name__ == "__main__":
    agregarPersona()
    leer_lista_personas()

