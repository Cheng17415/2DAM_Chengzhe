fun main(){
    print("Esciba el numero 1 ")
    val num1 : Int = readln().toInt()
    print("Esciba el numero 2 ")
    val num2 : Int = readln().toInt()
    val mayor = if (num1 > num2) num1 else num2
    println("El mayor es $mayor")
}