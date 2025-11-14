import math
from random import randrange
opciones = (
  "1. Ejercicio 1", "2. Ejercicio 2", "3. Ejercicio 3",
  "4. Ejercicio 4", "5. Ejercicio 5", "6. Ejercicio 6",
  "7. Ejercicio 7", "8. Ejercicio 8", "9. Ejercicio 9",
  "10. Ejercicio 10", "11. Ejercicio 11", "12. Ejercicio 12",
  "13. Ejercicio 13", "14. Ejercicio 14", "15. Ejercicio 15",
  "16. Ejercicio 16", "17. Ejercicio 17", "18. Ejercicio 18",
  "19. Ejercicio 19", "20. Ejercicio 20", "21. Ejercicio 21",
  "22. Ejercicio 22","23. Salir"
)

def menu(titulo, opciones):
  ESPACIOS_INICIO = " " * 10
  MAX_WIDTH = 50

  print(ESPACIOS_INICIO + "┌" + "─" * MAX_WIDTH + "┐")
  print(ESPACIOS_INICIO + "│" + " " * MAX_WIDTH + "│")
  print(ESPACIOS_INICIO + "│" + titulo.center(MAX_WIDTH) + "│")
  print(ESPACIOS_INICIO + "├" + "─" * MAX_WIDTH + "┤")
  print(ESPACIOS_INICIO + "│" + " " * MAX_WIDTH + "│")

  for opcion in opciones:
    print(ESPACIOS_INICIO + "│ " + opcion.ljust(MAX_WIDTH - 1) + "│")
    
  print(ESPACIOS_INICIO + "│" + " " * MAX_WIDTH + "│")
  print(ESPACIOS_INICIO + "└" + "─" * MAX_WIDTH + "┘")


  return int(input("\n\tElija una opcion: "))


########################################################################
def ejercicio1():
  print('Determinar si en la compra se aplica un descuento')
  print('-------------------------------------------------\n')
  descuento = 0
  try:
    importe = float(input('Introduzca el costo en euros: '))
    if importe < 0:
      print('El importe debe ser mayor que 0')
    else:
      if importe >= 100:
        print('¿Como va a ser el pago?')
        metodo = int(input('1. Contado 2. Tarjeta: '))
        if metodo == 1:
          descuento = 0.05
        elif metodo == 2:
          descuento = 0.02
        else:
          print('Metodo de pago no valido')
    total = importe * (1 - descuento)
    print(f"Total a pagar: {total:.2f} EUR")
  except ValueError:
    print('Error: debes introducir un número válido para el importe.')
  
#######################################################################
def calcularLetra(nota):
  if nota >= 8.5:
    letra = 'A'
  elif nota >= 6.5:
    letra = 'B'
  elif nota >= 5:
    letra = 'C'
  elif nota >= 3.5:
    letra = 'D'
  else:
    letra = 'F'
  return letra

def ejercicio2():
  print('Modificar sistema de representacion de calificaciones')
  print('-----------------------------------------------------\n')
  try:
    nota = float(input('Introduzca la nota obtenida: '))
    if nota < 0 or nota > 10 :
      print('La nota debe estar entre 0 y 10')
    else:
      print(f"La nota de {nota}, es igual a la nueva nota {calcularLetra(nota)}")
  except ValueError:
    print('Error: debes introducir un numero valido.')
    
######################################################################################

def ejercicio3():
  print('Ordenar numeros de forma ascendente')
  print('-----------------------------------\n')
  try:
    num1 = int(input('Introduzca primer numero: '))
    num2 = int(input('Introduzca segundo numero: '))
    num3 = int(input('Introduzca tercer numero: '))     
    nums = [num1,num2,num3]
    nums.sort()
    
    print(f"La lista ordenada es: {nums}")
  except ValueError:
    print('Error: debes introducir un numero entero')

