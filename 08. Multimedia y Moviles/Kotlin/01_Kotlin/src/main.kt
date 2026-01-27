fun main() {
    var valor1 : Int = 100 //Val variable inmutables
    var valor2 : Int = 400
    val mensaje : String = "hola como estais $valor1 + $valor2"
    var resultado: Int
    resultado = valor1 + valor2
    println("La suma de $valor1 + $valor2 es $resultado")
    resultado = valor1 * valor2
    println("El producto de $valor1 * $valor2 es $resultado")
}