from enum import Enum
from util import obtenerLetra, generarNumero
class TipoSexo(Enum):
    HOMBRE = "H"
    MUJER = "M"

class Persona:
    def __init__(self,nombre: str = "", edad: int = 0, sexo: str = TipoSexo.HOMBRE.value, peso: float = 0, altura: float = 0):
        self.__nombre = nombre
        self.__edad = edad
        self.__DNI = self.__generaDNI()
        self.__sexo = sexo
        self.__peso = peso
        self.__altura = altura

    def calcularIMC(self):
        if self.__altura <= 0:
            return 0
        ideal = self.__peso/ pow(self.__altura, 2)
        return -1 if ideal < 20 else 0 if 20 <= ideal <= 25 else 1

    def esMayorDeEdad(self):
        return self.__edad >= 18

    def __comprobarSexo(self, sexo):
        if not (sexo == TipoSexo.MUJER.value or sexo == TipoSexo.HOMBRE.value):
            self.__sexo = TipoSexo.HOMBRE.value

    def __str__(self):
        return f"Persona({self.__DNI},{self.__nombre},{self.__edad} ,{self.__sexo},{self.__peso},{self.__altura})"

    @staticmethod
    def __generaDNI():
        dni = ''.join(str(generarNumero()) for _ in range(8))
        letra = obtenerLetra(dni)
        dni += letra
        return dni

    def setNombre(self, nombre: str):
        self.__nombre = nombre

    def setEdad(self, edad: int):
        self.__edad = edad

    def setSexo(self, sexo):
        self.__sexo = sexo
        self.__comprobarSexo(sexo)

    def setPeso(self, peso: float):
        self.__peso = peso

    def setAltura(self, altura: float):
        self.__altura = altura

def mensajeIMC(num):
    if num == -1:
        print("Estas en tu peso ideal")
    elif num == 0:
        print("Estas por debajo de tu peso ideal")
    elif num == 1:
        print("Estas en sobrepeso")

if __name__ == '__main__':
    nombre = input("Introduzca nombre: ")
    edad = int(input("Introduzca edad: "))
    sexo = input("Introduzca sexo(H/M): ")
    peso = float(input("Introduzca peso: "))
    altura = float(input("Introduzca altura: "))

    p1 = Persona(nombre, edad, sexo, peso, altura)
    p2 = Persona(nombre, edad, sexo)
    p3 = Persona()
    p3.setNombre("Cheng")
    p3.setEdad(22)
    p3.setSexo(TipoSexo.HOMBRE)
    p3.setPeso(65.5)
    p3.setAltura(1.75)

    personas = [p1, p2, p3]
    for p in personas:
        mensajeIMC(p.calcularIMC())
        print("Es mayor de edad") if p.esMayorDeEdad() else print("No es mayor de edad")
        print(p)

