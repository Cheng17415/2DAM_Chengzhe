import mysql
from mysql.connector import Error


def crearBBDD():
    try:
        connection = mysql.connector.connect(host = "localhost", user ="root", password = "root")
        cursor = connection.cursor()
        bbdd = "CREATE DATABASE IF NOT EXISTS Cumpleanios"
        cursor.execute(bbdd)
        cursor.execute("USE Cumpleanios")

        print("Schema creado y en uso")

        cursor.close()
        connection.close()
    except Error as e:
        print(e)

if __name__ == "__main__":
    crearBBDD()
    print("HOLA")