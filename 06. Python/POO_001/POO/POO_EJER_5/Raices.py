import math


class Raices:
    def __init__(self, a = 0,b  = 0,c = 0):
        self.__a = a
        self.__b = b
        self.__c = c

    def getDiscriminante(self):
        return math.pow(self.__b,2) - (4 * self.__a * self.__c)

    def tieneRaices(self):
        return self.getDiscriminante() > 0

    def tieneRaiz(self):
        return self.getDiscriminante() == 0

    def obtenerRaices(self):
        raiz1 = (-self.__b + math.sqrt(self.getDiscriminante()))/(2 * self.__a)
        raiz2 = (-self.__b - math.sqrt(self.getDiscriminante()))/(2 * self.__a)
        return raiz1, raiz2

    def obtenerRaiz(self):
        return (-self.__b + math.sqrt(self.getDiscriminante()))/(2 * self.__a)

    def calcular(self):
        if self.tieneRaices():
            raiz1, raiz2 = self.obtenerRaices()
            print(f"a = {self.__a}, b = {self.__b}, c = {self.__c}")
            print(f"raiz1 = {raiz1}")
            print(f"raiz2 = {raiz2}")
        elif self.tieneRaiz():
            raiz1 = self.obtenerRaiz()
            print(f"a = {self.__a}, b = {self.__b}, c = {self.__c}")
            print(f"raiz1 = {raiz1}")
        else:
            print(f"a = {self.__a}, b = {self.__b}, c = {self.__c}")
            print("No tiene solucion real")

if __name__ == "__main__":
    r = Raices(-1,4,90)
    r.calcular()
