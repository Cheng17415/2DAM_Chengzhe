// VARIABLES GLOBALES
let tamano = 3;
let filasTotales = (tamano * 2) + 2;
let tablero = new Array(filasTotales);
let turno = 1;
let modo = 2;
let jugadas1 = [], jugadas2 = [];
let puntos1 = getScore(modo, 1);
let puntos2 = getScore(modo, 2);

// INICIALIZACIÓN
for (let i = 0; i < filasTotales; i++) tablero[i] = new Array(tamano);

window.addEventListener("load", () => { cargar(); });

document.querySelector('.js-unJugador').addEventListener('click',()=> cambiarModo(1));
document.querySelector('.js-dosJugadores').addEventListener('click',()=> cambiarModo(2));
document.querySelector('.js-resetear').addEventListener('click',()=> resetPuntos());

// FUNCIONES DE INICIO Y RESET
function cargar() {
    updateScore(1, puntos1);
    updateScore(2, puntos2);
    cambiarTextoJugador(modo);
    addClaseisClicked(modo);

    let destino = document.getElementById("opciones");

    for (let i = 3; i <= 10; i++) {
        let opcion = document.createElement("option");
        opcion.text = i;
        opcion.value = i;
        destino.appendChild(opcion);
    }
    destino.addEventListener("change", () => reset());
    reset();
}

function reset() {
    jugadas1 = [];
    jugadas2 = [];
    turno = 1;

    updateTablero();
    updateTurno();
}

function resetPuntos() {
    puntos1 = 0;
    puntos2 = 0;

    setScore(modo, 1, puntos1);
    setScore(modo, 2, puntos2);

    updateScore(1, puntos1);
    updateScore(2, puntos2);
}

// FUNCIONES DE TABLERO Y GENERACIÓN
function updateTablero() {
    tamano = parseInt(document.getElementById("opciones").value);

    updateTitulo();

    filasTotales = (tamano * 2) + 2;
    tablero = new Array(filasTotales);

    for (let i = 0; i < filasTotales; i++) {
        tablero[i] = new Array(tamano);
    }

    let filaActual = 0;
    for (let fila = 0; fila < tamano; fila++) {
        for (let col = 0; col < tamano; col++) {
            tablero[filaActual][col] = (fila * tamano) + (col + 1);
            tablero[filaActual + tamano][col] = (col * tamano) + (fila + 1);
        }
        filaActual++;
    }

    filaActual = (tamano * 2);
    for (let i = 0; i < tamano; i++) {
        tablero[filaActual][i] = (i * tamano) + (i + 1);
        tablero[filaActual + 1][i] = (i * tamano) + (tamano - i);
    }

    document.getElementById("tablero").innerHTML = generarTablero();

    //Para cada boton del juego, añadir el evento de click
    document.querySelectorAll('.btnJuego')
      .forEach((boton,idx)=>{ 
          boton.addEventListener('click',()=>{
            changeImage(boton,idx)
          })
    });
}

function generarTablero() {
    let html = "<table>";
    for (let i = 0; i < tablero[i].length; i++) {
        html += "<tr>";
        for (let j = 0; j < tablero[i].length; j++) {
            html += `<td>
                    <button class="btnJuego">
                        <img src="imagenes/cloudy.png">
                    </button>
                    </td>`;
        }
        html += "</tr>";
    }
    html += "</table>";
    return html;
}

// FUNCIONES DE JUEGO
function updateTurno() {
    const turnoElem = document.querySelectorAll('.js-turno');
    turnoElem[0].innerHTML = '';
    turnoElem[1].innerHTML = '';

    if (modo === 1) turnoElem[turno - 1].innerHTML = turno === 1 ? "Tu turno" : "Turno de CPU";
    else if (modo === 2) turnoElem[turno - 1].innerHTML = `Tu turno`;
}

function changeImage(boton, idx) {
    let imagen = boton.children[0];
    if (!imagen.src.endsWith('imagenes/cloudy.png')) return;

    //Cálculo de las coordenadas de 'x' e 'y' de la array bidimensional a partir del index
    x = Math.floor(idx / tamano)
    y = idx % tamano
    if (turno === 1) {
        imagen.src = 'imagenes/sun.png';
        jugadas1.push(tablero[x][y]);
    } else {
        imagen.src = 'imagenes/moon.png';
        jugadas2.push(tablero[x][y]);
    }

    let ganado = comprobarSoluciones(turno === 1 ? jugadas1 : jugadas2);
    turno = turno === 1 ? 2 : 1;

    updateTurno();
    if (!ganado && modo === 1 && turno === 2) {
        habilitarBoton(false);
        setTimeout(() => {
            habilitarBoton(true);
            jugarCPU();
        }, 500);
    }
}

