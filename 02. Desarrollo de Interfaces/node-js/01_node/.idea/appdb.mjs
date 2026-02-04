import connect from './dbConnection.mjs';
import { writeFile } from 'node:fs/promises';

const rutaDepartamentos ="C:/ejemplosNode/departamentos.txt"
const rutaEmpleados = "C:/ejemplosNode/empleados.txt"
async function fetchData() {
    const db = await connect();
    try {
        // Consulta para obtener todos los usuarios
        const [rows, fields] = await db.execute('SELECT * FROM dept');
        writeFile(rutaDepartamentos,JSON.stringify(rows, null, 2), (error)=>{
            if (err) throw err;

            console.log('Nuevo archivo creado y contenido escrito');
        });
        // Consulta para obtener usuarios por ciudad
        const departamento = 10;
        const [cityRows, cityFields] = await db.execute('SELECT * FROM emp WHERE deptno = ?', [departamento]);

        writeFile(rutaEmpleados,JSON.stringify(cityRows, null, 2), (error)=>{
            if (err) throw err;

            console.log('Nuevo archivo creado y contenido escrito');
        });
        //console.log(`Empleados en ${departamento}:`, cityRows);
    } catch (error) {
        console.error('Error al obtener datos:', error);
    } finally {
        db.end();
    }
}

fetchData();