from clases1 import Cuadrado, Rectangulo, Cubo
def imprimir():
    global lista
    for item in lista:
        print(f'{item} area= {item.calcular_area():.2f} perimetro = {item.calcular_perimetro():.2f} volumen= {item.calcular_volumen():.2f}') if isinstance(item,Cubo) else print(f'{item} area= {item.calcular_area():.2f} perimetro = {item.calcular_perimetro():.2f}')

def rellenar():
    global lista
    lista = [Cuadrado(5), Cuadrado(10), Cuadrado(15),
             Rectangulo(10.50,20.60),
             Rectangulo(20.5,40.6),
             Rectangulo(30.50, 50.60),
             Cubo(10), Cubo(20)]

#Metodo que acceda a la lista y decir la cantidad de rect cuadr y cubo
def contar(li):
    c = [["Cuadrado", 0], ["Rectangulo", 0], ["Cubo", 0]]
    for item in li:
        match item.__class__.__name__:
            case "Cubo":
                c[2][1] += 1
            case "Rectangulo":
                c[1][1] += 1
            case "Cuadrado":
                c[0][1] += 1
    for i in range(len(c)):
        print(f'{c[i][0]:10s} {c[i][1]:4d}')

def contar2(li):
    c = {"Cuadrado": 0, "Rectangulo": 0, "Cubo": 0}
    for item in li:
        c[item.__class__.__name__] += 1

    for k,v in c.items():
        print(f'{k:10s} {v:4d}')
if __name__ == '__main__':
    cuad = Cuadrado(5)
    print(cuad)
    print(f'Perimetro: {cuad.calcular_perimetro():.2f}')
    print(f'Area: {cuad.calcular_area()}')
    lista = []
    rellenar()
    imprimir()
    contar(lista)
    contar2(lista)