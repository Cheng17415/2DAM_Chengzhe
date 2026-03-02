from abc import ABC, abstractmethod
import math
class FG(ABC):
    def __init__(self, lado1: float):
        #self._lado1 atributo protected
        #self.__lado1 atributo private
        self.lado1 = lado1

    def __str__(self):
        return f'FG({self.lado1:.2f})'

    @abstractmethod
    def calcular_perimetro(self) -> float:
        pass

    @abstractmethod
    def calcular_area(self) -> float:
        pass

class Cuadrado(FG):

    def calcular_perimetro(self) -> float:
        return self.lado1*4

    def calcular_area(self) -> float:
        return math.pow(self.lado1,2)

    def __str__(self):
        return f'Cuadrado({self.lado1:.2f})'

class Rectangulo(FG):

    def __init__(self, lado1: float, lado2: float):
        super().__init__(lado1)
        self.lado2 = lado2

    def calcular_perimetro(self) -> float:
        return 2*(self.lado1 + self.lado2)

    def calcular_area(self) -> float:
        return self.lado1*self.lado2

    def __str__(self):
        return f'Recangulo({self.lado1:.2f}, {self.lado2:.2f})'

class Cubo(Cuadrado):

    def calcular_perimetro(self) -> float:
        return super().calcular_perimetro() * 3

    def calcular_area(self) -> float:
        return super().calcular_area() * 6

    def calcular_volumen(self) -> float:
        return math.pow(self.lado1,3)

    def __str__(self):
        return f'Cubo({self.lado1:.2f})'