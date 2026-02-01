from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

DATABASE_URL = "mysql+pymysql://root:root@localhost:3306/ecommerce"

engine = create_engine(DATABASE_URL, echo=False)
#Puente entre la conexión y los modelos
SessionLocal = sessionmaker(bind=engine)