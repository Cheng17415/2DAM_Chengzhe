import random


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

def obtenerLetra(dni: str) -> str:
    if not dni:
        return ""

    if len(dni) != 8:
        return ""

    letras = "TRWAGMYFPDXBNJZSQVHLCKE"

    numero = dni[:]

    if not numero.isdigit():
        return ""

    return letras[int(numero) % 23]

def generarNumero() -> int:
    return random.randint(0,9)
