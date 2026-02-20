import re

def is_valid_email(email):

    """Check if the email is a valid format."""

    regex = r'^[a-z0-9]+[\._]?[a-z0-9]+[@]\w+[.]\w+$'

    if re.match(regex, email):
        return True
    else:
        return False

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

