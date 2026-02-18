class CCuenta (private var nombre: String, private var cuenta: String,
               private var saldo: Double, private var tipoDeInteres: Double) {

    fun asignarNombre(nombre: String){
        this.nombre = nombre
    }

    fun obtenerNombre(): String{
        return this.nombre
    }

    fun asignarCuenta(cuenta: String){
        this.cuenta = cuenta
    }

    fun obtenerCuenta(): String{
        return this.cuenta
    }

    fun asignarTipoDeInteres(tipoDeInteres: Double){
        this.tipoDeInteres = tipoDeInteres
    }

    fun obtenerTipoDeInteres(): Double{
        return this.tipoDeInteres
    }

    fun ingreso(cantidad: Double){
        this.cuenta += cantidad
    }
}