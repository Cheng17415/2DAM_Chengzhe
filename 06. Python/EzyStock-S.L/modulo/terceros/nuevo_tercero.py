import os
from typing import List, Optional, Dict
from collections import Counter
import matplotlib.pyplot as plt
from datetime import datetime

# ====================================================================
# CONFIGURACIÓN DE RUTAS Y CONSTANTES
# ====================================================================
PROYECTO_DIR = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
BBDD_DIR = os.path.join(PROYECTO_DIR, "base_datos")
BBDD_FILE = os.path.join(BBDD_DIR, "terceros.txt")
EMPLEADOS_FILE = os.path.join(BBDD_DIR, "empleados.txt")  # Archivo de empleados para validación

TIPOS_VALIDOS = ["cliente", "proveedor"]
SEPARADOR = ";"


# ====================================================================
# CLASES DE MODELO Y GESTIÓN
# ====================================================================
class Tercero:
    """Representa un tercero (cliente o proveedor) con trazabilidad del último empleado que lo modificó."""

    def __init__(self, id: int, nombre: str, tipo: str, direccion: str, nro_fiscal: str, email: str, telefono: str,
                 empleado_id: Optional[int] = None):
        self.id = int(id)
        self.nombre = nombre
        self.tipo = tipo
        self.direccion = direccion
        self.nro_fiscal = nro_fiscal
        self.email = email
        self.telefono = telefono
        self.empleado_id = int(empleado_id) if empleado_id else None

    def __str__(self) -> str:
        """Representación en string para imprimir (Incluye Empleado ID)"""
        emp_id_display = self.empleado_id if self.empleado_id is not None else 'N/A'
        return (f"ID: {self.id} | NOMBRE: {self.nombre} | TIPO: {self.tipo.upper()} | "
                f"DIRECCIÓN: {self.direccion} | NRO. FISCAL: {self.nro_fiscal} | "
                f"EMAIL: {self.email} | TELÉFONO: {self.telefono} | EMP. ID: {emp_id_display}")

    def to_line(self) -> str:
        """Convierte el tercero a una línea para archivo de texto (8 campos)"""
        emp_id_str = str(self.empleado_id) if self.empleado_id is not None else ""
        return f"{self.id}{SEPARADOR}{self.nombre}{SEPARADOR}{self.tipo}{SEPARADOR}" \
               f"{self.direccion}{SEPARADOR}{self.nro_fiscal}{SEPARADOR}" \
               f"{self.email}{SEPARADOR}{self.telefono}{SEPARADOR}{emp_id_str}"

    @staticmethod
    def from_line(linea: str) -> 'Tercero':
        """
        Crea un Tercero desde una línea del archivo (Acepta 7 u 8 campos)
        """
        partes = linea.strip().split(SEPARADOR)

        if len(partes) not in (7, 8):
            raise ValueError(
                f"Línea con formato incorrecto. Esperadas 7 u 8 partes, encontradas {len(partes)} en: '{linea.strip()}'")

        try:
            tercero_id = int(partes[0])
        except ValueError:
            raise ValueError(f"ID '{partes[0]}' no es un número válido en la línea: '{linea.strip()}'")

        empleado_id_str = partes[7] if len(partes) == 8 else None

        return Tercero(
            id=tercero_id,
            nombre=partes[1],
            tipo=partes[2],
            direccion=partes[3],
            nro_fiscal=partes[4],
            email=partes[5],
            telefono=partes[6],
            empleado_id=empleado_id_str
        )


