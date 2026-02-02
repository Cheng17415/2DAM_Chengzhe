export class Animal {
  name: string;
  constructor(name: string) {
    this.name = name;
  }
  makeSound() {
    console.log("Animal is making a sound");
  }
}

export class Persona{
  nombre: string;
  edad: number;
  constructor(nombre: string, edad: number) {
    this.nombre = nombre;
    this.edad = edad;
  }
  imprimir():string{
    return `${this.nombre} ${this.edad}`;
  }
}
export function sumar(a: number, b: number): number {
  return a + b; // Tipo inferido como number
}

// Definimos una interfaz 'Usuario' con propiedades 'nombre' y 'email'
export interface User {
  nombre: string;
  email: string;
}

// Definimos una interfaz 'Empleado' con propiedades 'empleadoID' y 'departamento'
export interface Employee {
  empleadoID: number;
  departamento: string;
}

// Creamos un alias de tipo 'UsuarioEmpleado' que combina las interfaces 'Usuario' y 'Empleado'
export type UsuarioEmpleado = User & Employee;

export class Libro {
  constructor(public titulo: string, public autor: string) {
  }
}

export class CuentaBancaria {
  private saldo: number;

  constructor(saldoInicial: number) {
    this.saldo = saldoInicial;
  }

  public mostrarSaldo(): void {
    console.log(`Saldo: ${this.saldo}`);
  }

  public depositar(cantidad: number): void {
    if (cantidad > 0) {
      this.saldo += cantidad;
    }
  }
}