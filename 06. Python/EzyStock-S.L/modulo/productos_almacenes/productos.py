# modules/productos_almacenes/productos.py
import os

# Obtener la ruta correcta
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RUTA_PRODUCTOS = os.path.join(BASE_DIR, "base_datos", "productos.txt")

# Asegurar que existe la carpeta
os.makedirs(os.path.dirname(RUTA_PRODUCTOS), exist_ok=True)


# -------------------------------------------
#   LECTURA Y GUARDADO DE PRODUCTOS
# -------------------------------------------

def leer_productos():
    productos = []
    try:
        with open(RUTA_PRODUCTOS, "r", encoding="utf-8") as f:
            for linea in f:
                datos = linea.strip().split("|")
                if len(datos) == 7:
                    productos.append({
                        "codigo": datos[0],
                        "nombre": datos[1],
                        "categoria": datos[2],
                        "precio": float(datos[3]),
                        "stock": int(datos[4]),
                        "almacen": datos[5],
                        "estado": datos[6],
                    })
    except FileNotFoundError:
        # Crear archivo si no existe
        with open(RUTA_PRODUCTOS, "w", encoding="utf-8") as f:
            pass

    return productos


def guardar_productos(lista):
    with open(RUTA_PRODUCTOS, "w", encoding="utf-8") as f:
        for p in lista:
            linea = f"{p['codigo']}|{p['nombre']}|{p['categoria']}|{p['precio']}|{p['stock']}|{p['almacen']}|{p['estado']}\n"
            f.write(linea)


# -------------------------------------------
#   GENERAR CÓDIGO AUTOMÁTICO
# -------------------------------------------

def generar_codigo(productos):
    numero = len(productos) + 1
    return f"PRD{numero:03d}"


# -------------------------------------------
#   CRUD
# -------------------------------------------

def crear_producto():
    productos = leer_productos()

    codigo = generar_codigo(productos)
    nombre = input("Nombre del producto: ")
    categoria = input("Categoría: ")

    # Validar entrada numérica para precio
    while True:
        try:
            precio = float(input("Precio: "))
            break
        except ValueError:
            print("Error: Debe ingresar un número válido para el precio")

    # Validar entrada numérica para stock
    while True:
        try:
            stock = int(input("Stock inicial: "))
            break
        except ValueError:
            print("Error: Debe ingresar un número entero válido para el stock")

    almacen = input("Código de almacén asignado (ALM001...): ")

    nuevo = {
        "codigo": codigo,
        "nombre": nombre,
        "categoria": categoria,
        "precio": precio,
        "stock": stock,
        "almacen": almacen,
        "estado": "ACTIVO"
    }

    productos.append(nuevo)
    guardar_productos(productos)

    print("Producto registrado correctamente.")


def listar_productos():
    productos = leer_productos()
    print("\n=== LISTA DE PRODUCTOS ===")
    for p in productos:
        print(
            f"{p['codigo']} - {p['nombre']} ({p['categoria']}) [{p['estado']}] - Stock: {p['stock']} - Precio: ${p['precio']:.2f}")


def actualizar_producto():
    productos = leer_productos()
    codigo = input("Código del producto a modificar: ")

    for p in productos:
        if p["codigo"] == codigo:
            print("Deja en blanco para mantener el valor actual.")

            nombre = input(f"Nuevo nombre ({p['nombre']}): ") or p['nombre']
            categoria = input(f"Nueva categoría ({p['categoria']}): ") or p['categoria']

            # Manejo seguro de precio
            precio_input = input(f"Nuevo precio ({p['precio']}): ")
            if precio_input:
                try:
                    p["precio"] = float(precio_input)
                except ValueError:
                    print("Error: Precio no válido. Se mantiene el anterior.")

            # Manejo seguro de stock
            stock_input = input(f"Nuevo stock ({p['stock']}): ")
            if stock_input:
                try:
                    p["stock"] = int(stock_input)
                except ValueError:
                    print("Error: Stock no válido. Se mantiene el anterior.")

            almacen = input(f"Nuevo almacén ({p['almacen']}): ") or p['almacen']

            p["nombre"] = nombre
            p["categoria"] = categoria
            p["almacen"] = almacen

            guardar_productos(productos)
            print("Producto actualizado.")
            return

    print("Código no encontrado.")


def desactivar_producto():
    productos = leer_productos()
    codigo = input("Código del producto a desactivar: ")

    for p in productos:
        if p["codigo"] == codigo:
            p["estado"] = "INACTIVO"
            guardar_productos(productos)
            print("Producto desactivado.")
            return

    print("Código no encontrado.")