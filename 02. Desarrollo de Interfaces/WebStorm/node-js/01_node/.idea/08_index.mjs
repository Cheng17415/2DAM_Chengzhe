import fs from 'node:fs';

function bisiesto(a){
    return ((a%4==0 && a%100!=0)||(a%400 == 0))
}

const archivo = 'c://ejemplosNode/bisiestos.txt';
const writableStream = fs.createWriteStream(archivo, 'utf8');

for(let i = 2000; i <= 2100; i++){
    if(bisiesto(i)){
        writableStream.write(`Año ${i} es bisiesto\n`)
    }
}

writableStream.end('Finalizando escritura en el archivo.\n');

writableStream.on('finish', () => {
    console.log('Escritura de archivo completa');
});

writableStream.on('error', (err) => {
    console.error('Error al escribir en el archivo:', err);
});