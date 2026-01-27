fun main(parametro: Array<String>) {
    val sueldos: IntArray
    sueldos = IntArray(5)
    //carga de sus elementos por teclado
    for(i in 0 until sueldos.size) {
        print("Ingrese sueldo:")
        sueldos[i] = readln().toInt()
    }
    //impresion de sus elementos
    for(i in 0 until sueldos.size) {
        println(sueldos[i])
    }
    val sueldos2 = DoubleArray(5)
}