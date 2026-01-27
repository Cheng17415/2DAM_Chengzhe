fun main() {
    println("Ingrese un valor entero")
    val valor = readln().toInt()
    val esPar : Boolean = if(valor % 2 == 0){
        println("$valor es par")
        true
    } else{
        println("$valor es impar")
        false
    }
    print(esPar)
}