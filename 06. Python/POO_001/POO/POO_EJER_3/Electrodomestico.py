from enum import Enum

class ConsumoEnergetico(Enum):
    A = 100
    B = 80
    C = 60
    D = 50
    E = 30
    F = 10

class Electrodomestico:

    COLOR_DEFECTO = "blanco"
    CONSUMO_DEFECTO = ConsumoEnergetico.F
    PRECIO_BASE_DEFECTO = 100
    PESO_DEFECTO = 5

    COLORES_DISPONIBLES = ["blanco", "negro", "rojo", "azul", "gris"]

    def __init__(self, precioBase=PRECIO_BASE_DEFECTO, peso=PESO_DEFECTO,
                 color=COLOR_DEFECTO, consumoEnergetico=CONSUMO_DEFECTO):

        self.precioBase = precioBase
        self.peso = peso
        self.color = self.comprobarColor(color)
        self.consumoEnergetico = self.comprobarConsumoEnergetico(consumoEnergetico)

    # getters
    @property
    def precio(self):
        return self.precioBase

    @precio.setter
    def precio(self, precio):
        self.precioBase = precio

    @property
    def color(self):
        return self.color

    @color.setter
    def color(self, color):
        self.color = color

    @property
    def consumoEnergetico(self):
        return self.consumoEnergetico
    @consumoEnergetico.setter
    def consumoEnergetico(self, consumoEnergetico):
        self.consumoEnergetico = consumoEnergetico

    @property
    def peso(self):
        return self.peso

    @peso.setter
    def peso(self, peso):
        self.peso = peso

    def comprobarConsumoEnergetico(self, consumo):
        if isinstance(consumo, ConsumoEnergetico):
            return consumo
        return self.CONSUMO_DEFECTO

    def comprobarColor(self, color):
        color = color.lower()
        if color in self.COLORES_DISPONIBLES:
            return color
        return self.COLOR_DEFECTO

    def precioFinal(self):

        precio = self.precioBase

        # aumento por consumo (usamos el enum)
        precio += self.consumoEnergetico.value

        # aumento por peso
        match True:
            case _ if self.peso >= 80:
                precio += 100
            case _ if self.peso >= 50:
                precio += 80
            case _ if self.peso >= 20:
                precio += 50
            case _:
                precio += 10

        return precio

if __name__ == "__main__":
    ...