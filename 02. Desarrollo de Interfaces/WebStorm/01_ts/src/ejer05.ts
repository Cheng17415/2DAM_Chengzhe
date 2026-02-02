//Array de numeros
let numeros : number[] = [20, 40.5, 20.2,5]
//Funcion flecha, le pasamos el array como primer parametro y como segundo parametro la cantidad
const arraySumar = (a: number[], b: number): void=>{
  for (let numero in a){
    a[numero] += b;
  }
}
arraySumar(numeros,20);
console.log(numeros)

//Funcion flecha 1. Array num y devuelva una tupla (suma, media)

const resultados = (numeros : number[]): [number, number]=>{
  let suma =numeros.reduce((acc: number, cur: number): number=>acc + cur, 0);
  return [suma, suma/numeros.length]
}

console.log(resultados(numeros));