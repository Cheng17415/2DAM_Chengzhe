# EzyStock-S.L — Documentación Técnica

EzyStock-S.L es un ERP modular educativo desarrollado en Python.
El proyecto se organiza por módulos de negocio independientes que comparten un menú principal y utilizan ficheros en texto plano como base de datos.

Este documento está orientado a desarrolladores que necesiten mantener, extender o comprender la arquitectura interna del sistema.
* Requisitos para utilizar el proyecto
-Tener instalado git https://git-scm.com/
-Tener instalado python la version 3 en adelante https://www.python.org/downloads/
-Editor de texto (Visual studio - pycharm etc).

1. Como utilizar el repositorio
Clonar repositorio:
* Abrir git bash en la carpeta que se desea utilizar
* Escribir siguiente comando:
    git clone https://github.com/JGhm007/EzyStock-S.L.git
* Ingresar usuario y contraseña de github en caso solicite
* Y listo :) 

2. Arquitectura del proyecto
```
EzyStock-S.L/
│
├── menu_principal.py                  # Punto de entrada del sistema
│
├── base_de_datos/                     # "Base de datos" del sistema
│   ├── usuarios.txt                   # Datos de usuarios y roles
│   ├── terceros.txt                   # Datos de clientes / proveedores
│   ├── productos.txt                  # Datos de productos y almacenes
│   ├── comercial.txt                  # Datos de pedidos / ventas
│   └── logs.txt                       # Acciones registradas (auditoría)
│
├── modules/                           # Módulos independientes
│   │
│   ├── usuarios_grupos/               # Autor: Cheng
│   │   ├── __init__.py
│   │   ├── menu_usuarios.py           # Menú y flujo del módulo
│   │   └── usuarios.py                # CRUD de usuarios
│   │
│   ├── terceros/                      # Autor: Sonia
│   │   ├── menu_terceros.py
│   │   └── terceros.py
│   │
│   ├── productos_almacenes/           # Autor: Jose
│   │   ├── menu_productos.py
│   │   └── productos.py
│   │
│   └── comercial_pedidos/             # Autor: Cindy
│       ├── menu_comercial.py
│       └── comercial.py
│
└── README.md
```
3. Actualizar repositorio (Subir nuestros cambios)
*** IMPORTANTE,
SIEMPRE ACTUALIZAR EL PROYECTO ANTES DE REALIZAR CUALQUIER MODIFICACIÓN PARA EVITAR CONFLICTOSSSSSS!!!!!

* Abrir GIT BASH en la carpeta que se está utilizando
* Comandos:
1. git status
2. git pull                         → PARA ACTUALIZAR EL REPOSITORIO LOCAL (TU MAQUINA)
2. git add *
3. git commit -m "<el mensaje con el cambio que se realizó>"
4. git status
5. git push
6. Verificar el repositorio si están los cambios realizados.


4. Dependencias utilizadas
1. mathplotlib - Para instalarlo, utilice el siguiente comando en la terminal: pip install matplotlib