# Ecommerce

Proyecto de ejemplo de ecommerce en Python con SQLAlchemy y una estructura sencilla por capas.

**Estructura del proyecto**
```
ecommerce/
|-- app/
|   |-- main.py
|   |-- db/
|   |   |-- database.py
|   |   |-- base.py
|   |   `-- init_db.py
|   |-- models/
|   |   |-- carrito.py
|   |   |-- envio.py
|   |   |-- pedido.py
|   |   |-- producto.py
|   |   |-- rol.py
|   |   `-- usuario.py
|   `-- crud/
|       |-- usuario.py
|       |-- producto.py
|       |-- carrito.py
|       `-- pedido.py
|-- venv/
|-- requirements.txt
`-- README.md
```

**Requisitos**
- Python 3.x

**Configuracion rapida (CMD / PowerShell)**
1. Ir a la carpeta principal `ecommerce`
2. Crear el entorno virtual: `python -m venv venv`
3. Activar el entorno:
   - PowerShell: `.\venv\Scripts\Activate.ps1`
   - CMD: `.\venv\Scripts\activate.bat`
4. Instalar dependencias: `pip install -r requirements.txt`
5. Ejecutar la app: `python .\app\main.py`

**Notas**
- Si PowerShell bloquea la activacion, ejecutar: `Set-ExecutionPolicy -Scope Process RemoteSigned`
- La base de datos y sus modelos estan en `app/db` y `app/models`.
