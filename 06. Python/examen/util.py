import csv
import os
import random
from typing import Any


def alea(li: int, ls: int) -> int:
    """Devuelve un entero aleatorio entre li y ls (incluidos)."""
    if li > ls:
        li, ls = ls, li
    return random.randint(li, ls)


def _asegurar_directorio(ruta_fichero: str) -> None:
    directorio = os.path.dirname(ruta_fichero)
    if directorio:
        os.makedirs(directorio, exist_ok=True)


def _leer_todos(ruta_fichero: str, separador: str = ";") -> list[dict[str, str]]:
    if not os.path.exists(ruta_fichero):
        return []

    with open(ruta_fichero, "r", encoding="utf-8", newline="") as f:
        lector = csv.DictReader(f, delimiter=separador)
        return list(lector)


def _guardar_todos(
    ruta_fichero: str, registros: list[dict[str, Any]], separador: str = ";"
) -> None:
    _asegurar_directorio(ruta_fichero)

    if not registros:
        with open(ruta_fichero, "w", encoding="utf-8", newline="") as _:
            return

    cabeceras = list(registros[0].keys())
    with open(ruta_fichero, "w", encoding="utf-8", newline="") as f:
        escritor = csv.DictWriter(f, fieldnames=cabeceras, delimiter=separador)
        escritor.writeheader()
        escritor.writerows(registros)


def crear_registro(
    ruta_fichero: str,
    nuevo_registro: dict[str, Any],
    clave: str = "id",
    separador: str = ";",
) -> bool:
    """Crea un registro. False si ya existe esa clave."""
    if clave not in nuevo_registro:
        raise ValueError(f"El registro debe incluir la clave '{clave}'.")

    registros = _leer_todos(ruta_fichero, separador)
    valor_clave = str(nuevo_registro[clave])

    if any(r.get(clave) == valor_clave for r in registros):
        return False

    registros.append({k: str(v) for k, v in nuevo_registro.items()})
    _guardar_todos(ruta_fichero, registros, separador)
    return True


def leer_todos_registros(
    ruta_fichero: str, separador: str = ";"
) -> list[dict[str, str]]:
    """Devuelve todos los registros del fichero."""
    return _leer_todos(ruta_fichero, separador)


def leer_registro_por_clave(
    ruta_fichero: str,
    valor_clave: Any,
    clave: str = "id",
    separador: str = ";",
) -> dict[str, str] | None:
    """Devuelve el registro por clave o None si no existe."""
    valor_clave = str(valor_clave)
    for registro in _leer_todos(ruta_fichero, separador):
        if registro.get(clave) == valor_clave:
            return registro
    return None


def actualizar_registro(
    ruta_fichero: str,
    valor_clave: Any,
    cambios: dict[str, Any],
    clave: str = "id",
    separador: str = ";",
) -> bool:
    """Actualiza un registro por clave. True si se actualiza."""
    valor_clave = str(valor_clave)
    registros = _leer_todos(ruta_fichero, separador)

    actualizado = False
    for registro in registros:
        if registro.get(clave) == valor_clave:
            for k, v in cambios.items():
                registro[k] = str(v)
            actualizado = True
            break

    if actualizado:
        _guardar_todos(ruta_fichero, registros, separador)
    return actualizado


def eliminar_registro(
    ruta_fichero: str,
    valor_clave: Any,
    clave: str = "id",
    separador: str = ";",
) -> bool:
    """Elimina un registro por clave. True si se elimina."""
    valor_clave = str(valor_clave)
    registros = _leer_todos(ruta_fichero, separador)
    restantes = [r for r in registros if r.get(clave) != valor_clave]

    if len(restantes) == len(registros):
        return False

    _guardar_todos(ruta_fichero, restantes, separador)
    return True


def _leer_todos_txt(
    ruta_fichero: str, campos: list[str], separador: str = ";"
) -> list[dict[str, str]]:
    if not os.path.exists(ruta_fichero):
        return []

    registros: list[dict[str, str]] = []
    with open(ruta_fichero, "r", encoding="utf-8") as f:
        for linea in f:
            linea = linea.strip()
            if not linea:
                continue
            partes = linea.split(separador)
            if len(partes) != len(campos):
                continue
            registros.append(dict(zip(campos, partes)))
    return registros


def _guardar_todos_txt(
    ruta_fichero: str,
    campos: list[str],
    registros: list[dict[str, Any]],
    separador: str = ";",
) -> None:
    _asegurar_directorio(ruta_fichero)
    with open(ruta_fichero, "w", encoding="utf-8") as f:
        for i, registro in enumerate(registros):
            linea = separador.join(str(registro.get(campo, "")) for campo in campos)
            if i > 0:
                f.write("\n")
            f.write(linea)


def crear_registro_txt(
    ruta_fichero: str,
    campos: list[str],
    nuevo_registro: dict[str, Any],
    clave: str = "id",
    separador: str = ";",
) -> bool:
    """Crea un registro en .txt sin cabecera. False si la clave ya existe."""
    if clave not in nuevo_registro:
        raise ValueError(f"El registro debe incluir la clave '{clave}'.")

    registros = _leer_todos_txt(ruta_fichero, campos, separador)
    valor_clave = str(nuevo_registro[clave])

    if any(r.get(clave) == valor_clave for r in registros):
        return False

    registro_final = {campo: str(nuevo_registro.get(campo, "")) for campo in campos}
    registros.append(registro_final)
    _guardar_todos_txt(ruta_fichero, campos, registros, separador)
    return True


def leer_todos_registros_txt(
    ruta_fichero: str, campos: list[str], separador: str = ";"
) -> list[dict[str, str]]:
    """Devuelve todos los registros de un .txt sin cabecera."""
    return _leer_todos_txt(ruta_fichero, campos, separador)


def leer_registro_por_clave_txt(
    ruta_fichero: str,
    campos: list[str],
    valor_clave: Any,
    clave: str = "id",
    separador: str = ";",
) -> dict[str, str] | None:
    """Devuelve un registro por clave en .txt o None."""
    valor_clave = str(valor_clave)
    for registro in _leer_todos_txt(ruta_fichero, campos, separador):
        if registro.get(clave) == valor_clave:
            return registro
    return None


def actualizar_registro_txt(
    ruta_fichero: str,
    campos: list[str],
    valor_clave: Any,
    cambios: dict[str, Any],
    clave: str = "id",
    separador: str = ";",
) -> bool:
    """Actualiza un registro por clave en .txt. True si actualiza."""
    valor_clave = str(valor_clave)
    registros = _leer_todos_txt(ruta_fichero, campos, separador)

    actualizado = False
    for registro in registros:
        if registro.get(clave) == valor_clave:
            for k, v in cambios.items():
                if k in campos:
                    registro[k] = str(v)
            actualizado = True
            break

    if actualizado:
        _guardar_todos_txt(ruta_fichero, campos, registros, separador)
    return actualizado


def eliminar_registro_txt(
    ruta_fichero: str,
    campos: list[str],
    valor_clave: Any,
    clave: str = "id",
    separador: str = ";",
) -> bool:
    """Elimina un registro por clave en .txt. True si elimina."""
    valor_clave = str(valor_clave)
    registros = _leer_todos_txt(ruta_fichero, campos, separador)
    restantes = [r for r in registros if r.get(clave) != valor_clave]

    if len(restantes) == len(registros):
        return False

    _guardar_todos_txt(ruta_fichero, campos, restantes, separador)
    return True
