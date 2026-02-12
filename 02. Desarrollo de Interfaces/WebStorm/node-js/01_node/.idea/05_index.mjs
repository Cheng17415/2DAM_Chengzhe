import { readFile, appendFile, writeFile } from 'node:fs';

const origen = 'c://ejemplosNode//archivo.txt';
const nuevaLinea = 'Nueva línea a añadir';

appendFile(origen, `${nuevaLinea}\n`, (err) => {
    if (err) throw err;

    console.log('Línea añadida al archivo');
});

readFile(origen, 'utf8', (err, data) => {
    if (err) {
        console.error('Error al leer el archivo:', err);
        return;
    }

    console.log('Contenido del archivo:');
    console.log(data);
});
const nuevo = 'c://ejemplosNode/creado.txt';
const variasLineas = "ana\nmanuel\nandres\n";

writeFile(nuevo, variasLineas, (err) => {
    if (err) throw err;

    console.log('Nuevo archivo creado y contenido escrito');
});
