import fs from 'node:fs';
import connect from './dbConnection.mjs';

const archivo = 'c://ejemplosNode/salida.txt';
const writableStream = fs.createWriteStream(archivo, 'utf8');

async function fetchData() {
    const db = await connect();
    try {
        // Consulta para obtener todos los usuarios
        const [rows, fields] = await db.execute('SELECT * FROM dept');

        console.log("Todos los departamentos", rows);
        for(let i = 0; i < rows.length; i++) {
            writableStream.write(JSON.stringify(rows[i]) + "\n");
        }

        // Consulta para obtener usuarios por ciudad
        const departamento = 10;
        const [cityRows, cityFields] = await db.execute('SELECT * FROM emp WHERE deptno = ?', [departamento]);
        for(let i = 0; i < cityRows.length; i++) {
            writableStream.write(JSON.stringify(cityRows[i]) + "\n");
        }

        //console.log(`Empleados en ${departamento}:`, cityRows);
    } catch (error) {
        console.error('Error al obtener datos:', error);
    } finally {
        await db.end();
        writableStream.end('Finalizando escritura en el archivo.\n');
    }
}

fetchData();


writableStream.on('finish', () => {
    console.log('Escritura de archivo completa');
});

writableStream.on('error', (err) => {
    console.error('Error al escribir en el archivo:', err);
});