import random
tablaColores = ['R','V','Z','M','B']

def alea(ls, li):
    return (int)(random.random() * (ls - li + 1)) + li

def rellenar(m):
    for i in range(len(m)):
        m[i] = tablaColores[alea(0,len(tablaColores) -1)]

def evaluacionCombinacion(cb):
    usu = []

if __name__ == '__main__':
    ...