#########################################################################################
def mes(num):
  if num < 1 or num > 12:
    return 'Debes introducir un numero desde 1 a 12' 
  meses={
    1:'Enero',
    2:'Febrero',
    3:'Marzo',
    4:'Abril',
    5:'Mayo',
    6:'Junio',
    7:'Julio',
    8:'Agosto',
    9:'Septiembre',
    10:'Octubre',
    11:'Noviembre',
    12:'Diciembre',
  }
  return meses[num]

def ejercicio4():
  print('Correspondencia de numero entero a nombre de mes')
  print('------------------------------------------------\n')
  num = -1
  try:
    while(num < 1 or num > 12):
      num = int(input('Indique el numero del mes(1-12): '))
    print(f'El mes {num} es {mes(num)}')
  except ValueError:
    print('ERROR: Debes introducir un numero entero')

#########################################################################################

def ejercicio5():
  print('Pedir numeros y devolver media, mayor y menor')
  print('---------------------------------------------\n')
  suma = 0
  max = -math.inf
  min = math.inf
  numeros = []
  try:
    cantidad = int(input('Introduzca de cantidad de numeros que se van a introducir: '))
    if cantidad < 2:
      print('La cantidad introducida es menor que 2.')
      return
    
    for i in range(cantidad):
      num = float(input(f'Introduzca el {i+1}º numero: '))
      numeros.append(num)
      suma += num

      if num > max:
        max = num
      if num < min:
        min = num
    print(f'La media aritmetica es {suma/len(numeros)}. El maximo es {max} y el minimo es {min}')

  except ValueError:
    print('ERROR: Debes introducir un numero valido')

##########################################################################################

def sumarListas(list1, list2):
  listSuma = []
  tamanoMenos = min(len(list1),len(list2))
  listaMas = list1 if len(list1) > len(list2) else list2

  for i in range(tamanoMenos):
    listSuma.append(list1[i] + list2[i])

  for i in range(tamanoMenos , len(listaMas)):
    listSuma.append(listaMas[i])

  return listSuma

def ejercicio6():
  print('Sumas elementos de listas misma longitud')
  print('-----------------------------------\n')
  list1 = [10,21.5,20]
  list2 = [-10,21.5,10]
  print(sumarListas(list1,list2))

##########################################################################################

def ejercicio7():
  print('Sumas elementos de listas distinta longitud')
  print('-------------------------------------------\n')
  list1 = [10,21.5,20,-100,44,12]
  list2 = [-10,21.5,10,100,34]
  print(sumarListas(list1,list2))

##########################################################################################

def ejercicio8():
  print('Ordenar listas')
  print('--------------\n')
  datos = [10,-20,40,60,-10,-100,-5,0,20,100,
           20,33,44,11,-12,-150,-9,255,-255,1]
  datosAsc = sorted(datos)
  datosDesc = sorted(datos,reverse = True)
  print(f'Numeros ordenados ascendentes: {datosAsc}')
  print(f'Numeros ordenados descendentes: {datosDesc}')

##########################################################################################

def ejercicio9():
  print('Generar lista con numeros aleatorios y calcular media, valor max y min')
  print('----------------------------------------------------------------------\n')
  numEnteros = 5
  suma = 0
  enteros = []

  for i in range(numEnteros):
    rand = randrange(1,21)
    suma += rand
    enteros.append(rand)

  numMax = max(enteros)
  numMin = min(enteros)
  media = suma / len(enteros)

  print(enteros)
  print(f'\nLa media es de {media:.2f}')
  print(f'El maximo es de {numMax}')
  print(f'El minimo es de {numMin}')

##########################################################################################  
def es_palindromo(palabra):
  if len(palabra) < 1: return False
  
  i = 0
  j = len(palabra) - 1

  while i < j:
    if palabra[i] != palabra[j]:
      return False
    i+=1
    j-=1

  return True

def ejercicio10():
  print('Determinar si una palabra es palindromo')
  print('---------------------------------------\n')
  palabra = input('Introduzca una palabra: ')
  palindromo = es_palindromo(palabra)
  print(f'La palabra {palabra} es palindromo') if palindromo else print(f'La palabra {palabra} no es palindromo')

