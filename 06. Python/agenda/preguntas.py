from persona import Persona, EstadoPersona, obtener_descripcion
import dni
import os
import csv
import matplotlib.pyplot as plt
def usuarios_por_ciudad():
    RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
    ruta_fichero = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")
    ciudades = {}

    try:
        with open(ruta_fichero, "r", encoding="latin-1") as fichero:
            for linea in fichero:
                datos = linea.strip().split(";")

                if len(datos) < 9:
                    print(f"Línea ignorada por formato incorrecto: {linea.strip()}")
                    continue

                ciudad = datos[5]
                persona = Persona(
                    nombre=datos[1],
                    apellidos=datos[2],
                    direccion=datos[3],
                    codigoPostal=datos[4],
                    ciudad=datos[5],
                    telefono=datos[6],
                    email=datos[7],
                    descripcion=datos[8]
                )

                if ciudad in ciudades:
                    ciudades[ciudad].append(persona)
                else:
                    ciudades[ciudad] = [persona]
    except FileNotFoundError:
        print("No se encontró el fichero de usuarios.")
        return {}
    except Exception as e:
        print(f"Error al procesar el fichero: {e}")
        return {}

    return ciudades

def visualizar_usuarios_por_ciudad():
    ciudades = usuarios_por_ciudad()
    if len(ciudades) > 0:
        print("\nUsuarios por ciudad:")
        for ciudad, personas in ciudades.items():
            print(f"{ciudad}: {len(personas)}")
            for persona in personas:
                print(f"  - {persona.nombre} {persona.apellidos}")

def usuarios_por_provincia():
    provincias = {}
    codigos_a_provincias = {}
    RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
    ruta_agenda = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")
    ruta_codigos_postales = os.path.join(RUTA_ACTUAL, "base_datos/codigos_postales.txt")

    try:
        with open(ruta_codigos_postales, "r", encoding="latin-1") as f:
            for linea in f:
                partes = linea.strip().split(";")
                if len(partes) == 2:
                    codigo, provincia = partes
                    codigos_a_provincias[codigo] = provincia
    except FileNotFoundError:
        print("No se encontró el archivo de códigos postales.")
        return {}
    except Exception as e:
        print(f"Error al leer códigos postales: {e}")
        return {}

    try:
        with open(ruta_agenda, "r", encoding="latin-1") as f:
            for linea in f:
                datos = linea.strip().split(";")
                if len(datos) >= 9:
                    codigo_postal = datos[4][:2]
                    provincia = codigos_a_provincias.get(codigo_postal)

                    if provincia:
                        persona = Persona(
                            nombre=datos[1],
                            apellidos=datos[2],
                            direccion=datos[3],
                            codigoPostal=datos[4],
                            ciudad=datos[5],
                            telefono=datos[6],
                            email=datos[7],
                            descripcion=datos[8]
                        )

                        if provincia in provincias:
                            provincias[provincia].append(persona)
                        else:
                            provincias[provincia] = [persona]
    except FileNotFoundError:
        print("No se encontró el archivo de agenda.")
        return {}
    except Exception as e:
        print(f"Error al leer la agenda: {e}")
        return {}

    return provincias

def visualizar_usuarios_por_provincia():
    provincias = usuarios_por_provincia()
    if len(provincias) > 0:
        print("\nUsuarios por provincia:")
        for provincia, personas in provincias.items():
            print(f"{provincia}: {len(personas)}")
            for persona in personas:
                print(f"  - {persona.nombre} {persona.apellidos}")
        
        # Gráfico con matplotlib
        provincias_nombres = list(provincias.keys())
        cantidad_usuarios = [len(personas) for personas in provincias.values()]
        
        plt.figure(figsize=(12, 6))
        plt.bar(provincias_nombres, cantidad_usuarios, color='skyblue', edgecolor='navy')
        plt.xlabel('Provincia')
        plt.ylabel('Cantidad de Usuarios')
        plt.title('Usuarios por Provincia')
        plt.xticks(rotation=45, ha='right')
        plt.tight_layout()
    respuesta = input("¿Quieres visualizar la tabla? s/n ")
    if respuesta.lower() == "s":
        plt.show()

def filtrar_por_descripcion(descripcion_filtro):
    RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
    ruta_agenda = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")
    personas_filtradas = []

    try:
        with open(ruta_agenda, "r", encoding="latin-1") as fichero:
            for linea in fichero:
                datos = linea.strip().split(";")

                if len(datos) < 9:
                    print(f"Línea ignorada por formato incorrecto: {linea.strip()}")
                    continue

                try:
                    descripcion_enum = EstadoPersona(datos[8].strip().lower())
                except ValueError:
                    print(f"Descripción desconocida: {datos[8]}. Se asignará 'ACTIVO'.")
                    descripcion_enum = EstadoPersona.ACTIVO

                if descripcion_enum == EstadoPersona[descripcion_filtro.upper()]:
                    persona = Persona(
                        nombre=datos[1],
                        apellidos=datos[2],
                        direccion=datos[3],
                        codigoPostal=datos[4],
                        ciudad=datos[5],
                        telefono=datos[6],
                        email=datos[7],
                        descripcion=descripcion_enum,
                        id=datos[0]
                    )
                    personas_filtradas.append(persona)
    except FileNotFoundError:
        print("No se encontró el archivo de agenda.")
    except Exception as e:
        print(f"Error al leer la agenda: {e}")

    return personas_filtradas

