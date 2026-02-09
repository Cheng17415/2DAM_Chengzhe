package poo

private class Libro constructor(private var ISBN: String, private var titulo: String,
                                private var autor: String, private var precio: Float

){

    fun setISBN(ISBN: String){
        this.ISBN = ISBN
    }

    fun getISBN(): String {
        return this.ISBN
    }
    fun setTitulo(titulo: String){
        this.titulo = titulo
    }
    fun getTitulo(): String {
        return this.titulo
    }
    fun setAutor(autor: String){
        this.autor = autor
    }
    fun getAutor(): String{
        return this.autor
    }
    fun setPrecio(precio: Float){
        this.precio = precio
    }
    fun getPrecio(): Float{
        return this.precio
    }

    override fun toString(): String {
        return "ISBN: $ISBN, Titulo: $titulo, Autor: $autor, Precio $precio"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Libro

        if (precio != other.precio) return false
        if (ISBN != other.ISBN) return false
        if (titulo != other.titulo) return false
        if (autor != other.autor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = precio.hashCode()
        result = 31 * result + ISBN.hashCode()
        result = 31 * result + titulo.hashCode()
        result = 31 * result + autor.hashCode()
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