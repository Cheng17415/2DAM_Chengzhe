import os
# Ruta del archivo de dni
RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
FECHERO_DNI = os.path.join(RUTA_ACTUAL, "base_datos/dni.txt")
DNIs = {}

def obtenerDNIs():
    DNIs.clear()
    try:
        with open(FECHERO_DNI, "r", encoding="utf-8") as f:
            for linea in f:
                partes = linea.strip().split(";")
                DNIs[int(partes[0])] = partes[1]
    except FileNotFoundError:
        print(f"Archivo '{FECHERO_DNI}' no encontrado.")

def guardar_cambios_dni():
    try:
        with open(FECHERO_DNI, "w", encoding="utf-8") as f:
            total_items = len(DNIs)
            for i, (k, v) in enumerate(DNIs.items()):
                f.write(f"{k};{v}")
                if i < total_items - 1:
                    f.write("\n")
    except IOError as e:
        print(f"Error al guardar los cambios en el archivo '{FECHERO_DNI}': {e}")

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

def obtener_DNI(id):
    return DNIs.get(id)

def existeDNI(dni) -> bool:
    return any(dni == v for v in DNIs.values())

def anadir_o_cambiar_DNI(id, dni):
    DNIs[int(id)] = dni
    guardar_cambios_dni()

def eliminarDNI(id):
    del DNIs[int(id)]
    guardar_cambios_dni()
    
obtenerDNIs()
if __name__ == "__main__":
  print("Has iniciado el proyecto en dni.py")