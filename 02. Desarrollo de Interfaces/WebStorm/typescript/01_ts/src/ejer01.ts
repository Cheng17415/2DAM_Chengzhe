import { Animal, Persona, sumar, UsuarioEmpleado} from './clases.ts';

const PI: number = 3.1416;
console.log(`PI =  ${PI}`); // 3.1416

// PI = 3.14; // Error: No se puede asignar un nuevo valor a una constante

let nombre: string = "Luis";
let edad: number = 25;
let esMayor: boolean = true;
let listaNumeros: number[] = [1, 2, 3, 4, 5];
let persona: { nombre: string, edad: number } = { nombre: nombre, edad: edad };
console.log(`Persona tiene ${persona.edad} y se llama ${persona.nombre}`)
console.log(`Es mayor de edad: ${esMayor}`)

//For each de indices
console.log("\nFor each de indices")
for (const l in listaNumeros){
    console.log(listaNumeros[l]);
}

let resultado: number = sumar(1, 2); // Tipo inferido como number
console.log("\nEl resultado de la suma es de "+resultado);

//funcion que devuelve una tupla
type employee = [string, number];
function crearPersona(): employee{
  return ["pedro", 22];
}

let emp1: employee = crearPersona();
console.log("Tupla con empleado implementando type: " + emp1);

let a: Animal = new Animal("Perro");
a.makeSound();
console.log(`El nombre del animal es de ${a.name}`);

let persona1: Persona = new Persona("ana maria", 50);
console.log(`\nPersona: ${persona1.imprimir()}`);

console.log("\nUnion")
let age: number | string;
age = 25;
console.log("Tipo de age: " + typeof age + " " + age);
age = "25";
console.log("Tipo de age: " + typeof age + " " + age);

//Tipo de dato interseccion

type Admin = {
  name: string;
  isAdmin: boolean;
}

type Employee = {
  name: string;
  salary: number;
}

type AdminEmployee = Admin & Employee;

let adminEmpleado: AdminEmployee ={
  name : 'jose',
  isAdmin : true,
  salary : 5000
};
console.log(`\nAdmin y empleado: ${adminEmpleado.name}`);

const empleado: UsuarioEmpleado = {
  nombre: "Ana García",
  email: "ana.garcia@example.com",
  empleadoID: 101,
  departamento: "Desarrollo"
};

console.log("\n" + empleado.nombre); // Ana García
console.log(empleado.departamento); // Desarrollo

type Operacion = (a: number, b: number) => number;

const sumar2: Operacion = (a: number, b : number): number => a + b;
const restar: Operacion = (a: number, b : number): number => a - b;

console.log("\n" + sumar2(5, 3)); // 8
console.log(restar(5, 3)); // 2
