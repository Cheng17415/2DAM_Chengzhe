import fs from 'node:fs';

const archivo = 'c://ejemplosNode/creadowrite.txt';
const writableStream = fs.createWriteStream(archivo, 'utf8');

writableStream.write('Esto es un texto que será escrito en el archivo.\n');
writableStream.write('Podemos escribir datos de forma incremental.\n');

writableStream.end('Finalizando escritura en el archivo.\n');

writableStream.on('finish', () => {
    console.log('Escritura de archivo completa');
});

writableStream.on('error', (err) => {
    console.error('Error al escribir en el archivo:', err);
});