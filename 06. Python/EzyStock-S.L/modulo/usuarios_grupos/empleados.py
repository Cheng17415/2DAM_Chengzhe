from typing import List, Optional
import matplotlib.pyplot as plt

# Ruta del archivo de empleados
FICHERO_EMPLEADOS = "base_datos/empleados.txt"

# Lista global de empleados
empleados = []

class Empleado:
    #ID global de los empleados
    cont_id= 1

    #Constructor
    def __init__(self, id = None, nombre = None, apellido=None, edad=None,
                  salario=None, supervisorId = None) -> None:

        if id is None:
            self.id = Empleado.cont_id
        else:
            self.id = int(id)

        Empleado.cont_id = max(Empleado.cont_id, self.id + 1)

        self.nombre = nombre
        self.apellido = apellido if apellido else None
        self.edad = int(edad) if edad not in (None, "", "None") else None
        self.salario = float(salario) if salario not in (None, "", "None") else None
        self.supervisorId = int(supervisorId) if supervisorId not in (None, "", "None") else None
   
    #Getters y setters
    def getEmpleado(self) -> tuple:
        return self.id, self.nombre, self.apellido, self.edad, self.salario, self.supervisorId
    
    def getId(self) -> int:
        return self.id

    def getNombre(self) -> str:
        return self.nombre or ""
   
    def setNombre(self,nombre) -> None:
        self.nombre = nombre
   
    def getApellido(self) -> str:
        return self.apellido or ""
   
    def setApellido(self,apellido) -> None:
        self.apellido = apellido

    def getEdad(self) -> Optional[int]:
        return self.edad
   
    def setEdad(self,edad) -> None:
        self.edad = edad

    def getSalario(self) -> Optional[float]:
        return self.salario
   
    def setSalario(self,salario) -> None:
        self.salario = salario

    def getSupervisorId(self) -> Optional[int]:
        return self.supervisorId
    
    def setSupervisorId(self, supervisorId) -> None:
        self.supervisorId = supervisorId

    #Convertir a formato fichero.
    def to_file(self) -> str:
        valores = [
            self.id,
            self.nombre,
            self.apellido if self.apellido else "",
            self.edad if self.edad is not None else "",
            self.salario if self.salario is not None else "",
            self.supervisorId if self.supervisorId is not None else ""
        ]
        return ";".join(map(str, valores))
    
    #toString
    def __str__(self):
        edad = self.getEdad() if self.getEdad() is not None else "N/A"
        salario = f"{self.getSalario():.2f}" if self.getSalario() is not None else "N/A"
        return f'ID: {self.id:<3} | Nombre: {self.nombre:<15} | Apellido: {self.apellido or "":<15} | Edad: {edad:<3} | Salario: {salario:<10} | Supervisor: {obtenerNombreSupervisor(self.supervisorId)}'

def listarEmpleados() -> None:
  leer_fichero()
  for emp in empleados:
      print(emp)

def agregarEmpleado() -> None:
   nombre = input("Nombre del empleado: ").strip()
   apellido = input("Apellido del empleado: ").strip()
   edad= input("Edad del empleado: ").strip()
   salario = input("Salario del empleado: ").strip()
   supervisorId_input = input("ID del supervisor: ").strip()
   supervisorId = int(supervisorId_input) if supervisorId_input else None
  
  #Lo hago de esta manera ya que no quiero modificar el id
   empleado = Empleado(nombre = nombre, apellido = apellido, edad = edad, salario = salario, supervisorId = supervisorId)
   guardarEmpleado(empleado)

   print("\nEmpleado añadido correctamente.")

def encontrarEmpleadoId(id) -> Optional["Empleado"]:
    '''A partir de una ID obtener al empleado''' 
    for emp in empleados:
        if emp.getId() == id:
            return emp
    return None

def encontrarEmpleadoNombre(nombre:str) -> List[Empleado]:
    """Busca empleados por nombre (búsqueda parcial, no distingue mayúsculas)"""
    nombre_lower = nombre.lower()
    return [t for t in empleados if nombre_lower in t.nombre.lower()]

