interface Mueble{
    nombre: string;
    precio: number;
    obtenerPrecio(): number;
}

class Silla implements Mueble{
    private _color: string;
    private _nombre: string;
    private _precio: number;
    constructor(nombre: string, precio:number, color: string) {
    this._nombre = nombre;
    this._precio = precio;
    this._color = color;
    }

    obtenerPrecio(): number {
        return this._precio;
    }

    get color(): string {
        return this._color;
    }

    set color(value: string) {
        this._color = value;
    }

    get nombre(): string {
        return this._nombre;
    }

    set nombre(value: string) {
        this._nombre = value;
    }

    get precio(): number {
        return this._precio;
    }

    set precio(value: number) {
        this._precio = value;
    }
}

class Mesa implements Mueble{
    private _nombre: string;
    private _precio: number;

    constructor(nombre: string, precio: number) {
        this._nombre = nombre;
        this._precio = precio;
    }
    obtenerPrecio(): number {
        return this._precio;
    }

    get nombre(): string {
        return this._nombre;
    }

    set nombre(value: string) {
        this._nombre = value;
    }

    get precio(): number {
        return this._precio;
    }

    set precio(value: number) {
        this._precio = value;
    }
}
function main(){
    let silla: Silla = new Silla("Silla 1", 14.66, "marron");
    console.log(typeof silla);
    console.log(silla.obtenerPrecio())

    type empleado = {
        nombre: string,
        salario: number,
        edad: number
    }
    let emp1: empleado = {
        nombre: "Juan",
        salario: 1244,
        edad: 55
    }
    console.log(emp1)
}

main()