import mysql.connector
from mysql.connector import Error


def test_db():
    print("Iniciando intento de conexión ")

    try:
        conexion = mysql.connector.connect(host='localhost',database='bd_scott', user='root', password='root')

        if conexion.is_connected():
            print("Conexión establecida.")
            cursor = conexion.cursor()
            cursor.execute("SELECT DATABASE();")
            print(f"Base de datos activa: {cursor.fetchone()}")

            cursor.close()
            conexion.close()
            print("Conexión cerrada correctamente.")

    except Exception as err:
        print(f"Error esperado de MySQL: {err}")

def crear_database():
    try:
        conexion = mysql.connector.connect(host='localhost', user='root', password='root')
        cursor = conexion.cursor()

        cursor.execute("CREATE DATABASE IF NOT EXISTS electronic")
        cursor.execute("USE electronic")

        print("Schema creado y en uso")

        cursor.close()
        conexion.close()

    #capturar solo errores que provienen de la base de datos
    except mysql.connector.Error as err:
        print(f"Error esperado de MySQL: {err}")

def selectID(id):
    try:
        connection = mysql.connector.connect(host='localhost', database = 'Electronics', user='root', password='root')
        query = "SELECT * FROM electronic WHERE id = %s"
        tid=(id,)
        cursor = connection.cursor()
        cursor.execute(query, tid)
        row = cursor.fetchone()
        print(row[0], row[1], row[2], row[3])

    except Error as err:
        print(f"Error esperado de MySQL: {err}")
    finally:
        if connection.is_connected():
            connection.close()
            cursor.close()
            print("MySQL connection closed.")

def selectBetween(price1, price2):
    try:
        connection = mysql.connector.connect(host='localhost', database ='Electronics', user='root', password='root')
        query = "SELECT * FROM ELECTRONICS WHERE PRICE BETWEEN %s and %s"
        tid = (price1,price2)
        cursor = connection.cursor()
        cursor.execute(query, tid)
        rows = cursor.fetchall()
        for row in rows:
            print(row[0], row[1], row[2], row[3])
    except Error as err:
        print(f"Error esperado de MySQL: {err}")
    finally:
        if connection.is_connected():
            connection.close()
            cursor.close()
            print("MySQL connection closed.")

if __name__ == "__main__":
    # test_db()
    crear_database()