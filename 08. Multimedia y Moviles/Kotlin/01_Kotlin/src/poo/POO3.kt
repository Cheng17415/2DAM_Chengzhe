package poo

private class Alumno constructor(nombre : String, nota: Float){
    var nombre: String = nombre
    var nota: Float = nota

    fun imprimir(){
        println("Nombre: $nombre tiene una nota $nota")
        if (nota>=4) println("Esta regular")
    }
}

fun main(){
    val alumno = Alumno("Pepito", 9.5f)
    alumno.imprimir()
    val alumno2 = Alumno("Isaias", 3.5f)
    alumno2.imprimir()
}