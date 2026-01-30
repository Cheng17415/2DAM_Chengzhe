enum class Paises (val habitantes: Int, val capital :String) {
    BRASIL (202450649, "Brasilia"),
    COLOMBIA (50364000, "Bogota"),
    PERU (31151643, "Lima"),
    VENEZUELA (31028337, "Caracas"),
    CHILE (18261884, "Santiago de Chile"),
    ECUADOR (16298217, "Qito"),
    BOLIVIA (10888000, "La paz"),
    PARAGUAY (6460000, "Asuncion"),
    URUGUAY (3372000, "Montevideo")
}

fun main(parametro: Array<String>) {
    val pais1 = Paises.BRASIL
    println(pais1)
    println(pais1.habitantes)
    print(pais1.capital)
}