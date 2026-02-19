class CCuentaCorrienteConIn(nombre: String, cuenta: String,
                            saldo: Double, tipoDeInteres: Double,
                            transacciones: Int, importePorTrans: Double,
                            transExentas: Int
                            ): CCuentaCorriente(nombre, cuenta, saldo, tipoDeInteres,
                                                transacciones, importePorTrans, transExentas) {
    override fun intereses() {
        if (this.saldo >= 3000) saldo += this.saldo * this.tipoDeInteres
    }
}