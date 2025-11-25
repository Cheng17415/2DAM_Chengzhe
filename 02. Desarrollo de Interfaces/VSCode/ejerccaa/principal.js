import {provincias, cautonomas} from './autonomias-provincias.js';

const marcoProv = document.getElementById("marcoProvincias");
cautonomas.forEach((_, idx)=>{
    ponerImagen(idx,marcoProv);
});

function ponerImagen(pos,des){
    let elem = document.createElement("img");
    elem.setAttribute("src","banderas/" + cautonomas[pos] + ".gif");
    elem.setAttribute("height","50");
    elem.setAttribute("width","50");
    elem.setAttribute("id","imagen_"+pos);
    des.appendChild(elem);
}