# Ecommerce
***
ecommerce/
│
├── app/
│   ├── main.py
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
│   
│
├── .env
├── requirements.txt
└── README.md

Instalación de dependencias:
pip install -r requirements.txt
