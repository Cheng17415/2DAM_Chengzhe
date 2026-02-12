import { createReadStream, createWriteStream } from 'node:fs';
import { Transform } from 'node:stream';

const readStream = createReadStream('c://ejemplosNode/bisiestos.txt', 'utf8');
const writeStream = createWriteStream('c://ejemplosNode/bisiestosMayusculas.txt');

const transformStream = new Transform({
    transform(chunk, encoding, callback) {
        this.push(chunk.toString().toUpperCase());
        callback();
    }
});

readStream.pipe(transformStream).pipe(writeStream);

writeStream.on('finish', () => {
    console.log('Archivo transformado y escrito en mayúsculas.');
});

writeStream.on('error', (err) => {
    console.error('Error al transformar y escribir el archivo:', err);
});