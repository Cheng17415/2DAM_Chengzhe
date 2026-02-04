import { unlink } from 'node:fs';

const destino = 'c://ejemplosNode//copia.txt';

//Eliminar un archivo
unlink(destino, (err) => {
    if (err) throw err;

    console.log('Archivo borrado exitosamente');
});