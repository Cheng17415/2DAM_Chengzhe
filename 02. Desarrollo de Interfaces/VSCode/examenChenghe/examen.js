let nombres = {
    F: 'Fahrenheit',
    C: 'Celsius',
    K: 'Kelvin'
};

function inicio() {

    let fieldset = document.getElementById('fieldOpciones');
    //Creamos la leyenda y se lo damos a fieldset
    let legend = document.createElement('legend');
    legend.innerHTML = 'Opciones';
    fieldset.appendChild(legend);
    
    let opc = ['C', 'F', 'K'];
    //Hacemos un for anidado para tener todas las opciones, CF, CK, FC, etc.
    opc.forEach((elem1) => {
        opc.forEach((elem2) => {
            if (elem1 === elem2) return;
            //Para cada uno, debemos crear el input con sus valores
            let opcion = document.createElement('input');
            opcion.type = 'radio';
            opcion.name = 'opcion';
            opcion.id = `opc${elem1}${elem2}`;
            opcion.value = `${elem1}${elem2}`;
            fieldset.appendChild(opcion);

            //Y también crear el label para ese input
            let label = document.createElement('label');
            label.for = `opc${elem1}${elem2}`;
            label.innerHTML = `${elem1} a ${elem2}`;
            fieldset.appendChild(label);
        });
    });
}
//Esta funcion cambia el label dependiendo de la opción elegida
function cambio() {
    let label = document.getElementById('lInput');
    let radioSelec = document.querySelector('input[name="opcion"]:checked');
    //Si no está seleccionada ninguna opción, no hacer nada.
    if (!radioSelec) return;
    //El valor de radioSelec puede ser CF, CK, FC, etc.
    let radioValue = radioSelec.value;
    /*Utilizando substring de 0 a 1 podemos obtener la letra, que junto
    con la variable global nombres obtenemos el nombre.*/
    label.innerHTML = nombres[radioValue.substring(0, 1)];

}
//Esta función sirve para calcular al darle click al botón convertir
function click() {
    let radioSelec = document.querySelector('input[name="opcion"]:checked');
    let inpNum = document.getElementById('inpNum');
    let destino = document.getElementById('textoConversor');
    //Si están vacía cualquiera de los valores, no hacer nada.
    if (!radioSelec || !inpNum) return;
    let valorNum = inpNum.value;

    increment(radioSelec, valorNum, destino);
}
function redondear(numero, decimales) {
    let n = Math.pow(10, decimales);
    return Math.round(numero * n) / n;
}
//Calculo de la temperatura
function increment(quien, valor, destino) {
    let vid = quien.value;
    let temp1 = vid.substring(0, 1);
    let temp2 = vid.substring(1);
    let utilizar = 0;
    let vgc = 0, vgf = 0, vgk = 0;
    switch (temp1) {
        case "C": // Grados Celsius
            vgc = parseFloat(valor);
            vgf = (vgc * 9) / 5 + 32;
            vgk = vgc + 273.15;
            break;
        case "F": // Grados Fahrenheit
            vgf = parseFloat(valor);
            vgc = (vgf - 32) * 5 / 9;
            vgk = vgc + 273.15;
            break;
        case "K": // Grados Kelvin
            vgk = parseFloat(valor);
            vgc = vgk - 273.15;
            vgf = (vgc * 9) / 5 + 32;
            break;
    }
    switch (temp2) {
        case "C":
            utilizar = redondear(vgc, 2);
            break;
        case "F":
            utilizar = redondear(vgf, 2);
            break;
        case "K":
            utilizar = redondear(vgk, 2);
            break;
    }
    destino.innerHTML = `${redondear(parseFloat(valor), 2)} ${nombres[temp1]} =
                        ${utilizar} ${nombres[temp2]}`;
}
window.addEventListener('load', () => inicio());
window.addEventListener('click', () => cambio());
document.getElementById('botonConvertir').addEventListener('click', () => click());