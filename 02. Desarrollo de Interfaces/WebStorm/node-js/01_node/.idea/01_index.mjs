import path from 'node:path';

const ruta1 = '/carpeta1';
const ruta2 = 'subcarpeta/archivo.txt';

const rutaCompleta = path.join(ruta1, ruta2);
console.log('Ruta completa:', rutaCompleta);