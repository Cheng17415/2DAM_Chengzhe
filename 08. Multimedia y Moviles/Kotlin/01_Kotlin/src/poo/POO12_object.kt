package poo/*object Matematica{
    val PI = 3.1416
    fun aleatorio(minimo: Int, maximo: Int) = ((Math.random() * (maximo + 1 - minimo)) + minimo).toInt()
}*/

fun main(parametro: Array<String>) {
    val mates = object {
            val PI = 3.1416
            fun aleatorio(minimo: Int, maximo: Int) = ((Math.random() * (maximo + 1 - minimo)) + minimo).toInt()

    }
//    println("El valor de Pi es ${Matematica.PI}")
//    print("Un valor aleatorio entre 5 y 10: ")
//    println(Matematica.aleatorio(5, 10))
    println("El valor de Pi es ${mates.PI}")
    print("Un valor aleatorio entre 5 y 10: ")
    println(mates.aleatorio(5, 10))
}