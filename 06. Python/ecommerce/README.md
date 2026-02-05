# Ecommerce
***
ecommerce/
│
├── app/
│   ├── main.py
│   │
│   │
│   │
│   │
│   │
│   │
│   ├── db/                 # Base de datos
│   │   ├── database.py
│   │   ├── base.py
│   │   └── init_db.py
│   │
│   ├── models/             # SQLAlchemy (BBDD)
│   │   ├── carrito.py
│   │   ├── envio.py
│   │   ├── pedido.py
│   │   ├── producto.py
│   │   ├── rol.py
│   │   └── usuario.py
│   │
│   └── crud/               # Lógica de base de datos
│       ├── usuario.py
│       ├── producto.py
│       ├── carrito.py
│       └── pedido.py
│   
├── venv
├── requirements.txt
└── README.md

***
Este proyecto está realizado dentro de un entorno virtual.
Pasos para ejecutar el programa desde CMD
1. Acceder a la carpeta principal ecommerce
2. Crear el entorno virtual con 'python -m venv venv'
2. Activa el entorno virtual con '.\venv\Scripts\activate'
3. Instala las dependencias dentro del entorno con 'pip install -r requirements.txt'
4. Ejecuta la app con 'python .\app\main.py'