//Se ingresa por teclado un valor entero, mostrar una leyenda que indique si el número es positivo, nulo o negativo.
fun main(){
    println("Introduzca un valor entero")
    val val1: Int = readln().toInt()
    val leyenda: String = if(val1>0) "positivo" else if(val1<0) "negativo" else "cero"
    println("El valor $val1 es $leyenda")
}