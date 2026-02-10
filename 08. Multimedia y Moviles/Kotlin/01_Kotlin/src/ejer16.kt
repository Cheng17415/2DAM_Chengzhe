fun main() {
    var posicion: String
    var esta: Boolean
    do{
        println("¿Dónde está el alfil? ej(E5) ")
        posicion = readln()
        esta = estaEnTablero(posicion.trim())
    } while(!esta)
    val posiciones = posicionesDisponibles(posicion)
    for(pos in posiciones){
        println(pos)
    }
}

fun estaEnTablero(posicion : String): Boolean{
    if (posicion.length !=2){
        println("La longitud es diferente de 2")
        return false
    }
    val letra: Char = posicion[0].uppercaseChar()
    val num: Int = posicion[1].digitToInt()
    if (letra in 'A'..'H' && num in 1..8){
        return true
    }
    println("La posición del alfil no es válido")
    return false
}
fun posicionesDisponibles(posi: String): MutableList<String> {

    val x = letraANum(posi[0])
    val y = posi[1].digitToInt()

    val a = y - x
    val b = y + x

    val posiciones = mutableListOf<String>()

    for (xe in 1..8) {

        val ye1 = xe + a
        if (ye1 in 1..8 && !(xe == x && ye1 == y)) {
            posiciones.add("${numALetra(xe)}$ye1")
        }

        val ye2 = -xe + b
        if (ye2 in 1..8 && !(xe == x && ye2 == y)) {
            posiciones.add("${numALetra(xe)}$ye2")
        }
    }

    return posiciones
}

fun letraANum(letra: Char): Int{
    val abecedario: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val letraMayus = letra.uppercaseChar()
    if (letraMayus !in abecedario){
        return -1
    }
    return abecedario.indexOf(letraMayus) + 1
}

fun numALetra(num: Int): Char{
    val abecedario: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return abecedario[num - 1]
}