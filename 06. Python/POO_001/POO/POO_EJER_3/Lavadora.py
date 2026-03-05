from POO.POO_EJER_3.Electrodomestico import Electrodomestico

class Lavadora(Electrodomestico):

    CARGA_DEFECTO = 5

    def __init__(self, precioBase=Electrodomestico.PRECIO_BASE_DEFECTO,
                 peso=Electrodomestico.PESO_DEFECTO,
                 color=Electrodomestico.COLOR_DEFECTO,
                 consumoEnergetico=Electrodomestico.CONSUMO_DEFECTO,
                 carga=CARGA_DEFECTO):

        super().__init__(precioBase, peso, color, consumoEnergetico)
        self.carga = carga

    def getCarga(self):
        return self.carga

    def precioFinal(self):

        precio = super().precioFinal()

        if self.carga > 30:
            precio += 50

        return precio

