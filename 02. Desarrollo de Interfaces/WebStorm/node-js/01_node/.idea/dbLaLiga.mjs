import mysql from "mysql2/promise";

export async function connectLiga() {
    try {
        const connection = await mysql.createConnection({
            host: 'localhost',
            user: 'root',
            password: 'root',
            database: 'laliga',
        });
        console.log('Conexión a MySQL establecida.');
        return connection;
    } catch (error) {
        console.error('Error al conectar a MySQL:', error);
        throw error;
    }
}