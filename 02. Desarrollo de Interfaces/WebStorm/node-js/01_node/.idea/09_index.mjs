import { createReadStream, createWriteStream } from 'node:fs';

const readStream = createReadStream('c://ejemplosNode/bisiestos.txt');
const writeStream = createWriteStream('c://ejemplosNode/copiabisiestos.txt');

readStream.pipe(writeStream);

writeStream.on('finish', () => {
    console.log('Archivo copiado exitosamente.');
});

writeStream.on('error', (err) => {
    console.error('Error al copiar el archivo:', err);
});