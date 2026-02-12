import {connectLiga} from "./dbLaLiga.mjs";


async function consultaLiga(numJornada) {
    const db = await connectLiga();

    try {
        // Consulta para obtener todos los usuarios
        const [rows, fields] = await db.execute('SELECT e.nombre NOMBRE, SUM(PUNTOS) PUNTAJE FROM '+
            '(SELECT IDJORNADA, IDLOCAL, '+
            'CASE ' +
            'WHEN a.gol_local > a.gol_visitante THEN 3 '+
            'WHEN a.gol_local < a.gol_visitante THEN 0 '+
            'ELSE 1 '+
            'END PUNTOS ' +
            'FROM partidos a '+
            'WHERE IDJORNADA <= ? '+
            'UNION '+
            'SELECT IDJORNADA, IDVISITANTE, '+
            'CASE '+
            'WHEN a.gol_local > a.gol_visitante THEN 0 '+
            'WHEN a.gol_local < a.gol_visitante THEN 3 '+
            'ELSE 1 '+
            'END PUNTOS '+
            'FROM partidos a '+

            'WHERE IDJORNADA <= ?) AS TB '+
            'INNER JOIN equipos e ON TB.IDLOCAL = e.IDEQUIPO '+
            'GROUP BY TB.IDLOCAL '+
            'ORDER BY PUNTAJE DESC; ', [numJornada, numJornada]);
        console.log("Todas las filas", rows);

    } catch (error) {
        console.error('Error al obtener datos:', error);
    } finally {
        await db.end();
    }
}

consultaLiga(39);