##########################################################################################

def capitalizar(frase):
  palabras = frase.split(' ')
  palabra_cap = ''
  
  for palabra in palabras:
    palabra_cap  += palabra.capitalize() + " "
    
  return palabra_cap.rstrip()

def ejercicio11():
  print('Capitalizar cada palabra')
  print('------------------------\n')
  frase = input('Escriba una frase: ')
  print(capitalizar(frase))

##########################################################################################
def ejercicio12():
  print('Determinar si dos cadenas de caracteres son iguales')
  print('---------------------------------------------------\n')
  cadena1 = 'Hola buenos dias'
  cadena2 = 'Hola buenos dias'
  print(cadena1 == cadena2)
##########################################################################################
def cincoVocales(palabra):
  palabra = palabra.lower()
  vocales = ('a','e','i','o','u')
  
  for vocal in vocales:
    if vocal in palabra:
      continue
    else:
      return False
  return True

def ejercicio13():
  print('Determinar si una palabra contiene las 5 vocales')
  print('------------------------------------------------\n')
  palabra = input('Introduzca una palabra: ')
  print('Contiene todas las vocales' if cincoVocales(palabra) else 'No contiene todas las vocales')

##########################################################################################

def codificarFrase(frase):
  frase = frase.lower()
  codificar = {
    'a':'4',
    'e':'3',
    'i':'1',
    'o':'0',
    'u':'#'
  }

  fraseCod = ''
  for letra in frase:
    fraseCod += codificar[letra] if letra in codificar else letra
  return fraseCod

def ejercicio14():
  print('Codificar frase')
  print('---------------\n')
  frase = input('Introduzca una frase: ')
  print(codificarFrase(frase))
  
########################################################################################## 

def ejercicio15():
  print('Longitudes de palabras')
  print('---------------------\n')
  longitudPalabras = {}
  palabra = input('Introduzca una palabra o "fin" para finalizar: ')
  
  while(palabra != 'fin'):
    longitudPalabras[len(palabra)] = longitudPalabras[len(palabra)] + 1 if len(palabra) in longitudPalabras.keys() else 1
    palabra = input('Introduzca una palabra o "fin" para finalizar: ')
    
  for i in range(1,26):
    print(f"Palabras longitud {i}: {longitudPalabras.get(i,0)}")
    
##########################################################################################
almPersonas = []
def introducirPersona():
  sexo = None
  while sexo not in ('F','M','N'):
    sexo = input('¿De qué sexo eres? (F,M,N): ').strip().upper()
  try:
    edad = int(input('¿Cuantos anios tienes? '))
    
    while edad < 0:
      edad = int(input('¿Cuantos anios tienes? '))
      
    almPersonas.append({'numero': len(almPersonas) + 1,'sexo': sexo, 'edad': edad})
  except ValueError:
    print('Error: Tienes que introducir un numero entero')
  
def leerPersonas():
  for persona in almPersonas:
    print(persona)
    
def ejercicio16():
  opciones = ('1.Introdocir datos de persona', '2.Mostrar datos persona','3.Salir')
  opc = menu("Almacen de datos anonimos", opciones)
  acciones = {
    1: introducirPersona,
    2: leerPersonas
  }
  while True:
    if opc == len(acciones) + 1:
      print("\tGracias por utilizar el programa!")
      break
    
    accion = acciones.get(opc)
    if(accion):
      accion()
    else:
      print('\tOpción no disponible')      
    input("\nPresione enter para volver al menu...")    
    opc = menu("Ejercicio",opciones)
    
##########################################################################################
class Fecha:
  def __init__(self,dd:int,mm:int,yy:int):
    self.dia = dd
    self.mes = mm
    self.anno = yy
    
  def getFecha(self):
    return self.anno, self.mes, self.dia
  
  def setFecha(self,dd:int,mm:int,yy:int):
    self.dia = dd
    self.mes = mm
    self.anno = yy
    
