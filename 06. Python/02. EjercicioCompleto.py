import math

opciones = ("1. Ejercicio 1 - Calcular fuerza", "2. Ejercicio 2 - Calcular areas","3. Ejercicio 3 - Convertidor Barn - m²","4. Ejercicio 4 - Calcular media de 3 alumnos",
            "5. Ejercicio 5 - Calcular TIEA","6. Ejercicio 6 - Calcular radianes a grados","7. Ejercicio 7 - Segundos desde ultima medianoche",
            "8. Ejercicio 8 - Mostrar equivalencias de Km","9. Ejercicio 9 - ¿Esta en la circunferencia?","10. Ejercicio 10 - Calcular pintas",
            "11. Ejercicio 11 - Tabla de multiplicar","12. Ejercicio 12 - Convertirdor de temperatura","13. Ejercicio 13 - Distancia entre dos puntos",
            "14. Ejercicio 14 - Operaciones con numeros complejos","15. Ejercicio 15 - ¿Es bisiesto?", "16. Salir")

def menu(titulo,opciones):
    ESPACIOS_INICIO = " " * 10
    MAX_WIDTH = 30

    print(ESPACIOS_INICIO + "┌" + "─"* MAX_WIDTH + "┐")
    print(ESPACIOS_INICIO + "│" + "  "* MAX_WIDTH + "│")
    
    print(ESPACIOS_INICIO + "│" + titulo.center(MAX_WIDTH * 2) + "│")
    print(ESPACIOS_INICIO + "├" + "─"* MAX_WIDTH + "┤")
    print(ESPACIOS_INICIO + "│" + "  "* MAX_WIDTH + "│")
    
    for opcion in opciones:
        print(ESPACIOS_INICIO +"│\t" + opcion.ljust(MAX_WIDTH * 2 - 4) + "│")

    print(ESPACIOS_INICIO + "│" + "  "* MAX_WIDTH + "│")
    print(ESPACIOS_INICIO + "└" + "─"* MAX_WIDTH + "┘")
    return int(input("\n\tElija una opcion: "))
        

########################################################

def fuerza(masa, aceleracion):
    """ float, float --> float
        OBJ: A partir de la masa y aceleración, calcular la fuerza
        PRE: masa >= 0
    """
    return masa * aceleracion

def ejercicio1():
    print("\n EJERCICIO 1: Calcular fuerza")
    print("--------------------------------------------------")
    print("A partir de la masa y la aceleracion, calcular la fuerza con la formula F = m * a\n")
    masa = int(input("Dime la masa del objeto (kg): "))
    aceleracion = int(input("Dime la aceleracion del objeto (m/s²): "))
    print(f"La fuerza es de {fuerza(masa,aceleracion):.2f}N")

########################################################
    
def area_circulo(radio):
    """ float --> float
        OBJ: A partir del radio, calcular el area del circulo
        PRE: radio >=0
    """
    return radio **2 * math.pi

def area_cuadrado(lado):
    """ float --> float
        OBJ: A partir del lado, calcular el area del cuadrado
        PRE: lado >=0
    """
    return lado ** 2

def area_triangulo(base, altura):
    """ float, float--> float
        OBJ: A partir de la base y altura, calcular el area del triangulo
        PRE: base >=0, altura >= 0
    """
    return (base * altura)/2

def ejercicio2():
    print("\n EJERCICIO 2: Calcular areas")
    print("--------------------------------------------------")
    print("Calcular el area de circulo, cuadrado y triangulo\n")
    radio = float(input("Radio del circulo: "))
    print(f"Area del circulo: {area_circulo(radio):.2f}\n")
    
    lado = float(input("Lado del cuadrado: "))
    print(f"Area del cuadrado: {area_cuadrado(lado):.2f}\n")

    base = float(input("Base del triangulo: "))
    altura = float(input("Altura del triangulo: "))
    print(f"Area del triangulo: {area_triangulo(base,altura):.2f}\n")

########################################################

def mc_a_barn(m2):
    """ float --> float
        OBJ: A partir de los metros cuadrados, calcula los barn correspondientes
        PRE: m2 >=0
    """
    return m2 * math.pow(10,28)

def barn_a_mc(barn):
    """ float --> float
        OBJ: A partir de barn, calcula los m2 correspondientes
        PRE: barn >=0
    """
    return barn / math.pow(10,28)

def ejercicio3():
    print("\n EJERCICIO 3: Convertidor Barn - m²")
    print("--------------------------------------------------\n")
    
    opciones = ["1. m² a barn","2. Barn a m²"]
    opc = menu("¿Que quieres convertir?",opciones)
    
    if opc == 1:
        cant = float(input("Introduce m² a convertir a barn: "))
        print(f"{cant} m² = {mc_a_barn(cant):.2e} barn")
    elif opc ==2:
        cant = float(input("Introduce barn a convertir a m²: "))
        print(f"{cant} barn = {barn_a_mc(cant):.2e} m²")


########################################################
        
def calcularMedia(*args):
    """ *float --> float
    OBJ: Calcular la media pasandole args de tipo float
    """
    cont = 0
    suma = 0
    
    for num in args:
        suma += num
        cont += 1    
    return suma / cont

