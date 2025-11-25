import {provincias, cautonomas} from './autonomias-provincias.js';

let raiz = './banderas/';
let fin = '.gif';
const selector = document.getElementById("provincias");
cautonomas.forEach(cautonoma=>{
    let opcion = document.createElement('option');
    opcion.value = cautonoma;
    opcion.img = cautonoma;
    selector.appendChild(opcion);
});