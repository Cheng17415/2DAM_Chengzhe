private fun imprimirString(m:Array<Array<String>>){
    for(i in m.size -1 downTo 0){
        for(j in 0 until  m[i].size){
            print(String.format("%3s",m[i][j]))
        }
        println()
    }
}

private fun imprimirInt(m:Array<IntArray>){
    for(i in 0 until m.size){
        for(j in 0 until m[i].size){
            print(String.format("%3d",m[i][j]))
        }
        println()
    }
}

private fun rellenar(m:Array<IntArray>){
    var c = 0
    var nfc: Int = m[0].size
    var filas:Int = m.size
    for (i in 0 until nfc){
        m[filas-2][i] = (i * nfc) + (i + 1)
        m[filas-1][i] = (i * nfc) + (nfc - i)

        for (j in 0 until nfc){
            m[c][j] = (i * nfc) + (j + 1)
            m[c + nfc][j] = (j * nfc) + (i + 1)
        }
        c++
    }
}

fun main(){
    var nf:Int=3
    var turno:Boolean = true
    var opcionesValidas = Array((nf*2)+2){IntArray(nf)}
    rellenar(opcionesValidas)
    var matriz = Array(nf){Array(nf){"X"} }

    var c1:Int = 0
    var c2:Int = 0
    var ganador : Boolean = false
    while(true){
        imprimirString(matriz)
        tirarJugador(matriz,turno)
        if (turno){
            c1++
            if(c1>=nf){
                ganador = comprobar(turno, matriz, opcionesValidas)
            }
        } else{
            c2++
            if(c2>=nf){
                ganador = comprobar(turno, matriz, opcionesValidas)
            }
        }
        if (ganador){
            println("Ganador jugador " + if(turno) 1 else 2)
            break
        }
        if (c1+c2 == nf*2){
            println("Empatados")
            break
        }
        turno =!turno
    }
}

fun comprobar(t: Boolean, m: Array<Array<String>>, ov: Array<IntArray>): Boolean {
    var c : Int
    var n : Int
    var fila : Int
    var columna : Int
    var jugador : String = if(t) "1" else "2"
    for(i in 0 until ov.size){
        c = 0
        for (j in 0 until ov[i].size){
            n =  ov[i][j]
            fila = (n-1)/(ov[i].size)
            columna = (n-1)%ov[i].size
            if (m[fila][columna] == jugador) c++
        }
        if(c == ov[i].size) return true
    }
    return false
}

fun tirarJugador(m: Array<Array<String>>, t: Boolean) {
    do{
        print("Fila ")
        var f:Int = readln().toInt()
        print("Columna ")
        var c: Int = readln().toInt()
        if (c in 0 until m.size && f in 0 until m.size){
            if (m[f][c] =="X"){
                m[f][c] = if(t) "1" else "2"
                break
            } else{
                println("No esta libre")
            }
        } else{
            println("Fuera de limites")
        }
    } while(true)

}