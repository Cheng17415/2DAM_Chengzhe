package poo

import kotlin.math.pow

abstract class FiguraGeometrica(protected val lado1: Double){

    abstract fun calcularArea() :Double
    abstract fun calcularPerimetro(): Double
    override fun toString(): String {
        return "OOP.FiguraGeometrica(lado1=$lado1)"
    }
}

class Cuadrado(lado1: Double): FiguraGeometrica(lado1){
    override fun calcularArea(): Double {
        return lado1 * lado1
    }

    override fun calcularPerimetro(): Double {
        return lado1 * 4
    }

    override fun toString(): String {
        return "OOP.Cuadrado(lado1=$lado1)"
    }

}
class Rectangulo(lado1: Double, private val lado2: Double): FiguraGeometrica(lado1){
    override fun calcularArea(): Double {
        return lado1 * lado2
    }

    override fun calcularPerimetro(): Double {
        return lado1 * 2 + lado2 * 2
    }

    override fun toString(): String {
        return "OOP.Rectangulo(lado1=$lado1, lado2=$lado2)"
    }

}

class Circulo(var radio: Double): FiguraGeometrica(radio){
    override fun calcularArea(): Double {
        return radio.pow(2.0) * Math.PI
    }

    override fun calcularPerimetro(): Double {
        return 2 * radio * Math.PI
    }

    override fun toString(): String {
        return "OOP.Circulo(radio=$lado1)"
    }

}

fun main(){
    //var figura = OOP.FiguraGeometrica(5.3)
    var lista: MutableList<FiguraGeometrica> = mutableListOf()
    lista.add(Cuadrado(9.0))
    lista.add(Rectangulo(5.0,4.0))
    lista.add(Circulo(6.0))
    var lista1: List<FiguraGeometrica> = listOf(Cuadrado(9.0),Rectangulo(5.0,4.0), Circulo(6.0))
    for (figura in lista1){
        println(figura)
        println("${figura.calcularArea()}")
        println("${figura.calcularPerimetro()}")
    }

}