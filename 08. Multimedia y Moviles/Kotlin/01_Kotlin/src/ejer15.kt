fun rellenar(matriz : IntArray){
    //Rellenar de forma aleatoria entre 200 y 500
    for (i in 0 until matriz.size){
        matriz[i] = alea(200,500)
    }
}
fun imprimir(matriz: IntArray){
    for (num in matriz){
        print("$num  ")
    }
}
fun main(){
    val sueldos: IntArray = IntArray(5)
    rellenar(sueldos)
    imprimir(sueldos)
}

fun alea(li: Int, ls: Int): Int{
    return  (Math.random() * (ls - li + 1)).toInt() + li
}
