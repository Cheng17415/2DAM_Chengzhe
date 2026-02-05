import fs from 'node:fs';
import {connect} from './dbConnection.mjs';

const archivo = 'c://ejemplosNode/salida.txt';
const archivo2 = 'c://ejemplosNode/salida2.txt';
const writableStream = fs.createWriteStream(archivo, 'utf8');

async function fetchData() {
    const db = await connect();
    const writableStream = createWritableStream(archivo);
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

function createWritableStream(rutaArchivo) {
    const stream = fs.createWriteStream(rutaArchivo, 'utf8');

    stream.on('finish', () => {
        console.log('Escritura de archivo completa');
    });

    stream.on('error', (err) => {
        console.error('Error al escribir en el archivo:', err);
    });

    return stream;
}

async function insertData(deptno, dname, loc) {
    // Establecemos la conexión a la base de datos
    const db = await connect();
    try {
        // Ejecutamos la consulta SQL para insertar datos
        const [result] = await db.execute('INSERT INTO dept (deptno, dname, loc) VALUES (?, ?, ?)', [deptno, dname, loc]);
        // Mostramos el resultado de la operación
        console.log('Datos insertados:', result);
    } catch (error) {
        // Manejamos cualquier error que ocurra durante la inserción de datos
        console.error('Error al insertar datos:', error);
    } finally {
        // Cerramos la conexión a la base de datos, independientemente del resultado
        db.end();
    }
}
/*Subir el sueldo de los empledos por una cantidad por parámetro*/
async function updateData(incremento) {
    // Establecemos la conexión a la base de datos
    const db = await connect();
    try {
        // Ejecutamos la consulta SQL para actualizar datos
        const [result] = await db.execute('UPDATE emp SET sal = sal + ?', [incremento]);
        // Mostramos el resultado de la operación
        console.log('Datos actualizados:', result);
    } catch (error) {
        // Manejamos cualquier error que ocurra durante la actualización de datos
        console.error('Error al actualizar datos:', error);
    } finally {
        // Cerramos la conexión a la base de datos, independientemente del resultado
        db.end();
    }
}

async function deleteData(deptno) {
    // Establecemos la conexión a la base de datos
    const db = await connect();
    try {
        // Ejecutamos la consulta SQL para eliminar datos
        const [result] = await db.execute('DELETE FROM dept WHERE deptno = ?', [deptno]);
        // Mostramos el resultado de la operación
        console.log('Datos eliminados:', result);
    } catch (error) {
        // Manejamos cualquier error que ocurra durante la eliminación de datos
        console.error('Error al eliminar datos:', error);
    } finally {
        // Cerramos la conexión a la base de datos, independientemente del resultado
        db.end();
    }
}

//Obtener salario, nombre de los empleados y departamento junto con su nivel salarial
async function consultaScott() {
    const db = await connect();
    const writableStream = createWritableStream(archivo2);
    try {
        // Consulta para obtener todos los usuarios
        const [rows, fields] = await db.execute('SELECT a.ename, b.dname, nivel\n' +
            'FROM emp a\n' +
            'INNER JOIN dept b ON a.deptno = b.deptno\n' +
            'INNER JOIN salgrade c ON sal between losal and hisal');
        console.log("Todas las filas", rows);

        for(let i = 0; i < rows.length; i++) {
            writableStream.write(JSON.stringify(rows[i]) + "\n");
        }

    } catch (error) {
        console.error('Error al obtener datos:', error);
    } finally {
        await db.end();
        writableStream.end('Finalizando escritura en el archivo.\n');
    }
}

 // Ejecutamos la función updateData con los datos proporcionados
/*fetchData();

insertData(81, 'Prueba', 'Valencia'); // Ejecutamos la función insertData con los datos proporcionados

updateData(1000);*/

deleteData(81); // Ejecutamos la función deleteData con el ID del usuario a eliminar

consultaScott();

/*ID del equipo y nombre del equipo*/