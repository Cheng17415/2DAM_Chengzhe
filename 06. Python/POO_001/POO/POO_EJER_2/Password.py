import random
import string
class Password():
    def __init__(self, longitud: int = 8, contrasena: str = ""):
        """Constructor de la clase."""
        self.__longitud = longitud
        self.__contrasena = contrasena if contrasena != "" else self.generarPassword(longitud)

    def esFuerte(self):
        mayu = sum(c.isupper() for c in self.__contrasena)
        minu = sum(c.islower() for c in self.__contrasena)
        num = sum(c.isdigit() for c in self.__contrasena)
        return mayu > 2 and minu > 1 and num > 5

    def generarPassword(self, longitud):
        pool = string.ascii_letters + string.digits
        return ''.join(random.choice(pool) for _ in range(longitud))

    def getPassword(self):
        return self.__contrasena

    def getLongitud(self):
        return self.__longitud

    def setLongitud(self, longitud: int):
        self.__longitud = longitud

if __name__ == "__main__":
    cantidadPass = int(input("Numero de contrasenas a introducir: "))
    passwords = []
    fuerte = []
    longitud = int(input("Longitud: "))

    for i in range(cantidadPass):
        print(f"Password {i+1}")
        contrasena = input("Contrasena: ")
        password = Password(longitud, contrasena)
        passwords.append(password)
        fuerte.append(password.esFuerte())

    for i in range(len(passwords)):
        print(f"{passwords[i].getPassword()} {fuerte[i]}")