// Variables globales
const intentos = 8; // Número máximo de intentos
const cantidadBolas = 4; // Número de bolas por intento
let colores = ['R', 'A', 'V', 'Z', 'M', 'N']; // Colores disponibles
let posi = 0; // Posición actual en filas
let posj = 0; // Posición actual en columnas
let solucion = []; // Solución generada aleatoriamente
let intentoUsuario = []; // Intento actual del usuario

// Función principal que inicializa el juego
function inicio() {
  const destino = document.getElementById('usuario');
  generarTablero();
  generarBolasInter();
  generarBotonColores(destino);
  generarBotonRetro(destino);
  generarSolucionAlea();
  botonReset();
}

// Genera el tablero de juego
function generarTablero() {
  const tablero = document.getElementById('tablero');
  tablero.innerHTML = '';
  for (let i = intentos - 1; i >= 0; i--) {
    const fila = document.createElement('tr');
    tablero.appendChild(fila);

    // Celdas para las bolas del jugador
    for (let j = 0; j < cantidadBolas; j++) {
      const celda = document.createElement('td');
      celda.id = `esf${i}-${j}`;
      celda.classList.add('casilla');
      fila.appendChild(celda);
    }

    // Celda para las pistas
    const celdaPistas = document.createElement('td');
    celdaPistas.id = `pistas-${i}`;
    celdaPistas.classList.add('pistas');
    for (let k = 1; k <= cantidadBolas; k++) {
      const punto = document.createElement('div');
      punto.classList.add('pista-punto');
      celdaPistas.appendChild(punto);
    }
    fila.appendChild(celdaPistas);
    tablero.appendChild(fila);
  }
}

// Genera las bolas de solución (interrogantes al inicio)
function generarBolasInter() {
  const destino = document.getElementById('solucion');
  for (let i = 0; i < cantidadBolas; i++) {
    const bola = document.createElement('span');
    bola.classList.add('esferasSolucion');
    bola.innerHTML = '?';
    destino.appendChild(bola);
  }
}

// Cambia las bolas de solución para mostrar la respuesta
function cambiarBolasInter() {
  document.querySelectorAll('#solucion span').forEach((bola, index) => {
    bola.innerHTML = '';
    bola.classList.add(solucion[index]);
  });
}

// Genera los botones de colores
function generarBotonColores(destino) {
  colores.forEach(color => {
    const esfera = document.createElement('button');
    esfera.classList.add('color', color);
    esfera.id = color;
    esfera.type = 'button';

    esfera.handler = () => pulsarColor(esfera);
    esfera.addEventListener('click', esfera.handler);
    destino.appendChild(esfera);
  });
}

// Genera el botón de retroceso
function generarBotonRetro(destino) {
  const deleteButton = document.createElement('button');
  deleteButton.classList.add('btnRetroceso');
  deleteButton.type = 'button';
  deleteButton.innerHTML = '←';
  deleteButton.handler = () => pulsarRetroceso();
  deleteButton.addEventListener('click', deleteButton.handler);
  destino.appendChild(deleteButton);
}

// Configura el botón de reinicio
function botonReset() {
  const reset = document.getElementById('reset');
  reset.textContent = 'Reset';
  reset.addEventListener('click', resetJuego);
}

// Reinicia el juego
function resetJuego() {
  posi = 0;
  posj = 0;
  intentoUsuario = [];
  solucion = [];

  // Limpiar tablero
  document.querySelectorAll('.casilla').forEach(celda => {
    colores.forEach(color => celda.classList.remove(color));
  });

  // Limpiar pistas
  document.querySelectorAll('.pista-punto').forEach(punto => {
    punto.classList.remove('R', 'N');
  });

  // Ocultar solución
  document.querySelectorAll('#solucion span').forEach(bola => {
    bola.className = 'esferasSolucion';
    bola.textContent = '?';
  });

  // Nueva solución
  generarSolucionAlea();
}

// Maneja el evento de pulsar un color
function pulsarColor(elem) {
  if (posi >= intentos) return;
  const bola = document.getElementById(`esf${posi}-${posj}`);
  bola.classList.add(elem.id);
  if ((posj + 1) % cantidadBolas === 0) {
    posi++;
    posj = 0;
    intentoUsuario.push(elem.id);
    evaluar();
  } else {
    posj++;
    intentoUsuario.push(elem.id);
  }
}

// Maneja el evento de retroceso
function pulsarRetroceso() {
  if (posi === 0 && posj === 0 || posj === 0) return;
  posj--;
  const bola = document.getElementById(`esf${posi}-${posj}`);
  bola.classList.forEach((e) => {
    colores.forEach(color => {
      if (color === e) {
        bola.classList.remove(e);
      }
    });
  });
  intentoUsuario.pop();
}

// Genera una solución aleatoria
function generarSolucionAlea() {
  for (let i = 0; i < cantidadBolas; i++) {
    solucion[i] = colores[alea(0, colores.length - 1)];
  }
}

// Evalúa el intento del usuario
function evaluar() {
  let posExactas = 0, posAprox = 0;
  let solucionClon = solucion.slice();
  let empieza = intentoUsuario.length - cantidadBolas;
  let intentoUsuarioClon = intentoUsuario.slice(empieza);
  let numPistaElem = empieza / cantidadBolas;
  let pistasElem = document.querySelector(`#pistas-${numPistaElem}`);
  let numPista = 0;

  for (let i = 0; i < solucionClon.length; i++) {
    if (solucionClon[i] === intentoUsuarioClon[i]) {
      solucionClon[i] = '-';
      intentoUsuarioClon[i] = '-';
      pistasElem.children[numPista++].classList.add('R');
    }
  }

  // El usuario ha acertado todos
  if (posExactas == cantidadBolas) {
    cambiarBolasInter();
    return true;
  }

  for (let i = 0; i < solucionClon.length; i++) {
    if (intentoUsuarioClon[i] === '-') continue;

    for (let j = 0; j < solucionClon.length; j++) {
      if (solucionClon[j] === '-') continue;

      if (intentoUsuarioClon[i] === solucionClon[j]) {
        solucionClon[j] = '-';
        intentoUsuarioClon[i] = '-';
        posAprox++;
        pistasElem.children[numPista].classList.add('N');
        numPista++;
        break;
      }
    }
  }
  return false;
}

// Genera un número aleatorio entre un rango
function alea(li, ls) {
  return Math.floor(Math.random() * (ls - li + 1) + li);
}

// Inicia el juego al cargar la página
window.addEventListener('load', () => inicio());