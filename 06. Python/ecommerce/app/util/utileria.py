import re
import getpass
import bcrypt
from app.db.database import SessionLocal
from app.models.usuario import Usuario
from app.service.usuario_service import existe_dni

def comprobarDNI(dni: str) -> bool:
    if not dni:
        return False

    dni = dni.strip().upper()

    if existe_dni(dni):
        return False
    
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
    
def comprobarEmail(email: str) -> bool:
    # Expresión regular básica para email
    patron = r"^[\w\.-]+@[\w\.-]+\.\w+$"

    if not re.match(patron, email):
        print("Error en el regex")
        return False

    session = SessionLocal()
    try:
        existe = session.query(Usuario).filter(
            Usuario.email == email
        ).first()
        return existe is None
    finally:
        session.close()

def pedir_con_validacion(mensaje, funcion_validadora, mensaje_error):
    '''1º arg es el mensaje inicial;
    2º arg es la funcion utilizada para validar;
    3º arg es el mensaje cuando no es exitoso la validacion'''
    while True:
        valor = input(mensaje).strip()
        if funcion_validadora(valor):
            return valor
        print(mensaje_error)

def pedir_no_vacio(mensaje):
    while True:
        valor = input(mensaje).strip()
        if valor:
            return valor
        print("El campo no puede estar vacio.")
        
def pedir_contrasena(mensaje):
    while True:
        #Contrasena para que no se vea en la terminal
        contrasena = getpass.getpass(mensaje)
        if len(contrasena) < 5:
            print("La contrasena debe tener al menos 5 caracteres.")
            continue
        repetir_contrasena = getpass.getpass("Confirme la contrasena: ")
        if contrasena!= repetir_contrasena:
            print("Las contrasenas no coinciden")
            continue
        
        return contrasena

def hash_contrasena(contrasena):
    contrasena_bytes = contrasena.encode("utf-8")
    hashed = bcrypt.hashpw(contrasena_bytes, bcrypt.gensalt())
    return hashed.decode("utf-8")

def verificar_contrasena(contrasena, hash_guardado):
    return bcrypt.checkpw(contrasena.encode("utf-8"), hash_guardado.encode("utf-8"))