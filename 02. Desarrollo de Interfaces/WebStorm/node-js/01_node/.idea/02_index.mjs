import path from 'node:path';

const ruta = 'c://ejemplosNode//archivo.txt';
const nombreBase = path.basename(ruta);

console.log('Nombre del archivo:', nombreBase);
//Nombre del archivo: archivo.txt

const rutaNew = './/archivo.txt';
const rutaAbsoluta = path.resolve(rutaNew);

console.log('Ruta absoluta:', rutaAbsoluta);
// Ruta absoluta: C:\..lo_que_sea..\carpeta1\subcarpeta\archivo.txt