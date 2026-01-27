fun main(){
    var peso1 : Float = 90.5f
    var peso2 : Float = 85.5f
    var peso3 : Float = 55.5f
    var media = (peso1+peso2+peso3)/3
    println("La media es de $media")
    println("La media es de ${(peso1+peso2+peso3)/3}")

    val v1: Int = 12
    val v2: Int = 5
    val v3: Float = (v1/v2).toFloat()
    val v4 = (v1/v2)
    println("$v3")
    print(v4)
}