class Cronologia:
  def __init__(self,nacimiento:Fecha,matrimonio:Fecha,deceso:Fecha):
    self.nacimiento = nacimiento
    self.matrimonio = matrimonio
    self.deceso = deceso
  
  def getNacimiento(self):
    return self.nacimiento.getFecha()
  def getMatrimonio(self):
    return self.matrimonio.getFecha()
  def getDeceso(self):
    return self.deceso.getFecha()
    
  def setNacimiento(self, nacimiento):
    self.nacimiento = nacimiento
  def setMatrimonio(self, matrimonio):
    self.matrimonio = matrimonio
  def setDeceso(self, deceso):
    self.deceso = deceso
    
def introducirFecha(mensaje:str) -> Fecha:
  print(mensaje)
  anio,mes,dia = 0, 0, 0
  
  while anio not in range(1,10000):
    try:
      anio = int(input('\tIntroduzca anio: '))
    except ValueError:
      print('Debes introducir un anio valido')
      
  while mes not in range(1,13):
    try:
      mes = int(input('\tIntroduzca mes en numeros: '))
    except ValueError:
      print('Debes introducir un mes valido')
    
  maxDias = (31,30) [mes in [4,6,9,11]]

  if mes == 2:
    maxDias = 29 if anio % 4 == 0 and (anio % 100 != 0 or anio % 400 == 0) else 28
  
  while dia not in range(1,maxDias + 1):
    try:
      dia = int(input('\tIntroduzca dia: '))
    except ValueError:
      print('Debes introducir un dia valido')
      
  return Fecha(dia,mes,anio)
  
    
def visualizarTodasFechas(fechasTotales:list):
  TIPOFECHAS = ('nacimiento','matrimonio','deceso')
  
  for idx,crono in enumerate(fechasTotales):
    getters = {
      'nacimiento' : crono.getNacimiento,
      'matrimonio' : crono.getMatrimonio,
      'deceso' : crono.getDeceso 
    }
    
    print(f'\nPersona {idx+1}:')
    for tipoFecha in TIPOFECHAS:
      fecha = getters[tipoFecha]()
      print(f'\tFecha de {tipoFecha}: {fecha[0]}/{fecha[1]}/{fecha[2]}')
      decision = input('¿Quieres cambiar la fecha? S/N ')
      if decision.strip().upper() == 'S':
        nuevaFecha = introducirFecha('\nIntroduzca nueva fecha: ')
        
        setters = {
          'nacimiento' : crono.setNacimiento,
          'matrimonio' : crono.setMatrimonio,
          'deceso' : crono.setDeceso 
        }
        
        setters[tipoFecha](nuevaFecha)
      
def introducirDatosPersona(fechasTotales:list):
    nacimiento = introducirFecha('\nFecha de nacimiento: ')
    matrimonio = introducirFecha('\nFecha de matrimonio: ')
    deceso = introducirFecha('\nFecha de deceso: ')
    fechasTotales.append(Cronologia(nacimiento,matrimonio,deceso))
    
def ejercicio17():
  fechasTotales = []
  opciones = ('1. Visualizar todas las personas','2. Introducir fechas de una persona','3. Salir')
  acciones = {
    1: lambda: visualizarTodasFechas(fechasTotales),
    2: lambda: introducirDatosPersona(fechasTotales)
  }
  while True:
    opc = menu('Fechas importantes de personas', opciones)
    if opc == len(acciones) + 1:
      print("\tGracias por utilizar el programa!")
      break
    
    accion = acciones.get(opc)
    if(accion):
      accion()
    else:
      print('\tOpción no disponible')      
    input("\nPresione enter para volver al menu...")    

