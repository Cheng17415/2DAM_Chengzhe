fun main() {
    generarTablero()
    do{
        println("¿Dónde está el alfil? ej(E5) ")
        val posicion: String = readln()
        val esta: Boolean = estaEnTablero(posicion.trim())
    } while(!esta)

    //y = mx + a
    //y = x + a
}
fun estaEnTablero(posicion : String): Boolean{
    if (posicion.length !=2){
        println("La longitud es diferente de 2")
        return false
    }
    val letra: Char = posicion[0].uppercaseChar()
    val num: Int = posicion[1].digitToInt()
    if (letra in 'A'..'H' && num in 0..8){
        return true
    }
    println("La posición del alfil no es válido")
    return false
}
fun generarTablero(){
    for(i in 1 ..8){
        for (j in 'a' .. 'h'){
            print("$j$i ")
        }
        println()
    }
}