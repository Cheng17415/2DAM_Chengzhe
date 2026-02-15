import { access, copyFile, rename, unlink } from 'node:fs';

const ruta = 'c://ejemplosNode//archivo.txt';

//Como es una función asíncrona, necesita un callback.
// Un callback es una función que se ejecuta al terminar la función principal.
access(ruta, (err) => {
    if (err) {
        console.error('El archivo no existe');
        return;
    }

    console.log('El archivo existe');
});

const origen = 'c://ejemplosNode//archivo.txt';
const destino = 'c://ejemplosNode//copia.txt';
const destinoNew = 'c://ejemplosNode//destino.txt';

copyFile(origen,destino, (err) => {
    if (err) throw err;

    console.log('Archivo copiado exitosamente');
});

//Deja mover o cambiar de nombre
rename(destino, destinoNew, (err) => {
    if (err) throw err;

    console.log('Archivo movido exitosamente');
});
