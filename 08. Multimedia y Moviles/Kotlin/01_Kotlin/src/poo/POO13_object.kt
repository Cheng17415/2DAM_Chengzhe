package poo

object Mayor{
    fun maximo(num1 : Int, num2 : Int) = if (num1 > num2) num1 else num2
    fun maximo(num1 : Float, num2 : Float) = if (num1 > num2) num1 else num2
    fun maximo(num1 : Double, num2 : Double) = if (num1 > num2) num1 else num2
}

fun main(){
    println(Mayor.maximo(4.4,5.6))
    println(Mayor.maximo(5,3))
    println(Mayor.maximo(45.3f, 128.7f))
}