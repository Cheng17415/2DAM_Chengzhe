fun main() {
    print("Introduzca el numero del mes ")
    val mes:Int = readln().toInt()
    val trimestre: String = when{
        mes <=3 -> "Primer trimestre"
        mes <=6 -> "Segundo trimestre"
        mes <=9 -> "Tercer trimestre"
        mes <=12 -> "Cuarto trimestre"
        else -> "No pertene a ningun trimestre"
    }
    val semestre: String = when{
        mes <=6 -> "Primer semestre"
        mes <=12 -> "Segundo semestre"
        else -> "No pertene a ningun semestre"
    }
    println("El mes $mes pertenece al $trimestre y al $semestre")
}