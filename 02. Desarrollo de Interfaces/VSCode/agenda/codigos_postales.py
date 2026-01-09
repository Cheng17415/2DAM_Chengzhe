RUTA_CP = "agenda/base_datos/codigos_postales.txt"
CPs = {}

def obtenerCPs():
    CPs.clear()
    try:
        with open(RUTA_CP, "r", encoding="utf-8") as f:
            for linea in f:
                partes = linea.strip().split(",")
                CPs[partes[0]] = partes[1]
    except FileNotFoundError:
        print(f"Archivo '{RUTA_CP}' no encontrado.")

def obtenerProvincia(CP):
    return CPs.get(CP)

obtenerCPs()