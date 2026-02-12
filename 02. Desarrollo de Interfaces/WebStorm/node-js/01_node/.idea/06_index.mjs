import fs from 'node:fs';

const arhivo = 'c://ejemplosNode/creado.txt';
const readableStream = fs.createReadStream(arhivo, 'utf8');

readableStream.on('data', (chunk) => {
    console.log('Datos recibidos:', chunk);
});

readableStream.on('end', () => {
    console.log('Lectura de archivo completa');
});

readableStream.on('error', (err) => {
    console.error('Error al leer el archivo:', err);
});