##########################################################################################
def ejercicio19():
  print('Pedir 10 numeros al usuario. Cada numero es restado por el siguiente numero. ')
  print('----------------------------------------------------------------------------\n')
  numeros = []
  numerosFinal = []
  for i in range(1,11):
    try:
      num = float(input(f'Introduzca un numero({i}/10): '))
      numeros.append(num)
    except ValueError:
      print('ERROR: Debes introducir un número')
      
  for idx,num in enumerate(numeros):
    if idx == len(numeros) - 1:
      numerosFinal.append(numeros[idx] - numeros[0])
      continue
    numerosFinal.append(numeros[idx] - numeros[idx + 1])
    
  print(numerosFinal)
##########################################################################################

def ejercicio20():
  print('Gestion de cobros en una gasolinera')
  print('-----------------------------------\n')
  gastosGas = []
  gastosPro = []
  gastosTot = []
  for i in range(1,11):
    try:
      print(f'\tCliente{i}')
      gas = float(input(f'Introduzca cantidad gastada en gasolina: '))
      pro = float(input(f'Introduzca cantidad gastada en productos: '))
      gastosGas.append(gas)
      gastosPro.append(pro)
      gastosTot.append(gas+pro)
    except ValueError:
      print('ERROR: Debes introducir un número')
      
  for i in range(len(gastosTot)):
    print(f'Cliente {i+1} - {gastosTot[i]}€')
    
##########################################################################################
def visualizarAgenda(agendaTel:dict):
   for k,v in agendaTel.items():
    print(f'\tNombre: {k} - Telefono: {v}')

def introducirContacto(agendaTel:dict):
  while True:
    nombre = input('\tIntroduzca nombre: ')
    tel = 0
    while nombre in agendaTel.keys():
      print('Ese nombre ya se encuentra en la agenda')
      nombre = input('\tIntroduzca nombre: ')
    try:
      tel = int(input('\tIntroduzca numero de telefono: '))
    except ValueError:
      print('Debers introducir un numero de telefono valido')
    agendaTel[nombre] = tel
    
    seguir = input('Quiere seguir? S/N: ')
    if seguir.strip().upper() == 'N':
      break
  
def ejercicio21():
  print('Agenda telefonica')
  print('-----------------\n')
  agendaTel = {}
  introducirContacto(agendaTel)
  visualizarAgenda(agendaTel)
  
##########################################################################################
def devolverListas(diccionario:dict, lista:list):
  listaElem = []
  listaNoElem = []
  for item in lista:
    if item in diccionario.keys():
      listaElem.append(item)
    else:
      listaNoElem.append(item)
  return listaElem,listaNoElem

def ejercicio22():
  print('Listas con elementos que esten o no esten en el diccionario')
  print('-----------------------------------------------------------\n')
  
  lista = [10,20,30,40,'Pato']
  diccionario = {'Pato' : True,
                 40 : 'si'}
  listaElem, listaNoElem = devolverListas(diccionario,lista)
  print(f"Lista con elementos que se encuentran en el diccionario: ")
  print(listaElem)
  print(f"Lista con elementos que NO se encuentran en el diccionario: ")
  print(listaNoElem)

##########################################################################################
opc = menu("Bienvenidos",opciones)

acciones = {
  1: ejercicio1,
  2: ejercicio2,
  3: ejercicio3,
  4: ejercicio4,
  5: ejercicio5,
  6: ejercicio6,
  7: ejercicio7,
  8: ejercicio8,
  9: ejercicio9,
  10: ejercicio10,
  11: ejercicio11,
  12: ejercicio12,
  13: ejercicio13,
  14: ejercicio14,
  15: ejercicio15,
  16: ejercicio16,
  17: ejercicio17,
  18: ejercicio17,
  19: ejercicio19,
  20: ejercicio20,
  21: ejercicio21,
  22: ejercicio22
  
}

while True:
  if opc == len(acciones) + 1:
    print("\tGracias por utilizar el programa!")
    break
    
  accion = acciones.get(opc)
  if(accion):
    accion()
  else:
    print('\tOpción no disponible')      
  input("\nPresione enter para volver al menu...")    
  opc = menu("Bienvenido al programa",opciones)