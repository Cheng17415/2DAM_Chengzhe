import {provincias, cautonomas} from './autonomias-provincias.js';

function inicio(){
    const marcoProv = document.getElementById("marcoProvincias");
    cautonomas.forEach((_, idx)=>ponerImagen(idx,marcoProv));
}

function ponerImagen(pos,des){
    let elem = document.createElement("img");
    let id = "imagen_"+pos;
    elem.setAttribute("src","banderas/" + cautonomas[pos] + ".gif");
    elem.setAttribute("id", id);
    elem.addEventListener("click",()=>hacerClick(elem, pos));
    des.appendChild(elem);
}

function hacerClick(elem, pos){
    //Quitar la clase hechoClick al anterior
    const anterior = document.querySelector(".hechoClick");
    if(anterior !== null)anterior.classList.remove("hechoClick");
    //Añadir la clase hechoClick al elemento.
    elem.classList.add("hechoClick");

    //Actualizar el fieldSet con los datos
    document.getElementById("leyenda").innerHTML = cautonomas[pos];

    let selector = document.getElementById("ccaa");
    selector.innerHTML = "";
    provincias[pos].forEach(provincia=>{
        let opcion = document.createElement("option");
        opcion.value = provincia;
        opcion.innerHTML = provincia;
        selector.appendChild(opcion);
    });
}


window.onload = ()=>(inicio());
