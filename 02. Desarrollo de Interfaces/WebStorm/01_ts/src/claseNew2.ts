export interface Persona {
  nombre: string;
  edad: number;
  saludar(): void;
}
export class Estudiante implements Persona {
  nombre: string;
  edad: number;

  constructor(nombre: string, edad: number) {
    this.nombre = nombre;
    this.edad = edad;
  }

  saludar() {
    console.log(`Hola, mi nombre es ${this.nombre} y tengo ${this.edad} años.`);
  }
}

// Clase base
export class Animal {
  constructor(public nombre: string) {}

  hacerSonido(): void {
    console.log(`${this.nombre} hace un sonido.`);
  }
}

// Clase derivada
export class Perro extends Animal {
  ladrar(): void {
    console.log(`${this.nombre} está ladrando.`);
  }
}

export class Gato extends Animal {
  override hacerSonido(): void {
    console.log(`${this.nombre} maúlla.`);
  }
}

export class Pato extends Animal {
  constructor(nombre: string, public raza: string) {
    super(nombre); // Llama al constructor de la clase base
  }

  mostrarInfo(): void {
    console.log(`Nombre: ${this.nombre}, Raza: ${this.raza}`);
  }
}