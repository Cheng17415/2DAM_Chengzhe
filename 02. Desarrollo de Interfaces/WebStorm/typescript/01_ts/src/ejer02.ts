import { Libro, Persona, CuentaBancaria } from './clases.ts';

let personas : Array<Persona> = [
    new Persona("Pedro", 40),
    new Persona("Cheng", 22),
    new Persona("Silva", 25)
];

for (let persona of personas) {
  console.log(persona);
}

let amigos: Persona[] = [];
amigos.push(
    new Persona("Pedro", 40),
    new Persona("Cheng", 22),
    new Persona("Silva", 25)
)

for(let i:number=0; i<amigos.length; i++){
  console.log(amigos[i]);
}

console.log("==================");
for (const i in amigos){
  console.log(amigos[i].imprimir());
}

console.log("==================");

amigos.forEach((amigo: Persona, index: number): void => console.log(index +" " + amigo.imprimir()));

type Respuesta<T> = {
  exito: boolean;
  datos: T;
  mensaje?: string;
};

const respuesta1: Respuesta<string> = {
  exito: true,
  datos: "Operación exitosa"
};

const respuesta2: Respuesta<number> = {
  exito: false,
  datos: 0,
  mensaje: "Error en la operación"
};
console.log("==================");
console.log(respuesta1.datos); // Operación exitosa
console.log(respuesta2.mensaje); // Error en la operación
console.log("==================");
const libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez");
console.log(libro1);

const cuenta = new CuentaBancaria(1000);
cuenta.mostrarSaldo();    // Salida: Saldo: 1000
cuenta.depositar(500);
cuenta.mostrarSaldo();    // Salida: Saldo: 1500

//Aunque sea privada, es accesible ya que JS no tiene estos modificadores.
//console.log(cuenta.saldo);  // Error: Property 'saldo' is private and only accessible within class 'CuentaBancaria'.