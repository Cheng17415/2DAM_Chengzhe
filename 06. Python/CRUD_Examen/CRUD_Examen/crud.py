import csv
import json
import os

ARCHIVO_PROVINCIAS = "provincias.json"
ARCHIVO_AGENDA = "agenda.json"
ARCHIVO_DNI = "dni.json"

# ------------------ UTILIDADES ------------------

def cargar_datos(ruta, tipo=dict):
    if not os.path.exists(ruta):
        return [] if tipo == list else {}
    try:
        with open(ruta, 'r', encoding='utf-8') as f:
            return json.load(f)
    except:
        return [] if tipo == list else {}

def guardar_json(ruta, datos):
    try:
        with open(ruta, 'w', encoding='utf-8') as f:
            json.dump(datos, f, indent=4, ensure_ascii=False)
        return True
    except Exception as e:
        print(f"Error al guardar: {e}")
        return False

def preguntar_si_no(mensaje):
    while True:
        r = input(mensaje + " [SI/NO]: ").strip().upper()
        if r in ("SI", "S"): return True
        if r in ("NO", "N"): return False
        print("Respuesta inválida, use solo SI o NO")

def obtener_provincia(cp, dicc):
    cp_str = str(cp)
    if len(cp_str) < 2:
        return "CP Inválido"
    return dicc.get(cp_str[:2], "Provincia Desconocida")

def pedir_categoria():
    categorias = {"1": "Trabajo", "2": "Familia", "3": "Amigo", "4": "Conocido"}
    while True:
        for k,v in categorias.items(): print(f"{k}. {v}")

        opcion = input("Opción (1-4): ").strip()
        if not opcion: return "Sin Categoría"
        if opcion.upper() == "SALIR": return None
        if opcion in categorias: return categorias[opcion]
        print("Opción inválida (1-4)")

def validar_dni_nie(dni):
    dni = dni.upper().strip()
    if len(dni) != 9: return False  # Validación rápida de longitud

    # 1. Convertir NIE (X, Y, Z) a número (0, 1, 2)
    traductor_nie = {"X": "0", "Y": "1", "Z": "2"}
    if dni[0] in traductor_nie:
        dni = traductor_nie[dni[0]] + dni[1:]

    # 2. Extraer número y letra
    numero = dni[:-1]
    letra_introducida = dni[-1]

    if not numero.isdigit(): return False

    # 3. El "truco" de la cadena de caracteres (Algoritmo oficial)
    letras_validas = "TRWAGMYFPDXBNJZSQVHLCKE"
    letra_correcta = letras_validas[int(numero) % 23]

    return letra_introducida == letra_correcta

# ------------------ CRUD ------------------

def alta():
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    while True:
        print("Ingrese salir para cancelar")
        dni = input("DNI del nuevo contacto (SALIR para cancelar): ").strip().upper()
        if not dni or dni == "SALIR": return
        if dni in agenda:
            c = agenda[dni]
            print("DNI ya registrado:", dni, c["nombre"], c["apellido"])
            return
        if not validar_dni_nie(dni):
            print("DNI no válido")
            return
        nombre = input("Nombre: ").capitalize()
        if nombre.upper() == "SALIR":
            return
        apellido = input("Apellido: ").capitalize()
        if apellido.upper() == "SALIR":
            return
        while True:
            cp = input("CP: ")
            provs = cargar_datos(ARCHIVO_PROVINCIAS, tipo=dict)
            provincia = obtener_provincia(cp, provs)
            print("Provincia detectada:", provincia)
            if preguntar_si_no("¿Es correcta la provincia?"): break
        ciudad = input("Ciudad: ")
        if ciudad.upper() == "SALIR":
            return
        direccion = input("Dirección: ")
        if direccion.upper() == "SALIR":
            return
        telefono = input("Teléfono: ")
        if telefono.upper() == "SALIR":
            return
        email = input("Email: ")
        if email.upper() == "SALIR":
            return
        categoria = pedir_categoria()

        agenda[dni] = {
            "documento": dni, "nombre": nombre, "apellido": apellido,
            "cp": cp, "provincia": provincia, "ciudad": ciudad,
            "direccion": direccion, "telefono": telefono, "email": email,
            "categoria": categoria
        }
        guardar_json(ARCHIVO_AGENDA, agenda)
        print("Contacto dado de alta correctamente")

        if not preguntar_si_no("¿Registrar otro contacto?"): break


def listar(filtro_cat=None):
    """Listar contactos ordenados por apellido o DNI, con opción de filtrado"""
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    if not agenda:
        print("Agenda vacía")
        return

    # Convertimos a lista para poder manipular y ordenar
    contactos = list(agenda.values())

    # --- LÓGICA DE FILTRADO ---
    if filtro_cat:
        contactos = [c for c in contactos if c['categoria'] == filtro_cat]
        if not contactos:
            print(f"No hay contactos en la categoría: {filtro_cat}")
            return
        print(f"\n--- Listado de categoría: {filtro_cat} ---")
    else:
        print("\n--- Listado Completo ---")

    # --- LÓGICA DE ORDENACIÓN ---
    print("Ordenar por: 1. Apellido | 2. DNI")
    opcion = input("Seleccione opción (1-2, ENTER = Apellido): ").strip()

    if opcion == "2":
        contactos.sort(key=lambda c: c["documento"])
    else:
        contactos.sort(key=lambda c: c["apellido"])

    # --- IMPRESIÓN ---
    for c in contactos:
        print(f"{c['documento']} - {c['nombre']} {c['apellido']} [{c['categoria']}]")

