enum class TipoOperacion (val tipo: String) {
    SUMA("+"),
    RESTA("-"),
    MULTIPLICACION("*"),
    DIVISION("/")
}

class Operacion (val valor1: Int, val valor2: Int, val tipoOperacion: TipoOperacion) {

    fun operar() {

        var resultado: Int = when (tipoOperacion) {
            TipoOperacion.SUMA ->  valor1 + valor2
            TipoOperacion.RESTA -> valor1 - valor2
            TipoOperacion.MULTIPLICACION ->  valor1 * valor2
            TipoOperacion.DIVISION ->  valor1 / valor2
        }
        println("$valor1 ${tipoOperacion.tipo} $valor2 es igual a $resultado")
    }
}

fun main(parametro: Array<String>) {
    val operacion1 = Operacion(10, 4, TipoOperacion.SUMA)
    operacion1.operar()
}