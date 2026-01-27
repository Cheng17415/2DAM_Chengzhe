fun main(){
    print("Esciba el numero 1 ")
    val num1 : Int = readln().toInt()
    print("Esciba el numero 2 ")
    val num2 : Int = readln().toInt()
    print("Esciba el numero 3 ")
    val num3 : Int = readln().toInt()
    print("Esciba el numero 4 ")
    val num4 : Int = readln().toInt()

    val total: Int = num1+num2+num3+num4
    val promedio : Double = (total).toDouble()/4
    println("La suma de $num1 y $num2 es de ${num1+num2}")
    println("El producto de $num3 y $num4 es de ${num3*num4}")
    println("El suma de los cuatro es = $total")
    println("El promedio es de $promedio")
}