// FUNCION DE HABILITAR/DESHABILITAR BOTONES
function habilitarBoton(habilitado) {
    const botones = document.querySelectorAll('.btnJuego');
    botones.forEach(btn => {
        btn.disabled = !habilitado;
    });
}

// FUNCIONES COMPROBACIÓN VICTORIA O EMPATE
function comprobarSoluciones(jugadas) {
    if (jugadas.length < tamano) return false;
    let textoGanador = document.getElementById("js-ganador");

    if (comprobarGanador(jugadas)) {
        casoVictoria(textoGanador);
        return true;
    }

    if (jugadas1.length + jugadas2.length === tamano * tamano) {
        casoEmpate(textoGanador);
        return true;
    }
    return false;
}

function comprobarGanador(jugadas) {
    for (let i = 0; i < tablero.length; i++) {
        let contador = 0;
        for (let j = 0; j < tablero[i].length; j++) {
            if (jugadas.includes(tablero[i][j])) contador++;
            if (contador === tamano) {
                return true;
            }
        }
    }
    return false;
}

function casoVictoria(textoGanador) {
    textoGanador.textContent = getMensajeGanador();

    if (turno === 1) {
        puntos1++;
        setScore(modo, 1, puntos1);
        updateScore(turno, puntos1);
    } else {
        puntos2++;
        setScore(modo, 2, puntos2);
        updateScore(turno, puntos2);
    }
    habilitarBoton(false);
    setTimeout(() => {
        habilitarBoton(true);
        textoGanador.innerHTML = '';
        reset();
    }, 2000);
}

function casoEmpate(textoGanador) {
    textoGanador.innerHTML = 'Empate';

    setTimeout(() => {
        textoGanador.innerHTML = '';
        reset();
    }, 2000);
}

// FUNCIONES DE MODO DE JUEGO Y SCORES
function cambiarModo(numero) {
    modo = numero;
    puntos1 = getScore(modo, 1);
    puntos2 = getScore(modo, 2);
    updateScore(1, puntos1);
    updateScore(2, puntos2);
    cambiarTextoJugador(modo);
    addClaseisClicked(modo);
    reset();
}

function addClaseisClicked(numero) {
    const botones = document.querySelectorAll('.modoJuego');
    botones.forEach((btn, idx) => {
        if (idx === numero - 1) btn.classList.add('isClicked');
        else btn.classList.remove('isClicked');
    });
}

function updateScore(jugador, puntos) {
    let puntosElem = document.getElementById(`scoreP${jugador}`);
    puntosElem.textContent = `Score: ${puntos}`;
}

function getScore(modo, jugador) {
    const textoModo = (modo === 1) ? 'singleplayer' : 'multiplayer';
    let objJuego = JSON.parse(localStorage.getItem(textoModo)) || { p1: 0, p2: 0 };
    return (jugador === 1) ? objJuego.p1 : objJuego.p2;
}

function setScore(modo, jugador, puntos) {
    const textoModo = (modo === 1) ? 'singleplayer' : 'multiplayer';
    let objJuego = JSON.parse(localStorage.getItem(textoModo)) || { p1: 0, p2: 0 };
    if (jugador === 1) objJuego.p1 = puntos;
    else objJuego.p2 = puntos;
    localStorage.setItem(textoModo, JSON.stringify(objJuego));
}

// FUNCIONES DE CAMBIO DE TEXTO
function updateTitulo() {
    const textoTitulo = `${tamano} en Raya`;
    document.getElementById("titulo").innerHTML = textoTitulo;
    document.title = textoTitulo;
}

function getMensajeGanador() {
    if (modo === 1) return turno === 1 ? '¡Jugador 1 ha ganado!' : 'CPU ha ganado';
    else if (modo === 2) return `¡Jugador ${turno} ha ganado!`;
    return '';
}

function cambiarTextoJugador(modo) {
    let jug2Elem = document.getElementById('jugador2');
    jug2Elem.textContent = modo === 1 ? 'CPU' : 'Jugador 2';
}

// FUNCIONES DE CPU
function getMovimientoCPU() {
    let botones = document.querySelectorAll(".btnJuego");
    let libres = [];
    botones.forEach((boton, idx) => {
        let img = boton.querySelector('img');
        if (img.src.endsWith('imagenes/cloudy.png')) libres.push(idx);
    });
    if (libres.length === 0) return -1;
    return libres[Math.floor(Math.random() * libres.length)];
}

function jugarCPU() {
    let botones = document.querySelectorAll(".btnJuego");
    let idx = getMovimientoCPU();
    botones[idx].click();
}

// FUNCION ALEA
function alea(li, ls) {
    return Math.floor(Math.random() * (ls - li)) + li;
}