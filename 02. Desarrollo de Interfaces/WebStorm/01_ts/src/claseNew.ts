export class Vehicle {
  protected marca: string;

  constructor(marca: string) {
    this.marca = marca;
  }
}

export class Car extends Vehicle {
  public modelo: string;

  constructor(marca: string, modelo: string) {
    super(marca);
    this.modelo = modelo;
  }

  // la clase coche puede usar .marca
  public mostrarDetalles(): void {
    console.log(`Marca: ${this.marca}, Modelo: ${this.modelo}`);
  }
}

export class Configuracion {
  nombre :string
  static contador: number = 0
  constructor(n: string) {
  this.nombre = n;
  Configuracion.contador ++;
  }
  public imprimir():void{
    console.log(`Nombre: ${this.nombre}, Contador: ${Configuracion.contador}`)
  }
}