def ejercicio4():
    print("\n EJERCICIO 4: Calcular media de 3 alumnos")
    print("--------------------------------------------------\n")
    
    nota1 = float(input("Introduce la nota del primer alumno "))
    nota2 = float(input("Introduce nota del segundo alumno "))
    nota3 = float(input("Introduce la nota del tercer alumno "))
    print(f"La nota media es de {calcularMedia(nota1,nota2,nota3):.2f}")
    
########################################################
    
def calcularTIEA(tna, m):
    """ float, int --> float
    OBJ: Calcular la TIEA a partir de TNA y periodo de capitalizacion
    """
    return ((1 + tna/m)**m - 1) * 100
    
def ejercicio5():
    print("\n EJERCICIO 5: Calcular TIEA")
    print("--------------------------------------------------")
    print("Calcular TIEA a partir de TNA en decimal y el num de periodos de capitalizacion por anio\n")
    
    tna = float(input("Introduce la TNA en decimal: "))
    m = int(input("Introduce el numero de periodos de capitalizacion por anio: "))
    print(f"TIEA = {calcularTIEA(tna,m):.2f} %")
    
########################################################
    
def rad_en_grad(rad):
    """ float --> float
    OBJ: Calcular los grados a partir de radianes
    """
    import math
    return  (180/math.pi) * rad
    
def ejercicio6():
    print("\n EJERCICIO 6: Calcular radianes a grados")
    print("--------------------------------------------------\n")
    
    rad = float(input("Introduzca radianes: "))
    print(f"{rad:.2f} rad = {rad_en_grad(rad):.2f}º")
    
########################################################
    
def seg_medianoche(tiempo):
    """tuple o list --> int
    OBJ: Calcular los segundos desde la ultima medianoche a partir de horas,
        minutos y segundos dadas en una tupla o lista
         
    """
    return 3600 * tiempo[0] + 60 * tiempo[1] + tiempo[2]

def ejercicio7():
    print("\n EJERCICIO 7: Calcular segundos desde ultima medianoche")
    print("--------------------------------------------------")
    print("A partir de HH,MM,SS que proporciona el usuario, \ncalcular los segundos transcurridos desde la ultima medianoche\n")
    
    hora = int(input("Introduce las horas: "))
    minuto = int(input("Introduce los minutos: "))
    segundos = int(input("Introduce los segundos: "))
    tiempo = (hora,minuto,segundos)
    print(f"Han pasado {seg_medianoche(tiempo)} segundos desde la ultima medianoche")
    
########################################################
    
def km_a_Hm(km):
    return km * 10

def km_a_Dm(km):
    return km * 10000

def km_a_m(km):
    return km* 1000

def ejercicio8():
    print("\n EJERCICIO 8: Mostrar equivalencias de Km")
    print("--------------------------------------------------")
    print("A partir de unos Km introducidos, mostrar sus equivalencias en Hm, Dm y m\n")
    
    km = int(input("Introduzca los Km a convertir: "))
    print(f"\n{km} Km =\n\t{km_a_Hm(km)} Hm\n\t{km_a_Dm(km)} Dm\n\t{km_a_m(km)} m")
    
########################################################
    
def coord_sobre_circ(x,y):
    """float, float -> boolean
    OBJ: Determinar si 'x' e 'y' estan sobre la circunferencia x² + y² = 1000
    """
    if x**2 + y** 2 == 1000:
        return True
    return False

def ejercicio9():
    print("\n EJERCICIO 9: ¿Esta en la circunferencia?")
    print("--------------------------------------------------")
    print("Utilizando unas coordenadas x e y, determinar si esta sobre la circunferencia x² + y² = 1000\n")
    
    x = float(input("Coordenadas del punto en el eje x: "))
    y = float(input("Coordenadas del punto en el eje y: "))
    
    if coord_sobre_circ(x,y):
        print(f"El punto ({x},{y}) pertenece a la circunferencia")
    else:
        print(f"El punto ({x},{y}) no pertenece a la circunferencia")
        
########################################################

def calc_pintas(mili):
    """ float ->float
    OBJ: Calcular pintas a partir de ml
    
    """
    return mili/473.176473

def ejercicio10():
    print("\n EJERCICIO 10: Calcular pintas")
    print("--------------------------------------------------")
    print("A partir de unos mililitros dados, calcular pintas\n")
    
    mili  = float(input("Introduzca mililitros: "))
    print(f"{mili} ml = {calc_pintas(mili):.2f} pt")
    
########################################################

def tabla_multiplicar(num):
    """ int -> null
    OBJ: Mostrar por pantalla la tabla de multiplicar del numero introducido

    """
    print(f"\nTabla de multiplicar de {num}:")
    for i in range(10):
        print(f"\t{num} * {i+1} = {num * (i + 1)}")
        
def ejercicio11():
    print("\n EJERCICIO 11: Tabla de multiplicar")
    print("--------------------------------------------------")
    print("A partir de un numero entero, mostrar su tabla de multiplicar\n")
    
    num  = int(input("Introduzca numero entero: "))
    tabla_multiplicar(num)
    
