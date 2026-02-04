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
├── .env
├── requirements.txt
└── README.md

Este proyecto está realizado dentro de un entorno virtual.
Pasos para ejecutar el programa desde CMD
1. Acceder a la carpeta principal ecommerce
2. venv\Scripts\activate.bat
3. pip install -r requirements.txt
4. python .\app\main.py