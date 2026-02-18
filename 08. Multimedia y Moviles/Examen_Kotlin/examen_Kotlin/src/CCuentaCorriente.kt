class CCuentaCorriente (nombre: String, cuenta: String,
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
        this.saldo -= this.importePorTrans * this.transacciones
        this.transacciones = 0

        /*TODO
        *  val noExentas = if (transacciones > transExentas)
            transacciones - transExentas
        else 0

        saldo -= noExentas * importePorTrans
        transacciones = 0*/
    }

    override fun intereses(){
        TODO("Not yet implemented")
        /*var interesMensual = 0.0

        if (saldo <= 3000) {
            interesMensual = saldo * 0.005 / 12
        } else {
            val parte1 = 3000 * 0.005 / 12
            val parte2 = (saldo - 3000) * (obtenerTipoDeInteres() / 100) / 12
            interesMensual = parte1 + parte2
        }

        saldo += interesMensual*/
    }
}