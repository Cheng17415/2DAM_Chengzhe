//Libro ISBN String Titulo Autor Precio Privados Set y get, toString
private class Libro constructor(private var ISBN: String, private var Titulo: String,
                                private var Autor: String, private var Precio: Float

){

    fun setISBN(ISBN: String){
        this.ISBN = ISBN
    }

    fun getISBN(): String {
        return this.ISBN
    }
    fun setTitulo(Titulo: String){
        this.Titulo = Titulo
    }
    fun getTitulo(): String {
        return this.Titulo
    }
    fun setAutor(Autor: String){
        this.Autor = Autor
    }
    fun getAutor(): String{
        return this.Autor
    }
    fun setPrecio(Precio: Float){
        this.Precio = Precio
    }
    fun getPrecio(): Float{
        return this.Precio
    }

    override fun toString(): String {
        return "ISBN: $ISBN, Titulo: $Titulo, Autor: $Autor, Precio $Precio"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Libro

        if (Precio != other.Precio) return false
        if (ISBN != other.ISBN) return false
        if (Titulo != other.Titulo) return false
        if (Autor != other.Autor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Precio.hashCode()
        result = 31 * result + ISBN.hashCode()
        result = 31 * result + Titulo.hashCode()
        result = 31 * result + Autor.hashCode()
        return result
    }
}

fun main(){
    var libro1 = Libro("ISBNQUBWER", "Don quijote", "Cervantes", 34.54f)
    println(libro1)
    libro1.setISBN("QUETAL")
    println(libro1)
    var libro2 = Libro("QUETAL", "Don quijote", "Cervantes", 34.54f)
    if(libro1 == libro2) println("Son iguales") else println("No son iguales")
}