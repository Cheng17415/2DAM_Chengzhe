from persona import Persona, EstadoPersona, obtener_descripcion
import dni
def usuarios_por_ciudad():
    ruta_fichero = "agenda/base_datos/agenda.txt"
    ciudades = {}

    try:
        with open(ruta_fichero, "r", encoding="utf-8") as fichero:
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
    ruta_agenda = "agenda/base_datos/agenda.txt"
    ruta_codigos_postales = "agenda/base_datos/codigos_postales.txt"

    try:
        with open(ruta_codigos_postales, "r", encoding="utf-8") as f:
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
        with open(ruta_agenda, "r", encoding="utf-8") as f:
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

def filtrar_por_descripcion(descripcion_filtro):
    ruta_agenda = "agenda/base_datos/agenda.txt"
    personas_filtradas = []

    try:
        with open(ruta_agenda, "r", encoding="utf-8") as fichero:
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
    ruta_agenda = "agenda/base_datos/agenda.txt"
    personas_filtradas = []

    try:
        with open(ruta_agenda, "r", encoding="utf-8") as fichero:
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