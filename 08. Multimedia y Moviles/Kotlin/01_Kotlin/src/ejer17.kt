data class Articulo(var codigo: Int, var descripcion: String, var precio: Float)

fun main(parametro: Array<String>) {
    val articulo1 = Articulo(1, "papas", 34f)
    var articulo2 = Articulo(2, "manzanas", 24f)
    println(articulo1)
    println(articulo2)
    val puntero = articulo1
    puntero.precio = 100f
    println(articulo1)
    var articulo3 = articulo1.copy()
    articulo1.precio = 200f
    println(articulo1)
    println(articulo3)
    if (articulo1 == articulo3)
        println("Son iguales $articulo1 y $articulo3")
    else
        println("Son distintos $articulo1 y $articulo3")
    articulo3.precio = 200f
    if (articulo1 == articulo3)
        println("Son iguales $articulo1 y $articulo3")
    else
        println("Son distintos $articulo1 y $articulo3")
}