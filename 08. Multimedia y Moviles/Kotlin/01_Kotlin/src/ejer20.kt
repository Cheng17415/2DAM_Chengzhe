class Producto(val nombre:String): Cloneable{
    var precio: Double = 12.00
    //Overloaded Constructor
    constructor(vnombre: String, vprecio: Double): this(vnombre){
        this.precio = vprecio
    }

    override fun toString(): String {
        return "Producto(nombre='$nombre', precio=$precio, hashCode, ${hashCode()})"
    }

    public override fun clone(): Producto{
        return Producto(this.nombre, this.precio)
    }
}

fun cambio(n:Producto){
    n.precio *= 1.1
}

fun main(){

    data class User(val name:String, val age:Int)

    var usuario1:User = User("jose", 20)
    var usuario2:User = usuario1.copy()

    println(usuario1)
    println(usuario2)
    if (usuario1==usuario2) println("Son iguales") else println("Son distintos")
    var p1: Producto = Producto("peras")
    var p2: Producto = Producto("peras", 12.00)
    if (p1==p2) println("Son iguales") else println("Son distintos")
    cambio(p2)
    println(p2)

    var puntero = p1.clone()
    cambio(puntero)
    println(puntero)
    println(p1)

}