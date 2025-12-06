def cargar_productos():
    productos = {}
    with open("base_datos/ventas.txt", "r", encoding="utf-8") as f:
        next(f)
        for linea in f:
            if linea.strip():
                idp, nombre, precio, stock = linea.strip().split(";")
                productos[idp] = {
                    "nombre": nombre,
                    "precio": float(precio),
                    "stock": int(float(stock))
                }
    return productos


def guardar_productos(productos):
    with open("base_datos/ventas.txt", "w", encoding="utf-8") as f:
        f.write("ID;Nombre;Precio;Stock\n")
        for idp, data in productos.items():
            f.write(f"{idp};{data['nombre']};{data['precio']};{data['stock']}\n")


def registrar_venta(productos):
    id_producto = input("Introduce el ID del producto vendido: ")

    if id_producto not in productos:
        print("❌ Ese producto no existe.")
        return

    cantidad = int(input("¿Cuántas unidades deseas vender?: "))

    if cantidad > productos[id_producto]["stock"]:
        print("❌ No hay suficiente stock.")
        return

    # Actualizar stock
    productos[id_producto]["stock"] -= cantidad

    total = productos[id_producto]["precio"] * cantidad

    # Guardar la venta
    with open("ventas.txt", "a", encoding="utf-8") as f:
        id_venta = sum(1 for _ in open("ventas.txt", "r", encoding="utf-8"))
        f.write(f"{id_venta};{id_producto};{cantidad};{total}\n")

    print("✔ Venta registrada correctamente.")


def listar_productos(productos):
    print("\n📦 LISTA DE PRODUCTOS:")
    for idp, data in productos.items():
        print(f"{idp} - {data['nombre']} | Precio: {data['precio']}€ | Stock: {data['stock']}")


def buscar_producto(productos):
    nombre = input("Introduce el nombre del producto a buscar: ").lower()
    print("\n🔍 RESULTADOS DE LA BÚSQUEDA:")

    for idp, data in productos.items():
        if nombre in data["nombre"].lower():
            print(f"{idp} - {data['nombre']} | Precio: {data['precio']}€ | Stock: {data['stock']}")


def productos_vendidos(productos):
    try:
        with open("ventas.txt", "r", encoding="utf-8") as f:
            lineas = f.readlines()
        if not lineas:
            print("\n📄 No se han registrado ventas aún.")
            return
        print("\n🛒 PRODUCTOS VENDIDOS:")
        for linea in lineas:
            if linea.strip():
                id_venta, id_producto, cantidad, total = linea.strip().split(";")
                nombre = productos.get(id_producto, {}).get("nombre", "Desconocido")
                print(f"Venta {id_venta} - {nombre} | Cantidad: {cantidad} | Total: {total}€")
    except FileNotFoundError:
        print("❌ No se ha encontrado el archivo ventas.txt.")



def menu():
    productos = cargar_productos()

    while True:
        print("\n==== MENÚ PRINCIPAL ====")
        print("1 - Listar productos")
        print("2 - Registrar una venta")
        print("3 - Buscar un producto")
        print("4 - productos vendidos")
        print("5 - Salir")

        opcion = input("Selecciona una opción: ")

        if opcion == "1":
            listar_productos(productos)
        elif opcion == "2":
            registrar_venta(productos)
            guardar_productos(productos)
        elif opcion == "3":
            buscar_producto(productos)
        elif opcion == "4":
            productos_vendidos(productos)
        elif opcion == "5":
            print("Saliendo del programa...")
            break
        else:
            print("Opción no válida. Inténtalo de nuevo.")