def obtenerNombreSupervisor(supervisorId) -> str:
    '''A partir de una ID, obtener nombre y apellido del supervisor''' 
    if supervisorId is None: 
        return "Sin supervisor"
    supervisor = encontrarEmpleadoId(supervisorId)

    if supervisor:
        return f"{supervisor.nombre} {supervisor.apellido or ''}"
    
    return f"ID ${supervisorId} No existe"

def modificarEmpleado() -> None:
    id = int(input("ID del empleado a modificar: ").strip())
    empleado = encontrarEmpleadoId(id)
    if empleado is None:
        print("No existe ese empleado.")
        return
    
    modificaciones = 0
    print("Empleado encontrado.\n\tModifique el campo o deje en blanco si está correcto.")
    
    nombre = input(f"Nombre ({empleado.nombre}): ").strip()
    empleado.nombre, modificaciones = huboCambio(nombre, empleado.nombre, modificaciones)
    
    apellido = input(f"Apellido ({empleado.apellido or 'N/A'}): ").strip()
    empleado.apellido, modificaciones = huboCambio(apellido, empleado.apellido, modificaciones)
    
    edad = input(f"Edad ({empleado.edad if empleado.edad is not None else 'N/A'}): ").strip()
    empleado.edad, modificaciones = huboCambio(edad, empleado.edad, modificaciones, cast=int)
    
    salario = input(f"Salario ({empleado.salario if empleado.salario is not None else 'N/A'}): ").strip()
    empleado.salario, modificaciones = huboCambio(salario, empleado.salario, modificaciones, cast=float)

    cambioSuper = input(f"¿Desea cambiar el ID del Supervisor ({empleado.supervisorId if empleado.supervisorId is not None else 'Sin supervisor'})? Y/N: ").strip()
    if cambioSuper.upper() == "Y":
        supervisorId_input = input("Nuevo ID del Supervisor: ").strip()
        empleado.supervisorId, modificaciones = huboCambio(supervisorId_input, empleado.supervisorId, modificaciones, cast=int)

    if modificaciones > 0:
        print("\nEmpleado modificado correctamente.")
        guardar_cambios()
    else:
        print("\nNo se realizaron modificaciones al empleado.")

def huboCambio(texto, atr, contador, cast=None):
    '''Evalua si el texto pasado está vacío. Si lo es, devuelve el atributo que tenía previamente.
       Si no está vacío, aplica el tipo de dato especificado (cast) si es necesario.'''
    if texto.strip() == "":
        return atr, contador
    contador += 1
    return (cast(texto) if cast else texto), contador

def eliminarEmpleado():
    id = int(input("ID del empleado a eliminar: ").strip())
    empleado = encontrarEmpleadoId(id)
    if empleado is None:
        print("No existe ese empleado.")
        return

    empleados.remove(empleado)
    guardar_cambios()
    print("Empleado eliminado exitosamente")
    
def guardarEmpleado(empleado : Empleado) -> None:
    '''Guarda un empleado en el fichero'''
    with open(FICHERO_EMPLEADOS,"a") as f:
        f.write(empleado.to_file() + "\n")

def encontrarEmpleado():
    nombre = input("Introduzca el nombre del empleado: ")
    if nombre.strip() == "":
        return
    empleados = encontrarEmpleadoNombre(nombre)
    if empleados is None:
        print("No existe ese empleado.")
        return
    for emp in empleados:
        print(emp)

def empleadosPorSupervisor():
    mapa = {}

    for e in empleados:
        if e.supervisorId:
            mapa.setdefault(e.supervisorId, []).append(e)

    print("\n=== Terceros por empleado ===")  
    for supId, lista in mapa.items():
        sup = encontrarEmpleadoId(supId)
        print(f"\nSupervisor: {sup.nombre} {sup.apellido or ''} (ID: {supId})")
        for emp in lista:
            print(f"  - {emp.nombre}")

