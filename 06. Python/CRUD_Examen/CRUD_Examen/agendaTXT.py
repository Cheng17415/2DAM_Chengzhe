import csv

ARCHIVO_PROVINCIAS = "provincias.txt"
ARCHIVO_AGENDA = "agenda.txt"
ARCHIVO_DNI = "dni.txt"

# ---------------------
# FUNCIONES DE TXT
# ---------------------
def guardar_txt(archivo, contacto):
    """Agrega un contacto al final del archivo TXT"""
    linea = ";".join([
        contacto["documento"], contacto["nombre"], contacto["apellido"],
        contacto["cp"], contacto["provincia"], contacto["ciudad"],
        contacto["direccion"], contacto["telefono"], contacto["email"],
        contacto["categoria"]
    ])
    with open(archivo, "a", encoding="utf-8") as f:
        f.write(linea + "\n")

def cargar_txt(archivo):
    """Carga contactos desde TXT en un diccionario"""
    agenda = {}
    try:
        with open(archivo, "r", encoding="utf-8") as f:
            for linea in f:
                campos = linea.strip().split(";")
                if len(campos) == 10:
                    dni = campos[0].upper()
                    agenda[dni] = {
                        "documento": campos[0],
                        "nombre": campos[1],
                        "apellido": campos[2],
                        "cp": campos[3],
                        "provincia": campos[4],
                        "ciudad": campos[5],
                        "direccion": campos[6],
                        "telefono": campos[7],
                        "email": campos[8],
                        "categoria": campos[9]
                    }
    except FileNotFoundError:
        pass
    return agenda

def guardar_toda_agenda_txt(archivo, agenda):
    """Reescribe todo el archivo TXT con la agenda actualizada"""
    with open(archivo, "w", encoding="utf-8") as f:
        for c in agenda.values():
            linea = ";".join([
                c["documento"], c["nombre"], c["apellido"],
                c["cp"], c["provincia"], c["ciudad"],
                c["direccion"], c["telefono"], c["email"], c["categoria"]
            ])
            f.write(linea + "\n")

# ---------------------
# FUNCIONES AUXILIARES
# ---------------------
def preguntar_si_no(mensaje):
    while True:
        respuesta = input(mensaje + " [SI/NO]: ").strip().upper()
        if respuesta in ("SI", "S"):
            return True
        elif respuesta in ("NO", "N"):
            return False
        else:
            print("Respuesta inválida. Use solo SI o NO")

def pedir_categoria(prompt):
    categorias = {"1": "Trabajo", "2": "Familia", "3": "Amigo", "4": "Conocido"}
    while True:
        print("Categorías disponibles:")
        for k, v in categorias.items():
            print(f"  {k}. {v}")
        opcion = input(prompt).strip()
        if not opcion:
            return "Sin Categoría"
        if opcion.upper() == "SALIR":
            return None
        if opcion in categorias:
            return categorias[opcion]
        print("Opción fuera de rango (1-4)")

def obtener_provincia(cp_completo, diccionario_provincias):
    cp_str = str(cp_completo)
    if len(cp_str) < 2:
        return "CP Inválido"
    prefijo = cp_str[:2]
    return diccionario_provincias.get(prefijo, "Provincia Desconocida")

# ---------------------
# CRUD
# ---------------------
def alta():
    agenda = cargar_txt(ARCHIVO_AGENDA)

    print("Indique el DNI a consultar (SALIR para cancelar)")
    dni = input("DNI: ").strip().upper()
    if not dni or dni == "SALIR":
        return

    if dni in agenda:
        contacto = agenda[dni]
        print("DOCUMENTO YA REGISTRADO:", dni, contacto["nombre"], contacto["apellido"])
        return

    # Para simplificar, omitimos validación de DNI/NIE
    nombre = input("Nombre: ").capitalize()
    if nombre.upper() == "SALIR": return
    apellido = input("Apellido: ").capitalize()
    if apellido.upper() == "SALIR": return

    # Provincias
    lectura_prov = {}  # para examen podemos usar vacío o cargar de archivo
    while True:
        cp = input("CP: ")
        if cp.upper() == "SALIR": return
        provincia = obtener_provincia(cp, lectura_prov)
        print(f"Provincia detectada: {provincia}")
        if preguntar_si_no("¿Es correcta la provincia?"):
            break

    ciudad = input("Ciudad: ")
    if ciudad.upper() == "SALIR": return
    direccion = input("Dirección: ")
    if direccion.upper() == "SALIR": return
    telefono = input("Teléfono: ")
    if telefono.upper() == "SALIR": return
    email = input("Email: ")
    if email.upper() == "SALIR": return
    categoria = pedir_categoria("Categoría: ")
    if categoria is None: return

    nuevo_usuario = {
        "documento": dni,
        "nombre": nombre,
        "apellido": apellido,
        "cp": cp,
        "provincia": provincia,
        "ciudad": ciudad,
        "direccion": direccion,
        "telefono": telefono,
        "email": email,
        "categoria": categoria
    }

    guardar_txt(ARCHIVO_AGENDA, nuevo_usuario)
    print("Usuario dado de alta correctamente")

