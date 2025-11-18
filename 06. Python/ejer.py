tupla1 = (1,3,5,7)
tup2 = (1, "John", tupla1, True, -23.1)
print(tup2[2][1])
print('tupla1[:-2]:\t', tupla1[:-2])

print('tupla1[0:-2]:\t', tupla1[0:-2])
print('tupla1[::4]:\t', tupla1[::4])

lista1 = ['Alvaro',3, 'Daniel', 'Pilar', 3,'Beatriz']
for i in lista1:
 print(i, end=' ')
print('')
print(lista1)

vocalTupla = ('a', 'e','i','o','u')
vocalLista = list(vocalTupla )
print(vocalTupla)
print(vocalLista)

tupla = tuple(vocalLista)
print(tupla)

print()

diccionario = {
    'frutas': ['manzana','platano','kiwi'],
    'es_gratis' : True,
    'links':{
        'youtube' : 'youtube.com',
        'amazon' : 'amazon.com'
        },
    'num_suerte' : 7
    }

print(diccionario)
print(diccionario['links']['youtube'])
for k,v in diccionario.items():
    print(f"Clave: {k}. Valor: {v}")

dict2 = diccionario.copy()
print(f"Copy(): {dict2}")

print(diccionario.get('frutas','pera'))
print(diccionario.get('sinFrutas','pera'))

print(f"Llaves: {diccionario.keys()}")
print(f"Claves: {diccionario.values()}")
print(dict2.pop('links'))

print(f"popitem() quita el ultimo y lo devuelve: {dict2.popitem()}")
print('Por defecto', dict2.setdefault('num_suerte',0))

clave, valor = diccionario.items()
print(clave, valor)

diccionario.clear()
print(diccionario)
