import os
if os.path.exists("pruebaFichero.txt"):
    lines = ["Chengzhe", "Li", "Empleado"]
    text = ";".join(lines) + ";"
    with open("pruebaFichero.txt", "a") as f:
        f.write("\n"+text)
    
else:
    with open("pruebaFichero.txt","w") as f:
        f.write("Esto es una prueba")
        print("Escrito con éxito")

with open("pruebaFichero.txt", "r") as f:
    linea = f.readline()
    print(linea)
    elems = linea.split(";")
    print(elems)
    for elem in elems:
        print("Nombre " + elem)