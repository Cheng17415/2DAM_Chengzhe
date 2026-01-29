# Ecommerce
***
backend/
│
├── app/
│   ├── main.py
│   │
│   ├── core/               # Configuración global
│   │   ├── config.py
│   │   ├── security.py
│   │   └── settings.py
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
│   ├── schemas/            # Pydantic (entrada / salida)
│   │   ├── usuario.py
│   │   ├── producto.py
│   │   ├── carrito.py
│   │   └── pedido.py
│   │
│   ├── crud/               # Lógica de base de datos
│   │   ├── usuario.py
│   │   ├── producto.py
│   │   ├── carrito.py
│   │   └── pedido.py
│   │
│   ├── api/                # Rutas
│   │   ├── deps.py
│   │   │
│   │   ├── routes/
│   │   │   ├── auth.py
│   │   │   ├── usuarios.py
│   │   │   ├── productos.py
│   │   │   ├── carrito.py
│   │   │   └── pedidos.py
│   │   │
│   │   └── router.py
│   │
│   ├── services/           # Lógica de negocio
│   │   ├── auth_service.py
│   │   ├── carrito_service.py
│   │   └── pedido_service.py
│   │
│   ├── utils/
│   │   ├── email.py
│   │   └── helpers.py
│   │
│   └── tests/
│       └── test_auth.py
│
├── .env
├── requirements.txt
└── README.md

Instalación de dependencias:
pip install -r requirements.txt
