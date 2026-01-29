data class Articulo2(var codigo: Int, var descripcion: String, var precio: Float)

fun main(p: Array<String>){
    val articulo1 = Articulo2(1,"papas", 34f)
    articulo1.codigo = 22
    print(articulo1)
}