def visualizar_filtrar_por_descripcion():
    for p in filtrar_por_descripcion(obtener_descripcion().value):
        print(p.to_str())

def filtrar_por_nombre(nombre_filtro):
    RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
    ruta_agenda = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")
    personas_filtradas = []

    try:
        with open(ruta_agenda, "r", encoding="latin-1") as fichero:
            for linea in fichero:
                datos = linea.strip().split(";")

                if len(datos) < 9:
                    print(f"Línea ignorada por formato incorrecto: {linea.strip()}")
                    continue

                if nombre_filtro.lower() in datos[1].strip().lower():
                    persona = Persona(
                        nombre=datos[1],
                        apellidos=datos[2],
                        direccion=datos[3],
                        codigoPostal=datos[4],
                        ciudad=datos[5],
                        telefono=datos[6],
                        email=datos[7],
                        descripcion=datos[8],
                        id=datos[0]
                    )
                    personas_filtradas.append(persona)
    except FileNotFoundError:
        print("No se encontró el archivo de agenda.")
    except Exception as e:
        print(f"Error al leer la agenda: {e}")

    return personas_filtradas

def visualizar_filtrar_por_nombre():
    nombre_filtro = input("Introduce el nombre a buscar: ").strip()
    personas = filtrar_por_nombre(nombre_filtro)
    if personas:
        print("\nUsuarios encontrados:")
        for persona in personas:
            print(persona.to_str())
    else:
        print("No se encontraron usuarios con ese nombre.")

def filtrar_por_telefono(telefono_filtro):
    RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
    ruta_agenda = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")
    personas_filtradas = []

    try:
        with open(ruta_agenda, "r", encoding="latin-1") as fichero:
            for linea in fichero:
                datos = linea.strip().split(";")

                if len(datos) < 9:
                    print(f"Línea ignorada por formato incorrecto: {linea.strip()}")
                    continue

                if telefono_filtro.lower() in datos[6].strip().lower():
                    persona = Persona(
                        nombre=datos[1],
                        apellidos=datos[2],
                        direccion=datos[3],
                        codigoPostal=datos[4],
                        ciudad=datos[5],
                        telefono=datos[6],
                        email=datos[7],
                        descripcion=datos[8],
                        id=datos[0]
                    )
                    personas_filtradas.append(persona)
    except FileNotFoundError:
        print("No se encontró el archivo de agenda.")
    except Exception as e:
        print(f"Error al leer la agenda: {e}")

    return personas_filtradas

def visualizar_filtrar_por_telefono():
    telefono_filtro = input("Introduce el número de teléfono a buscar: ").strip()
    personas = filtrar_por_telefono(telefono_filtro)
    if personas:
        print("\nUsuarios encontrados:")
        for persona in personas:
            print(persona.to_str())
    else:
        print("No se encontraron usuarios con ese número de teléfono.")

def exportar_csv():
    RUTA_ACTUAL = os.path.dirname(os.path.abspath(__file__))
    ruta_agenda = os.path.join(RUTA_ACTUAL, "base_datos/agenda.txt")
    ruta_csv = os.path.join(RUTA_ACTUAL, "base_datos/agenda_export.csv")
    
    personas_list = []
    
    try:
        with open(ruta_agenda, "r", encoding="latin-1") as fichero:
            for linea in fichero:
                datos = linea.strip().split(";")
                
                if len(datos) < 9:
                    continue
                
                persona = Persona(
                    nombre=datos[1],
                    apellidos=datos[2],
                    direccion=datos[3],
                    codigoPostal=datos[4],
                    ciudad=datos[5],
                    telefono=datos[6],
                    email=datos[7],
                    descripcion=datos[8],
                    id=datos[0]
                )
                personas_list.append(persona)
        
        # Escribir en CSV
        with open(ruta_csv, "w", newline="", encoding="latin-1") as csvfile:
            fieldnames = ["ID", "Nombre", "Apellidos", "Dirección", "Código Postal", "Provincia", "Ciudad", "Teléfono", "Email", "Estado"]
            writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
            
            writer.writeheader()
            for persona in personas_list:
                dni_persona = dni.obtener_DNI(persona.id)
                writer.writerow({
                    "ID": persona.id,
                    "Nombre": persona.nombre,
                    "Apellidos": persona.apellidos,
                    "Dirección": persona.direccion,
                    "Código Postal": persona.codigoPostal,
                    "Provincia": persona.provincia,
                    "Ciudad": persona.ciudad,
                    "Teléfono": persona.telefono,
                    "Email": persona.email,
                    "Estado": persona.descripcion.value
                })
        
        print(f"\n✓ Archivo exportado correctamente: {ruta_csv}")
        print(f"Total de contactos exportados: {len(personas_list)}")
        
    except FileNotFoundError:
        print("No se encontró el archivo de agenda.")
    except Exception as e:
        print(f"Error al exportar a CSV: {e}")