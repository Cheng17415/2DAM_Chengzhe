class Persona4(val nombre: String, var edad: Int) { // Constructor Primario

    init {
        println("Persona $nombre creada con éxito.")
    }

    // Constructor Secundario
    constructor(nombre: String) : this(nombre, 0) {
        println("Edad no proporcionada, seteada en 0.")
    }
}

fun main(){
    val persona1 = Persona4("Pepe", 25)
    val persona2 = Persona4("Juan")
}

/*fun OOP.OOP.OOP.main(){
    val lista = "pan,leche,huevos".split(",")
    println(lista)

    val texto = "Hola Mundo"
    println(texto.length)

    val edad = 17
    val mensaje = if (edad >= 18){
        "Eres mayor de edad"
    } else{
        "Eres menor"
    }
    println(mensaje)
}*/
