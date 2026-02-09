package poo

enum class TipoCarta{
    DIAMANTE,
    TREBOL,
    CORAZON,
    PICA
}

class Carta(var tipo: TipoCarta, var valor: Int) {

    fun imprimir() {
        println("OOP.Carta: $tipo y su valor es $valor")
    }
}

fun main(parametro: Array<String>) {
    val carta1 = Carta(TipoCarta.TREBOL, 4)
    carta1.imprimir()
    carta1.tipo = TipoCarta.DIAMANTE
    carta1.imprimir()
}