# modules/productos_almacenes/almacenes.py
import os

# Obtener la ruta correcta independientemente de dónde se ejecute
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RUTA_ALMACENES = os.path.join(BASE_DIR, "base_datos", "almacenes.txt")

# Asegurar que existe la carpeta
os.makedirs(os.path.dirname(RUTA_ALMACENES), exist_ok=True)

# ------------------------------------------------
# LECTURA Y GUARDADO
# ------------------------------------------------

def leer_almacenes():
    almacenes = []
    try:
        with open(RUTA_ALMACENES, "r", encoding="utf-8") as f:
            for linea in f:
                datos = linea.strip().split("|")
                if len(datos) == 5:
                    almacenes.append({
                        "codigo": datos[0],
                        "nombre": datos[1],
                        "direccion": datos[2],
                        "responsable": datos[3],
                        "estado": datos[4],
                    })
    except FileNotFoundError:
        # Crear archivo si no existe
        with open(RUTA_ALMACENES, "w", encoding="utf-8") as f:
            pass

    return almacenes


def guardar_almacenes(lista):
    with open(RUTA_ALMACENES, "w", encoding="utf-8") as f:
        for a in lista:
            linea = f"{a['codigo']}|{a['nombre']}|{a['direccion']}|{a['responsable']}|{a['estado']}\n"
            f.write(linea)

# ------------------------------------------------
# GENERAR CODIGO AUTOMÁTICO
# ------------------------------------------------

def generar_codigo(almacenes):
    numero = len(almacenes) + 1
    return f"ALM{numero:03d}"

# ------------------------------------------------
# CRUD
# ------------------------------------------------

def crear_almacen():
    almacenes = leer_almacenes()

    codigo = generar_codigo(almacenes)
    nombre = input("Nombre del almacén: ")
    direccion = input("Dirección: ")
    responsable = input("Responsable: ")

    nuevo = {
        "codigo": codigo,
        "nombre": nombre,
        "direccion": direccion,
        "responsable": responsable,
        "estado": "ACTIVO",
    }

    almacenes.append(nuevo)
    guardar_almacenes(almacenes)

    print("Almacén registrado correctamente.")


def listar_almacenes():
    almacenes = leer_almacenes()
    print("\n=== LISTA DE ALMACENES ===")
    for a in almacenes:
        print(f"{a['codigo']} - {a['nombre']} ({a['estado']})")


def actualizar_almacen():
    almacenes = leer_almacenes()
    codigo = input("Código del almacén a modificar: ")

    for a in almacenes:
        if a["codigo"] == codigo:
            print("Deja en blanco para no cambiar el valor")

            nombre = input(f"Nuevo nombre ({a['nombre']}): ") or a['nombre']
            direccion = input(f"Nueva dirección ({a['direccion']}): ") or a['direccion']
            responsable = input(f"Nuevo responsable ({a['responsable']}): ") or a['responsable']
            estado = input(f"Estado [ACTIVO/INACTIVO] ({a['estado']}): ") or a['estado']

            a["nombre"] = nombre
            a["direccion"] = direccion
            a["responsable"] = responsable
            a["estado"] = estado

            guardar_almacenes(almacenes)
            print("Almacén actualizado.")
            return

    print("Código no encontrado.")


def desactivar_almacen():
    almacenes = leer_almacenes()
    codigo = input("Código del almacén a desactivar: ")

    for a in almacenes:
        if a["codigo"] == codigo:
            a["estado"] = "INACTIVO"
            guardar_almacenes(almacenes)
            print("Almacén desactivado.")
            return

    print("Código no encontrado.")