class GestorTerceros:
    """Maneja todas las operaciones CRUD de terceros"""
    
    def __init__(self):
        self.terceros: List[Tercero] = []
        self.asegurar_directorio()
        self.cargar_terceros()

    def cargar_terceros(self):
        """Carga terceros desde el archivo de texto si existe"""
        self.terceros.clear()
        if os.path.exists(BBDD_FILE):
            with open(BBDD_FILE, 'r', encoding='utf-8') as f:
                for linea in f:
                    if linea.strip():
                        try:
                            self.terceros.append(Tercero.from_line(linea))
                        except ValueError as e:
                            print(f"Error cargando línea: {e}")

    def asegurar_directorio(self):
        """Crea el direcctorio BBDD si no existe"""
        if not os.path.exists(BBDD_DIR):
            os.makedirs(BBDD_DIR)

    def obtener_siguiente_id(self) -> int:
        """Calcula el proximo ID disponible (sin reciclar IDs)"""
        if not self.terceros:
            return 1
        return max(t.id for t in self.terceros) + 1

    def guardar_terceros(self) -> bool:
        """Guarda todos los terceros en el archivo de texto"""
        try:
            with open(BBDD_FILE, 'w', encoding='utf-8') as archivo:
                for tercero in self.terceros:
                    archivo.write(tercero.to_line() + "\n")
            return True
        except Exception as e:
            print(f"Error al guardar: {e}")
            return False

    @staticmethod
    def validar_datos(nombre: str, tipo: str, nro_fiscal: str, email: str) -> List[str]:
        """Valida los datos de un tercero"""
        errores = []
        if not nombre.strip(): errores.append("El nombre no puede estar vacío")
        if tipo.lower() not in TIPOS_VALIDOS: errores.append(f"Tipo debe ser: {' o '.join(TIPOS_VALIDOS)}")
        if not nro_fiscal.strip(): errores.append("El número fiscal no puede estar vacío")
        if not email.strip():
            errores.append("El email no puede estar vacío")
        elif '@' not in email:
            errores.append("El email debe contener '@'")
        return errores

    def nro_fiscal_existe(self, nro_fiscal: str, excluir_id: Optional[int] = None) -> bool:
        """Verifica si un número fiscal ya está registrado (excluye el ID actual en modificación)"""
        for t in self.terceros:
            if t.nro_fiscal == nro_fiscal.strip():
                if excluir_id is None or t.id != excluir_id:
                    return True
        return False

    def agregar_tercero(self, nombre: str, tipo: str, direccion: str,
                        nro_fiscal: str, email: str, telefono: str, empleado_id: int) -> Optional[Tercero]:
        """Agrega un nuevo tercero con validaciones y asigna ID de empleado."""
        errores = self.validar_datos(nombre, tipo, nro_fiscal, email)
        if self.nro_fiscal_existe(nro_fiscal):
            errores.append(f"El número fiscal '{nro_fiscal}' ya está registrado")

        if errores:
            print("\nERRORES DE VALIDACIÓN:")
            for error in errores: print(f"  - {error}")
            return None

        nuevo_tercero = Tercero(
            id=self.obtener_siguiente_id(),
            nombre=nombre.strip(),
            tipo=tipo.lower().strip(),
            direccion=direccion.strip(),
            nro_fiscal=nro_fiscal.strip(),
            email=email.strip(),
            telefono=telefono.strip(),
            empleado_id=empleado_id
        )

        self.terceros.append(nuevo_tercero)

        if self.guardar_terceros():
            print(f"\nTercero '{nombre}' guardado con éxito (ID: {nuevo_tercero.id})")
            return nuevo_tercero
        else:
            self.terceros.pop()
            return None

    def listar_terceros(self, tipo: Optional[str] = None) -> None:
        """Lista todos los terceros o filtra por tipo"""
        if not self.terceros:
            print("\nNo hay terceros registrados")
            return

        terceros_mostrar = self.terceros
        if tipo:
            terceros_mostrar = [t for t in self.terceros if t.tipo == tipo.lower()]
            if not terceros_mostrar:
                print(f"\nNo hay terceros de tipo '{tipo}'")
                return

        print(f"\n{'=' * 100}")
        titulo = "LISTADO DE TERCEROS"
        if tipo: titulo += f" - {tipo.upper()}S"
        print(f"{titulo} ({len(terceros_mostrar)} registros)")
        print(f"{'=' * 100}")

        for tercero in terceros_mostrar:
            print(tercero)

        print(f"{'=' * 100}\n")

    def buscar_por_id(self, id: int) -> Optional[Tercero]:
        """Busca un tercero por ID"""
        for tercero in self.terceros:
            if tercero.id == id: return tercero
        return None

    def buscar_por_nombre(self, nombre: str) -> List[Tercero]:
        """Busca terceros por nombre (búsqueda parcial, no distingue mayúsculas)"""
        nombre_lower = nombre.lower()
        return [t for t in self.terceros if nombre_lower in t.nombre.lower()]


