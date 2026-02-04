import connect from './dbConnection.mjs';
async function insertData(deptno, dname, loc) {
    // Establecemos la conexión a la base de datos
    const db = await connect();
    try {
        // Ejecutamos la consulta SQL para insertar datos
        const [result] = await db.execute('INSERT INTO deptno (deptno, dname, loc) VALUES (?, ?, ?)', [deptno, dname, loc]);
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

insertData(80, 'Prueba', 'Valencia'); // Ejecutamos la función insertData con los datos proporcionados