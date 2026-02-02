import { Estudiante, Perro, Gato } from './claseNew2.ts';

let estudiante1: Estudiante = new Estudiante("jose", 24);
console.log(estudiante1);

const miPerro = new Perro("MiPerro");
miPerro.hacerSonido(); // Salida: MiPerro hace un sonido.
miPerro.ladrar();      // Salida: MiPerro está ladrando.
const miGato = new Gato("Michi");
miGato.hacerSonido();