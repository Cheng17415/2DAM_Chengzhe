#EJERCICIO 1
'''
print(type(20))
print(type("Hola"))
print(type(10.2))
'''
#EJERCICIO 2
'''
nombre = "Chengzhe"
edad = 22
print(nombre)
print(edad)
print(f"Nombre:{nombre}, edad: {edad}")
'''
#EJERCICIO 3
'''
#A)
longitud = int(input("¿Cuál es la longitud del cuadrado?\n"))
area = longitud * longitud
print(f"La lontidud es {longitud} y el area es {area}")

#B)
longitud = int(input("¿Cuál es la longitud del cuadrado?\n"))
print(f"La lontidud es {longitud} y el area es {longitud * longitud}")

'''
#EJERCICIO 4
'''
ingreso = float(input("¿Cánto es tu ingreso anual?"))
hijos = int(input("¿Cúantos hijos tienes?"))
if ingreso < 0:
    print("Los ingresos no pueden ser negativos")
    
if hijos < 0:
    print("El número de hijos no puede ser negativo")
    

if ingreso >= 0 and hijos >= 0:
    impuesto = (ingreso - 600 - 60 * hijos)/ 3
    print(f"Sus impuestos seran de {impuesto:,.2f}")
'''
#EJERCICIO 5
'''
import datetime
hora = datetime.datetime.now().strftime("%X")
horadiv = hora.split(":")
diaSeg = 86400
for i in range(len(horadiv)):
    horadiv[i]= int(horadiv[i])
segAhora = horadiv[0] * 3600 + horadiv[1] * 60 + horadiv[2]
print(f"Son las {horadiv[0]} horas {horadiv[1]} minutos y {horadiv[2]} segundos")
print(f"Han pasado {segAhora:,} desde la última medianoche")
print(f"Quedan {(diaSeg - segAhora):,} para la siguiente medianoche")
'''
#EJERCICIO 6
'''
distancia = float(input("¿Cuánta distancia lleva recorrido en km? "))
gasoil = float(input("¿Cuántos litros de gasoil has utilizado? "))
pgasoil = float(input("¿Cuál es el precio de gasoil en €? "))
mantenimiento = float(input("Dime los demás costos de mantenimiento "))
kpl = distancia/gasoil
costeTotal = gasoil * pgasoil + mantenimiento
costeKilometro = costeTotal / distancia

print(f"Los kilometros recorridos por litro es de {kpl:,.2f}€")
print(f"El coste total del viaje es de {costeTotal:,.2f}€")
print(f"El coste por kilometro es de {costeKilometro:,.2f}€")
'''
#EJERCICIO 7
'''
try:
    kil = float(input("Introduzca cantidad de kilómetros: "))
    print(f"{kil} km son {kil * 10} Hm, {kil * 10000} Dm y {kil * 1000} m")
except:
    print("Error")
'''
#EJERCICIO 8
'''
texto_numerico = "45"
print(type(texto_numerico))
print(type(int(texto_numerico)))
#Realizando la operación int(), convierte el string en int,
#parecido a Integer.valueOf() de Java

#print(int("Hola"))
#Da error ya que no se puede convertir una cadena de texto en un entero
print(int(3.99999))
#Al convertir un float a int, trunca el número
print(float(34))
#Convierte el número entero en un float
'''
#EJERCICIO 9
'''
lado1 = float(input("¿Cuánto mide el primer lado?"))
lado2 = float(input("¿Cuánto mide el otro lado?"))
area = lado1 * lado2
perimetro = 2 * (lado1 + lado2)
print(f"El área del rectángulo es de {area:,.2f},\
y el perímetro es de {perimetro:,.2f}")
'''
#EJERCICIO 10
'''
num1 = float(input("Escriba el primer numero real "))
num2 = float(input("Escriba el segundo numero real "))
num3 = float(input("Escriba el tercer numero real "))
suma = num1 + num2 + num3
media = suma/3
producto = num1 * num2 * num3
print(f"La media es de {media:.2f}, la suma total es de {suma} \
y el producto total es de {producto:.2f}")
'''
#EJERCICIO 11
'''
TF = int(input("Escriba los grados Fahrenheit que quieres convertir a Centígrados"))
TC = (TF - 32)* 5/9
print(f"{TF} ºF = {TC} ºC")
'''
#EJERCICIO 12
'''
a = 3/2
# La división retorna 1.5 ya que Python trata la división como float
b = 3.0 / 2

#Aquí también retorna 1.5 por el motivo previamente dicho
#y también por que 3.0 es un número flotante

c = 3 // 2
# El operado // es una división pero solo retorna la parte entera
print ('a= ', a, 'b= ', b, 'c= ', c)
'''
#EJERCICIO 13
'''
salario_base = 2000 #Euros
IRPF = 0.32 #32%
comision = (float(input("¿Cuantas ventas en € has realizado el mes anterior? ")) * 0.03)
salario_neto = (salario_base + comision) - (salario_base + comision)* IRPF
print(f"El salario neto de ese mes es de {salario_neto}")
'''
#EJERCICIO 14
'''
eur = float(input("¿Cuantos euros quieres conver a GBP? "))
gbp = eur * 0.87
comision = gbp * 0.02
gbp_final = gbp - comision
print(f"{eur} € son equivalentes a {gbp:.2f} GBP. Tras la comision, obtendras {gbp_final:.2f}")
'''
#EJERCICIO 15
#Parte a
fruta = " ciruela "
tipo = " claudia "
print (fruta + tipo) 


#Se obtiene" ciruela  claudia ".
#La operacion + con las cadenas de texto es la concatenación de ambos.

#Parte b
print (fruta * 3)

#Se obtiene "ciruela  ciruela  ciruela".
#La operación * con las cadenas de texto "multiplicado"por un numero entero(n) es la concatenación
#de la cadena de texto n veces.