def empleadoTercero():
    """Muestra los terceros que gestiona cada empleado."""
    try:
        from modulo.terceros.nuevo_tercero import GESTOR_TERCEROS
    except ImportError:
        print("Error: No se encuentra el módulo 'nuevo_tercero'.")
        return

    GESTOR_TERCEROS.cargar_terceros()
    
    lista_terceros = GESTOR_TERCEROS.terceros 

    if not lista_terceros:
        print("No hay terceros registrados.")
        return

    asignaciones = {}

    for obj_tercero in lista_terceros:
        id_emp = obj_tercero.empleado_id 
        
        if id_emp:
            asignaciones.setdefault(id_emp, []).append(obj_tercero.nombre)

    print("\n=== Terceros por empleado ===")    
    
    for emp in empleados:
        emp_id = emp.id

        lista_nombres_terceros = asignaciones.get(emp_id, [])

        if lista_nombres_terceros:
            print(f"\nEmpleado: {emp.nombre} {emp.apellido or ''} (ID: {emp_id})")
            for terc in lista_nombres_terceros:
                print(f"  - {terc}")


def salarioMensual():
    return sum(emp.salario for emp in empleados)

def pieSalario():
    nombres = [e.nombre for e in empleados if e.nombre]
    salarios = [e.salario for e in empleados if e.salario]

    fig, ax = plt.subplots()

    ax.pie(salarios, labels = nombres, autopct=lambda pct: mostrarValor(pct, salarios))
    ax.set_title(f"Salario Mensual: {salarioMensual():.2f}€")
    plt.show()

def mostrarValor(pct, todos):
    total = sum(todos)
    valor = pct * total / 100
    return f"{valor:.2f}€"

def preguntaSalarioMensual():
    print(f"El salario mensual total es de {salarioMensual()}€")
    respuesta = input("¿Quiere ver el gráfico? Y/N: ")
    if(respuesta.upper() == 'Y'):
        pieSalario()

def salarios():
    empleados_ordenados = sorted(empleados, key=lambda e: e.salario)
    salarios = [e.salario for e in empleados_ordenados if e.salario]
    nombres = [e.nombre for e in empleados_ordenados]
    
    print(f"Salario máximo: {max(salarios):.2f}€")
    print(f"Salario mínimo: {min(salarios):.2f}€")

    respuesta = input("¿Quiere ver el diagrama? Y/N: ")
    if respuesta.upper() == 'Y':
        diagramaSalarios(salarios, nombres)

def diagramaSalarios(salarios:list, nombres:list):
    fig, ax = plt.subplots()
    ax.bar(nombres,sorted(salarios))
    plt.xlabel("Nombres")
    plt.ylabel("Salarios (€)")
    plt.title("Diagrama de Salarios")
    plt.tight_layout()
    plt.show()

def histogramaEdades():
    edades = [e.edad for e in empleados if e.edad is not None]

    if not edades:
        print("No hay edades registradas para generar el histograma.")
        return
    
    fig, ax = plt.subplots()

    # Crear bins enteros de 1 en 1
    min_edad = min(edades)
    max_edad = max(edades)

    bins = range(min_edad, max_edad + 2)  # +2 para incluir el último valor correctamente

    ax.hist(edades, bins=bins, align='left')  # align left para centrar bien las barras

    # Etiquetas
    ax.set_xlabel("Edad")
    ax.set_ylabel("Empleados")
    ax.set_title("Histograma de Edades de los Empleados")

    # Eje Y de 1 en 1
    max_y = ax.get_ylim()[1]
    ax.set_yticks(range(0, int(max_y) + 1, 1))

    # Eje X de 1 en 1
    ax.set_xticks(range(min_edad, max_edad + 1, 1))

    plt.show()



def aumentoGeneral():
    try:
        porc = float(input("Porcentaje de aumento: "))
    except:
        print("Valor inválido.")
        return

    for e in empleados:
        if e.salario:
            e.salario *= (1 + porc/100)

    guardar_cambios()
    print("Aumento aplicado correctamente.")

# Funciones de persistencia
def guardar_cambios():
    with open(FICHERO_EMPLEADOS, "w") as f:
        for empleado in empleados:
            f.write(empleado.to_file() + "\n")

def leer_fichero():
    empleados.clear()
    try:
        with open(FICHERO_EMPLEADOS, "r") as f:
            for linea in f:
                partes = linea.strip().split(";")
                if len(partes) == 6:
                    empleados.append(Empleado(*partes))
    except FileNotFoundError:
        print(f"Archivo '{FICHERO_EMPLEADOS}' no encontrado. Se creará al guardar.")



