import { Car, Configuracion } from './claseNew.ts';

const coche = new Car("Toyota", "Corolla");
coche.mostrarDetalles();  // Salida: Marca: Toyota, Modelo: Corolla

// el programa no puede usar marca directamente
//console.log(coche.marca);  // Error: Property 'marca' is protected and only accessible within class 'Vehiculo' and its subclasses.

// Uso de las variables estáticas
let conf: Configuracion = new Configuracion("Quique");
let conf2: Configuracion = new Configuracion("Enrique");
console.log(conf.nombre);         // Salida: https://api.ejemplo.com
console.log(Configuracion.contador);    // Salida: 5
conf.imprimir();
console.log(conf2.nombre);
console.log(Configuracion.contador);
conf2.imprimir();

