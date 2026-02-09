package poo

import java.util.Locale.getDefault

private class Persona2 (nombre: String, edad: Int) {
     var nombre: String = nombre
        set(valor){
            field = valor.uppercase(getDefault())
        }
        get(){
            return field
        }
     var edad: Int = edad
        set(valor){
            field = if (valor >= 0 ) valor else 0
        }


    fun imprimir() {
        println("Nombre: $nombre y tiene una edad de $edad")
    }

    fun esMayorEdad() {
        if (edad >= 18)
            println("Es mayor de edad $nombre")
        else
            println("No es mayor de edad $nombre")
    }

}

fun main(parametro: Array<String>) {
    val persona1 = Persona2("Juan", 12)
    persona1.imprimir()
    persona1.esMayorEdad()
    persona1.nombre= "juan jose"
    persona1.edad = 25
    persona1.imprimir()
}