# ====================================================================
# INSTANCIA GLOBAL (Singleton Simple) Y FUNCIONES DE SOPORTE
# ====================================================================
GESTOR_TERCEROS = GestorTerceros()

def obtener_ids_empleados_validos() -> List[int]:
    """Lee el archivo de empleados y retorna una lista de IDs válidos."""
    ids = []
    EMPLEADOS_SEPARADOR = ";"

    if os.path.exists(EMPLEADOS_FILE):
        with open(EMPLEADOS_FILE, 'r', encoding='utf-8') as f:
            for linea in f:
                linea = linea.strip()
                if not linea: continue

                try:
                    partes = linea.split(EMPLEADOS_SEPARADOR)
                    if partes:
                        ids.append(int(partes[0].strip()))
                except (IndexError, ValueError) as e:
                    continue
    else:
        print("Advertencia: Archivo de empleados no encontrado. Usando IDs simulados.")
        return [1, 2, 3, 4, 5]

    return ids


def seleccionar_empleado() -> Optional[int]:
    """Muestra la lista de empleados y pide al usuario que seleccione uno por ID."""
    print("\n--- SELECCIÓN DE EMPLEADO ---")

    print("--- LISTADO DE EMPLEADOS (Simulado) ---")
    print("ID: 1 | Sonia Cabrera\nID: 2 | Chengzhe Li\nID: 3 | Jose Goycochea\nID: 4 | Cindy\nID: 5 | Prueba Pete")
    print("-" * 40)

    ids_validos = obtener_ids_empleados_validos()

    while True:
        try:
            emp_id_str = input("Ingrese el ID del empleado que realiza la acción: ").strip()
            if not emp_id_str:
                return None

            emp_id = int(emp_id_str)

            if emp_id in ids_validos:
                return emp_id
            else:
                print(f"Error: ID de empleado '{emp_id}' no válido. Intente de nuevo o deje vacío para cancelar.")
        except ValueError:
            print("Error: El ID debe ser un número entero.")


# ====================================================================
# FUNCIONES DE MENÚ (Interfaz de Usuario)
# ====================================================================
def agregar_tercero_menu() -> None:
    print("\n--- AGREGAR NUEVO TERCERO ---")

    empleado_id = seleccionar_empleado()
    if empleado_id is None:
        print("Acción de creación cancelada.")
        return

    nombre = input("Nombre del tercero: ").strip()
    tipo = input("Tipo (cliente/proveedor): ").strip()

    if tipo.lower() == 'cliente':
        prompt_nro_fiscal = "DNI/CIF del Cliente"
    elif tipo.lower() == 'proveedor':
        prompt_nro_fiscal = "NIF/CIF del Proveedor"
    else:
        prompt_nro_fiscal = "Número Fiscal (DNI/NIF/CIF)"

    nro_fiscal = input(f"{prompt_nro_fiscal}: ").strip()

    direccion = input("Dirección: ").strip()
    email = input("Email: ").strip()
    telefono = input("Teléfono: ").strip()

    GESTOR_TERCEROS.agregar_tercero(
        nombre=nombre,
        tipo=tipo,
        direccion=direccion,
        nro_fiscal=nro_fiscal,
        email=email,
        telefono=telefono,
        empleado_id=empleado_id
    )


def listar_terceros_menu() -> None:
    GESTOR_TERCEROS.listar_terceros()


def buscar_tercero_por_id_menu() -> None:
    print("\n--- BUSCAR TERCERO POR ID ---")
    try:
        tercero_id = int(input("Ingrese el ID del tercero a buscar: ").strip())
    except ValueError:
        print("\nError: El ID debe ser un número entero válido.")
        return

    tercero_encontrado = GESTOR_TERCEROS.buscar_por_id(tercero_id)

    if tercero_encontrado:
        print("\n¡Tercero encontrado!")
        print("-" * 30)
        print(tercero_encontrado)
        print("-" * 30)
    else:
        print(f"\nError: No se encontró ningún tercero con el ID {tercero_id}.")


