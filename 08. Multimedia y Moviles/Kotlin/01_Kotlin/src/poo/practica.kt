package poo

private class Persona4(val nombre: String, var edad: Int) {
    override fun toString(): String{
        return "$nombre, $edad"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Persona4

        if (edad != other.edad) return false
        if (nombre != other.nombre) return false

        return true
    }

    override fun hashCode(): Int {
        var result = edad
        result = 31 * result + nombre.hashCode()
        return result
    }

}

fun main(){
    val p1 = Persona4("Pedro", 25)
    println(p1)
    val p2 = Persona4("Pedro", 25)
    println(p1 == p2)
}

