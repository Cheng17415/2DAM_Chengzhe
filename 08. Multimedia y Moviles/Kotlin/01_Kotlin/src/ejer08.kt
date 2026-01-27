//Cargar un valor entero por teclado comprendido entre 1 y 99.
//Almacenar en otra variable la cantidad de dígitos que tiene el valor ingresado por teclado.
//Mostrar la cantidad de dígitos del número ingresado por teclado.

fun main(){
    print("Introduzca un valor entero ")
    val num1 = readln().toInt()
    var cant = calcNumDigitos(num1)
    print("El valor $num1 tiene $cant de longitud ")
}


fun calcNumDigitos(num:Int):Int{
    var num2 = num
    var l = 0
    while(num2 != 0){
       l++
       num2 /= 10
   }
    return l
}