def buscar_tercero_por_nombre_menu() -> None:
    print("\n--- BUSCAR TERCERO POR NOMBRE ---")

    nombre_busqueda = input("Ingrese el nombre o parte del nombre a buscar: ").strip()

    if not nombre_busqueda:
        print("\nError: El nombre de búsqueda no puede estar vacío.")
        return

    terceros_encontrados = GESTOR_TERCEROS.buscar_por_nombre(nombre_busqueda)

    if terceros_encontrados:
        print(f"\n¡{len(terceros_encontrados)} Tercero(s) encontrado(s) con '{nombre_busqueda}'!")
        print(f"{'=' * 100}")
        for tercero in terceros_encontrados:
            print(tercero)
        print(f"{'=' * 100}")
    else:
        print(f"\nNo se encontró ningún tercero que contenga '{nombre_busqueda}'.")


def modificar_tercero_menu() -> None:
    print("\n--- MODIFICAR TERCERO ---")

    try:
        tercero_id = int(input("Ingrese el ID del tercero a MODIFICAR: ").strip())
    except ValueError:
        print("\nError: El ID debe ser un número entero válido.")
        return

    tercero_a_modificar = GESTOR_TERCEROS.buscar_por_id(tercero_id)

    if not tercero_a_modificar:
        print(f"\nError: No se encontró ningún tercero con el ID {tercero_id}.")
        return

    empleado_id = seleccionar_empleado()
    if empleado_id is None:
        print("Acción de modificación cancelada.")
        return

    print("\nTercero encontrado. Deje el campo vacío para MANTENER el valor actual.")
    print(f"Datos actuales: {tercero_a_modificar}")
    print("-" * 50)

    def obtener_nuevo_valor(prompt: str, valor_actual: str) -> str:
        nuevo_valor = input(f"{prompt} (Actual: {valor_actual}): ").strip()
        return nuevo_valor if nuevo_valor else valor_actual

    nuevo_nombre = obtener_nuevo_valor("Nuevo nombre", tercero_a_modificar.nombre)
    nuevo_tipo = obtener_nuevo_valor("Nuevo tipo (cliente/proveedor)", tercero_a_modificar.tipo)
    nueva_direccion = obtener_nuevo_valor("Nueva dirección", tercero_a_modificar.direccion)
    nuevo_nro_fiscal = obtener_nuevo_valor("Nuevo Nro. Fiscal", tercero_a_modificar.nro_fiscal)
    nuevo_email = obtener_nuevo_valor("Nuevo Email", tercero_a_modificar.email)
    nuevo_telefono = obtener_nuevo_valor("Nuevo Teléfono", tercero_a_modificar.telefono)

    tipo_normalizado = nuevo_tipo.lower()
    errores = GESTOR_TERCEROS.validar_datos(nuevo_nombre, tipo_normalizado, nuevo_nro_fiscal, nuevo_email)

    if nuevo_nro_fiscal != tercero_a_modificar.nro_fiscal and \
            GESTOR_TERCEROS.nro_fiscal_existe(nuevo_nro_fiscal, excluir_id=tercero_id):
        errores.append(f"El Nro. Fiscal '{nuevo_nro_fiscal}' ya está registrado por otro tercero.")

    if errores:
        print("\nERRORES DE VALIDACIÓN:")
        for error in errores:
            print(f"  - {error}")
        return

    tercero_a_modificar.nombre = nuevo_nombre
    tercero_a_modificar.tipo = tipo_normalizado
    tercero_a_modificar.direccion = nueva_direccion
    tercero_a_modificar.nro_fiscal = nuevo_nro_fiscal
    tercero_a_modificar.email = nuevo_email
    tercero_a_modificar.telefono = nuevo_telefono
    tercero_a_modificar.empleado_id = empleado_id

    if GESTOR_TERCEROS.guardar_terceros():
        print(f"\nTercero ID {tercero_id} modificado y guardado con éxito.")
    else:
        print("\nError grave al guardar los datos modificados.")


