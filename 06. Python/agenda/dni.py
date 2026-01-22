import os
# Ruta del archivo de dni
RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
RUTA_CP = os.path.join(RUTA_ACTUAL, "base_datos/dni.txt")
DNIs = {}
def obtenerDNIs():
    DNIs.clear()
    try:
        with open(RUTA_CP, "r", encoding="utf-8") as f:
            for linea in f:
                partes = linea.strip().split(";")
                DNIs[int(partes[0])] = partes[1]
    except FileNotFoundError:
        print(f"Archivo '{RUTA_CP}' no encontrado.")

def obtener_DNI(id):
    return DNIs.get(id)

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
    return any(dni == v for v in DNIs.values())
  
'''def existeDNI(dni) -> bool:
return any(persona.dni == dni for persona in personas)'''
obtenerDNIs()
if __name__ == "__main__":
  print("Has iniciado el proyecto en dni.py")