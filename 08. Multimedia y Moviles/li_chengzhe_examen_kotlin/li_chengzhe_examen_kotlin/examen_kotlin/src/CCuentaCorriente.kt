open class CCuentaCorriente (nombre: String, cuenta: String,
                             saldo: Double, tipoDeInteres: Double,
                             private var transacciones: Int,
                             private var importePorTrans: Double,
                             private var transExentas: Int): CCuenta(nombre, cuenta, saldo, tipoDeInteres) {

    fun decrementarTransacciones(){ this.transacciones-- }

    fun asignarImportePorTrans(importe: Double){
        if (importe < 0.0) return;
        this.importePorTrans = importe
    }

    fun obtenerImportePorTrans(): Double{ return this.importePorTrans }

    fun asignarTransExentas(transExentas: Int){
        if (transExentas < 0) return;
        this.transExentas = transExentas
    }

    fun obtenerTransExentas(): Int{
        return this.transExentas
    }

    override fun ingreso(cantidad: Double){
        if (cantidad < 0.0) return
        this.saldo += cantidad
        this.transacciones++
    }

    override fun reintegro(cantidad: Double){
        val cantFinal = this.saldo - cantidad
        if (cantidad < 0.0 || cantFinal < 0.0) return
        this.saldo = cantFinal
        this.transacciones++
    }
    override fun comisiones() {
        var noExentas = this.transacciones - this.transExentas
        if (noExentas < 0) noExentas = 0
        saldo -= noExentas * importePorTrans
        transacciones = 0
    }

    override fun intereses(){
        if(this.saldo <= 0) return

        val maximo = 3000
        val interesMaximo = 0.005

        if(this.saldo < maximo){
            this.saldo += this.saldo * interesMaximo
            return
        }

        val restoDinero = this.saldo - maximo

        this.saldo += maximo * interesMaximo + restoDinero * this.tipoDeInteres
    }
}