def eliminar_tercero_menu() -> None:
    print("\n--- ELIMINAR TERCERO ---")

    empleado_id = seleccionar_empleado()
    if empleado_id is None:
        print("Acción de eliminación cancelada.")
        return

    try:
        tercero_id = int(input("Ingrese el ID del tercero a ELIMINAR: ").strip())
    except ValueError:
        print("\nError: El ID debe ser un número entero válido.")
        return

    tercero_a_eliminar = GESTOR_TERCEROS.buscar_por_id(tercero_id)

    if not tercero_a_eliminar:
        print(f"\nError: No se encontró ningún tercero con el ID {tercero_id}.")
        return

    print(f"\nVa a eliminar el siguiente registro: ")
    print(tercero_a_eliminar)

    confirmacion = input("¿Está seguro que desea eliminarlo? (S/N): ").upper().strip()

    if confirmacion != 'S':
        print("\nEliminación cancelada.")
        return

    tercero_a_eliminar.empleado_id = empleado_id

    try:
        GESTOR_TERCEROS.terceros.remove(tercero_a_eliminar)
    except ValueError:
        print("\nError interno: El tercero no se pudo remover de la lista.")
        return

    if GESTOR_TERCEROS.guardar_terceros():
        print(f"\nTercero ID {tercero_id} eliminado y cambios guardados con éxito.")
    else:
        tercero_a_eliminar.terceros.append(tercero_a_eliminar)
        print("\nError grave al guardar los datos después de la eliminación. ¡Se revirtió la acción!")


# ====================================================================
# FUNCIONES DE GRÁFICOS (Matplotlib)
# ====================================================================

def obtener_nombres_empleados() -> Dict[int, str]:
    """Lee el archivo de empleados (empleados.txt) y retorna un diccionario ID: Nombre."""
    nombres_map = {}
    EMPLEADOS_SEPARADOR = ";"

    if os.path.exists(EMPLEADOS_FILE):
        with open(EMPLEADOS_FILE, 'r', encoding='utf-8') as f:
            for linea in f:
                linea = linea.strip()
                if not linea: continue
                try:
                    partes = linea.split(EMPLEADOS_SEPARADOR)
                    emp_id = int(partes[0].strip())
                    nombre = partes[1].strip()
                    nombres_map[emp_id] = nombre
                except (IndexError, ValueError):
                    continue
    return nombres_map

def obtener_frecuencia_empleados() -> Dict[int, int]:
    """Cuenta cuántas veces aparece cada empleado_id en la lista de terceros."""
    ids_activos = [t.empleado_id for t in GESTOR_TERCEROS.terceros if t.empleado_id is not None]
    return Counter(ids_activos)

def pieAccionesPorEmpleado():
    """Genera un gráfico de pastel mostrando la distribución de acciones (registros) por empleado."""
    frecuencias = obtener_frecuencia_empleados()

    if not frecuencias:
        print("\nNo hay terceros registrados con ID de empleado. No se puede generar el gráfico.")
        return

    nombres_empleados = obtener_nombres_empleados()
    nombres: List[str] = []
    acciones: List[int] = []

    for emp_id, conteo in frecuencias.items():
        nombre = nombres_empleados.get(emp_id, f"ID Desconocido ({emp_id})")
        nombres.append(nombre)
        acciones.append(conteo)

    fig, ax = plt.subplots(figsize=(5, 5))

    def mostrar_porcentaje_y_valor(pct):
        valor_absoluto = int(round(pct / 100. * sum(acciones)))
        return f"{pct:.1f}%\n({valor_absoluto} regs.)"

    ax.pie(
        acciones,
        labels=nombres,
        autopct=mostrar_porcentaje_y_valor,
        startangle=90,
        wedgeprops={"edgecolor": "black", "linewidth": 0.5},
        textprops={'fontsize': 10}
    )

    ax.set_title("Distribución de Registros en Terceros por Empleado", pad=20)
    ax.axis('equal')
    plt.show()


def mostrar_grafico_acciones_menu() -> None:
    """Función de menú para llamar a la generación del gráfico."""
    try:
        pieAccionesPorEmpleado()
    except Exception as e:
        print(f"\nError al generar el gráfico. ¿Está 'matplotlib' instalado? Error: {e}")