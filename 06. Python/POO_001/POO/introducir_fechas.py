from datetime import datetime, timedelta
import locale
'''
fecha = input("Dime una fecha (dd/mm/yyyy): ")
fecha = datetime.strptime(fecha, "%d/%m/%Y")
undiamas = timedelta(days=1)
locale.setlocale(locale.LC_ALL, 'es_ES')

while fecha.strftime("%A").capitalize() != 'Jueves':
    fecha = fecha + undiamas

sietediasmas = timedelta(days=7)

mes = fecha.month
while fecha.month == mes:
    print(f'Fecha: {fecha.strftime("%d %B %Y").upper()}')
    fecha = fecha + sietediasmas
'''
#Introducido 1 año, para cada mes del año, el ultimo viernes de cada mes
'''
fecha2 = input("Dime un año (yyyy):")
fecha2 = datetime.strptime(fecha2, "%Y")
undiamas = timedelta(days=1)
sietediasmas = timedelta(days=7)
locale.setlocale(locale.LC_ALL, 'es_ES')

anio = fecha2.year
while fecha2.year == anio:
    while fecha2.strftime("%A").capitalize() != 'Viernes':
        fecha2 = fecha2 + undiamas

    mes = fecha2.month
    ultimoViernes = ''
    while fecha2.month == mes:
        ultimoViernes = f'Fecha: {fecha2.strftime("%d %B %Y").upper()}'
        fecha2 = fecha2 + sietediasmas

    print(ultimoViernes)
    fecha2 = fecha2.replace(day = 1)
'''
#Introducir una fecha, devolver el calendario de ese mes
import calendar
locale.setlocale(locale.LC_ALL, 'es_ES')
fecha3 = input('Introduzca una fecha (dd/mm/yyyy): ')
fecha3 = datetime.strptime(fecha3, "%d/%m/%Y")
print(calendar.month(fecha3.year, fecha3.month))