########################################################

def cent_a_fahr(TC):
    """ float -> float
    OBJ: Convierte los grados centigrados a Fahrenheit
    """
    return 9 * TC / 5 + 32

def cent_a_K(TC):
    """ float -> float
    OBJ: Convierte los grados centigrados a Kelvin
    """
    return TC + 273.15

def ejercicio12():
    print("\n EJERCICIO 12: Convertirdor de temperatura")
    print("--------------------------------------------------")
    print("A partir de unos grados centigrados, calcular ºF y K\n")
    
    TC  = float(input("Introduzca grados centigrados: "))
    print(f"{TC} ºC = {cent_a_fahr(TC):.2f} ºF = {cent_a_K(TC):.2f} K")
    
########################################################
    
def distancia_dos_puntos(punto1, punto2):
    """
    list, list -> float
    OBJ: Calcular la distancia entre dos puntos en el espacio utilizando la formula de la distancia euclidiana
    """
    
    return math.sqrt((punto1[0] - punto2[0])**2 +
                     (punto1[1] - punto2[1])**2 +
                     (punto1[2] - punto2[2])**2)

def ejercicio13():
    print("\n EJERCICIO 13: Distancia entre dos puntos en el espacio")
    print("--------------------------------------------------")
    print("A partir de dos puntos en el espacio, calcular la distancia\n")
    
    print("\tCoordenadas punto 1:")
    x1 = float(input("Valor de x: "))
    y1 = float(input("Valor de y: "))
    z1 = float(input("Valor de z: "))
    
    print("\n\tCoordenadas punto 2:")
    x2 = float(input("Valor de x: "))
    y2 = float(input("Valor de y: "))
    z2 = float(input("Valor de z: "))

    punto1 = (x1,y1,z1)
    punto2 = (x2,y2,z2)
    print(f"La distancia entre {punto1} y {punto2} es de {distancia_dos_puntos(punto1,punto2):.2f}")
    
########################################################
    
def suma_numComplejos(comp1,comp2):
    return comp1 + comp2

def resta_numComplejos(comp1,comp2):
    return comp1 - comp2

def mult_numComplejos(comp1,comp2):
    return comp1 * comp2

def div_numComplejos(comp1,comp2):
    """complex, complex -> str o complex
    """
    if comp2 == 0:
        return "El segundo argumento es 0, no se puede dividir"
    return comp1 / comp2

def ejercicio14():
    print("\n EJERCICIO 14: Operaciones con numeros complejos")
    print("--------------------------------------------------")
    print("Con dos numeros complejos dados, realizar sus operaciones\n")

    try:
        comp1 = complex(input("Introduzca primer numero complejo (ej: 2+3j o 2j): "))
        comp2 = complex(input("Introduzca segundo numero complejo (ej: 2+3j o 2j): "))
    except:
        print("Ha ocurrido un error, asegurate de que el numero complejo siga la estructura del ejemplo propuesto")
        return
    
    print(f"\n\t{comp1} + {comp2} = {suma_numComplejos(comp1,comp2)}")
    print(f"\t{comp1} - {comp2} = {resta_numComplejos(comp1,comp2)}")
    print(f"\t{comp1} * {comp2} = {mult_numComplejos(comp1,comp2)}")
    
    if comp2 == 0:
        print(f"\t{div_numComplejos(comp1,comp2)}")
    else:
        print(f"\t{comp1} / {comp2} = {div_numComplejos(comp1,comp2):.2f}")
    
########################################################

def es_bisiesto(anio):
    """float -> boolean
    OBJ: Dado un anio, determinar si es bisiesto o no
    """
    if anio % 400 == 0 or (anio % 4 == 0 and anio % 100 != 0):
        return True
    return False

def ejercicio15():
    print("\n EJERCICIO 15: ¿Es bisiesto?")
    print("--------------------------------------------------\n")
    
    anio = int(input(("Introduzca anio: ")))
    
    if(es_bisiesto(anio)):
        print(f"\nEl anio {anio} es bisiesto")
    else:
        print(f"\nEl anio {anio} no es bisiesto")
        
########################################################

#MAIN
opc = menu("Bienvenido al programa",opciones)

while True:
    if opc == 1:
        ejercicio1()
    elif opc == 2:
        ejercicio2()
    elif opc == 3:
        ejercicio3()
    elif opc == 4:
        ejercicio4()
    elif opc == 5:
        ejercicio5()
    elif opc == 6:
        ejercicio6()    
    elif opc == 7:
        ejercicio7()        
    elif opc ==8:
        ejercicio8()
    elif opc ==9:
        ejercicio9()
    elif opc ==10:
        ejercicio10()
    elif opc ==11:
        ejercicio11()
    elif opc == 12:
        ejercicio12()
    elif opc ==13:
        ejercicio13()
    elif opc ==14:
        ejercicio14()
    elif opc ==15:
        ejercicio15()
    elif opc == 16:
        print("\tGracias por utilizar el programa!")
        break
    else:
        print("\tOpcion no disponible")
        
    input("\nPresione enter para volver al menu...")    
    opc = menu("Bienvenido al programa",opciones)