def modificar():
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    if not agenda: print("Agenda vacía"); return
    while True:
        dni = input("DNI a modificar (SALIR para cancelar): ").strip().upper()
        if not dni or dni=="SALIR": return
        if dni not in agenda:
            print("No existe ese contacto")
            if not preguntar_si_no("¿Intentar otro DNI?"): return
            continue
        c = agenda[dni].copy()
        print("Datos actuales:", c)
        nombre = input(f"Nuevo nombre [{c['nombre']}]: ").capitalize()
        apellido = input(f"Nuevo apellido [{c['apellido']}]: ").capitalize()
        ciudad = input(f"Nueva ciudad [{c['ciudad']}]: ")
        direccion = input(f"Nueva dirección [{c['direccion']}]: ")
        telefono = input(f"Nuevo teléfono [{c['telefono']}]: ")
        email = input(f"Nuevo email [{c['email']}]: ")
        categoria = pedir_categoria() or c["categoria"]
        nuevo_dni = input(f"Nuevo DNI [{c['documento']}]: ").strip().upper()

        if nombre.upper() == "SALIR":
            return
        if nombre: c['nombre']=nombre

        if apellido.upper() == "SALIR":
            return
        if apellido: c['apellido']=apellido

        if ciudad.upper() == "SALIR":
            return
        if ciudad: c['ciudad']=ciudad

        if direccion.upper() == "SALIR":
            return
        if direccion: c['direccion']=direccion

        if telefono.upper() == "SALIR":
            return
        if telefono: c['telefono']=telefono

        if email.upper() == "SALIR":
            return
        if email: c['email']=email
        c['categoria']=categoria
        if nuevo_dni and nuevo_dni!=c['documento']:
            if nuevo_dni in agenda:
                print("Ese DNI ya existe, no se puede cambiar")
            else:
                agenda.pop(c['documento'])
                c['documento']=nuevo_dni
                dni=nuevo_dni
        agenda[dni]=c
        guardar_json(ARCHIVO_AGENDA, agenda)
        print("Contacto modificado correctamente")
        if not preguntar_si_no("¿Modificar otro contacto?"): break

def baja():
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    if not agenda: print("Agenda vacía"); return
    while True:
        dni = input("DNI a eliminar (SALIR para cancelar): ").strip().upper()
        if not dni or dni=="SALIR": return
        if dni not in agenda:
            print("No existe ese contacto")
            if not preguntar_si_no("¿Intentar otro DNI?"): return
            continue
        c = agenda[dni]
        print(f"{c['documento']} - {c['nombre']} {c['apellido']}")
        if preguntar_si_no("¿Confirma eliminación?"):
            agenda.pop(dni)
            guardar_json(ARCHIVO_AGENDA, agenda)
            print("Contacto eliminado")
        if not preguntar_si_no("¿Eliminar otro contacto?"): return

# ------------------ EXTRAS ------------------

def buscar_por_nombre():
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    if not agenda: print("Agenda vacía"); return
    nombre = input("Nombre a buscar (SALIR para cancelar): ").strip().capitalize()
    if not nombre or nombre=="SALIR": return
    resultados = [c for c in agenda.values() if nombre in c["nombre"]]
    if resultados:
        for r in resultados: print(r['documento'], r['nombre'], r['apellido'])
    else:
        print("No se encontraron contactos")

def filtrar_por_categoria():
    print("\nIngrese salir si desea para cancelar la busqueda")
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    if not agenda: print("Agenda vacía"); return
    cat = pedir_categoria()
    if not cat or cat=="SALIR": return
    listar(filtrar_categoria=cat)

def exportar_csv():
    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    if not agenda:
        print("Agenda vacía, no se puede exportar")
        return

    nombre_archivo = input("Nombre del archivo CSV (ENTER para usar 'agenda.csv'): ").strip()
    if not nombre_archivo:
        nombre_archivo = "agenda.csv"

    campos = ["documento", "nombre", "apellido", "cp", "provincia",
              "ciudad", "direccion", "telefono", "email", "categoria"]

    try:
        with open(nombre_archivo, 'w', newline='', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=campos)
            writer.writeheader()
            for contacto in agenda.values():
                writer.writerow(contacto)
        print(f"Agenda exportada correctamente a {nombre_archivo}")
    except Exception as e:
        print("Error al exportar:", e)

def importar_csv():
    nombre_archivo = input("Nombre del archivo CSV a importar(ej: agenda.csv): ").strip()
    if not nombre_archivo:
        print("Debe indicar un archivo")
        return

    agenda = cargar_datos(ARCHIVO_AGENDA, tipo=dict)
    campos = ["documento", "nombre", "apellido", "cp", "provincia",
              "ciudad", "direccion", "telefono", "email", "categoria"]

    try:
        with open(nombre_archivo, 'r', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for fila in reader:
                dni = fila["documento"].upper()
                if dni in agenda:
                    print(f"Ignorado {dni}, ya existe")
                    continue
                agenda[dni] = {k: fila[k] for k in campos}
        guardar_json(ARCHIVO_AGENDA, agenda)
        print(f"Agenda importada desde {nombre_archivo}")
    except Exception as e:
        print("Error al importar:", e)