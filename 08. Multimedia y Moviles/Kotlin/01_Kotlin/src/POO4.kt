private class Punto constructor(x: Int, y: Int){
    var x: Int = x
    var y: Int = y
    fun imprimir(){
        when {
            x > 0 && y > 0 -> println("Primer cuadrate")
            x < 0 && y > 0 -> println("Segundo cuadrante")
            x < 0 && y < 0 -> println("Tercer cuadrante")
            x > 0 && y < 0 -> println("Cuarto cuadrante")
            else -> println("El punto se encuentra en un eje")
        }
    }
}

fun main(){
    var p1 = Punto(3,-2)
    var p2 = Punto(-3,4)
    p1.imprimir()
    p2.imprimir()
}