# ---------------------
def listar():
    agenda = cargar_txt(ARCHIVO_AGENDA)
    if not agenda:
        print("Agenda vacía")
        return

    print("Ordenar por: 1-Apellido  2-DNI")
    opcion = input("Seleccione opción (ENTER = Apellido): ").strip()
    contactos = list(agenda.values())
    if opcion == "2":
        contactos.sort(key=lambda c: c["documento"])
    else:
        contactos.sort(key=lambda c: c["apellido"])

    print("CONTACTOS:")
    for c in contactos:
        print(f"{c['documento']} - {c['nombre']} {c['apellido']} ({c['categoria']})")

# ---------------------
def modificar():
    agenda = cargar_txt(ARCHIVO_AGENDA)
    if not agenda:
        print("Agenda vacía")
        return

    while True:
        dni_actual = input("DNI del contacto a modificar (SALIR para cancelar): ").strip().upper()
        if not dni_actual or dni_actual == "SALIR":
            return
        if dni_actual not in agenda:
            print("No se encontró contacto con DNI", dni_actual)
            if not preguntar_si_no("¿Desea intentar otro DNI?"):
                return
            continue

        contacto = agenda[dni_actual]

        # Editar campos
        for campo in ["nombre", "apellido", "ciudad", "direccion", "telefono", "email"]:
            valor = input(f"{campo.capitalize()} [{contacto[campo]}]: ").strip()
            if valor.upper() == "SALIR": return
            if valor: contacto[campo] = valor

        categoria = pedir_categoria(f"Categoría [{contacto['categoria']}]: ")
        if categoria is None: return
        contacto["categoria"] = categoria

        # Cambiar DNI
        nuevo_dni = input(f"Nuevo DNI [{contacto['documento']}]: ").strip().upper()
        if nuevo_dni.upper() == "SALIR": return
        if nuevo_dni and nuevo_dni != dni_actual:
            if nuevo_dni in agenda:
                print("Ese DNI ya existe. No se puede cambiar.")
            else:
                agenda.pop(dni_actual)
                contacto["documento"] = nuevo_dni
                dni_actual = nuevo_dni

        agenda[dni_actual] = contacto
        guardar_toda_agenda_txt(ARCHIVO_AGENDA, agenda)
        print("Contacto modificado correctamente")

        if not preguntar_si_no("¿Desea modificar otro contacto?"):
            break

# ---------------------
def baja():
    agenda = cargar_txt(ARCHIVO_AGENDA)
    if not agenda:
        print("Agenda vacía")
        return

    while True:
        dni = input("DNI del contacto a eliminar (SALIR para cancelar): ").strip().upper()
        if not dni or dni == "SALIR": return

        if dni not in agenda:
            print("No se encontró contacto con DNI", dni)
            if not preguntar_si_no("¿Desea intentar otro DNI?"):
                return
            continue

        contacto = agenda[dni]
        print(f"{contacto['documento']} - {contacto['nombre']} {contacto['apellido']}")
        if preguntar_si_no("¿Está seguro de eliminar este contacto?"):
            agenda.pop(dni)
            guardar_toda_agenda_txt(ARCHIVO_AGENDA, agenda)
            print("Contacto eliminado")
        else:
            print("Baja cancelada")

        if not preguntar_si_no("¿Desea eliminar otro contacto?"):
            break

# ---------------------
# CSV
# ---------------------
def exportar_csv():
    agenda = cargar_txt(ARCHIVO_AGENDA)
    if not agenda:
        print("Agenda vacía")
        return

    nombre_archivo = input("Nombre archivo CSV (ENTER = agenda.csv): ").strip()
    if not nombre_archivo: nombre_archivo = "agenda.csv"

    campos = ["documento","nombre","apellido","cp","provincia","ciudad","direccion","telefono","email","categoria"]

    with open(nombre_archivo,"w",newline="",encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=campos)
        writer.writeheader()
        for c in agenda.values():
            writer.writerow(c)
    print("Agenda exportada a CSV correctamente")

def importar_csv():
    nombre_archivo = input("Nombre archivo CSV a importar: ").strip()
    if not nombre_archivo: return
    agenda = cargar_txt(ARCHIVO_AGENDA)
    campos = ["documento","nombre","apellido","cp","provincia","ciudad","direccion","telefono","email","categoria"]

    try:
        with open(nombre_archivo,"r",encoding="utf-8") as f:
            reader = csv.DictReader(f)
            for fila in reader:
                dni = fila["documento"].upper()
                if dni in agenda:
                    print(f"⚠ Ignorado {dni}, ya existe")
                    continue
                agenda[dni] = {k: fila.get(k,"") for k in campos}
        guardar_toda_agenda_txt(ARCHIVO_AGENDA, agenda)
        print("Agenda importada desde CSV correctamente")
    except FileNotFoundError:
        print("Archivo no encontrado")

# ---------------------
# MENU
# ---------------------
def menu():
    while True:
        print("\n--- AGENDA ---")
        print("1. Alta")
        print("2. Baja")
        print("3. Modificar")
        print("4. Listar")
        print("5. Exportar CSV")
        print("6. Importar CSV")
        print("0. Salir")

        opcion = input("Seleccione opción: ").strip()
        match opcion:
            case "1": alta()
            case "2": baja()
            case "3": modificar()
            case "4": listar()
            case "5": exportar_csv()
            case "6": importar_csv()
            case "0": print("Saliendo..."); break
            case _: print("Opción inválida")

# ---------------------
# INICIO
# ---------------------
if __name__ == "__main__":
    menu()
