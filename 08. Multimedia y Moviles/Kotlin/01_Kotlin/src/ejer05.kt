fun suma(x1:Int, x2:Int): Int{
    return x1+x2
}

fun multiplicacion(x1:Int, x2:Int): Int{
    return x1*x2
}

fun main(){
    print("Esciba el numero 1 ")
    val num1 : Int = readln().toInt()
    print("Esciba el numero 2 ")
    val num2 : Int = readln().toInt()
    println("La suma de $num1 + $num2 = ${suma(num1,num2)}")
    println("La multiplicacion de $num1 * $num2 = ${multiplicacion(num1,num2)}")
}