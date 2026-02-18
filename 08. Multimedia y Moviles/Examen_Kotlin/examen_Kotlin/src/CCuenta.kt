abstract class CCuenta (protected var nombre: String, protected var cuenta: String,
                        protected var saldo: Double, protected var tipoDeInteres: Double) {

    fun asignarNombre(nombre: String){ this.nombre = nombre }

    fun obtenerNombre(): String{ return this.nombre }

    fun asignarCuenta(cuenta: String){ this.cuenta = cuenta }

    fun obtenerCuenta(): String{ return this.cuenta }

    fun asignarTipoDeInteres(tipoDeInteres: Double){ this.tipoDeInteres = tipoDeInteres }

    fun obtenerTipoDeInteres(): Double{ return this.tipoDeInteres }

    open fun ingreso(cantidad: Double){
        if (cantidad < 0.0) return
        this.saldo += cantidad
    }
    open fun reintegro(cantidad: Double){
        if (cantidad > 0.0 && this.saldo >= cantidad){
            this.saldo -= cantidad
        }
    }

    fun estado(): Double{ return this.saldo }

    abstract fun comisiones()
    abstract fun intereses(): Double
}