class CCuentaAhorro(nombre: String, cuenta: String,
                    saldo: Double, tipoDeInteres: Double,
                    private var cuotaMantenimiento: Double): CCuenta(nombre, cuenta, saldo, tipoDeInteres) {

    fun asignarCuotaManten(cuota: Double){
        this.cuotaMantenimiento = if (cuota < 0.0) this.cuotaMantenimiento else cuota
    }

    fun obtenerCuotaManten(): Double{ return this.cuotaMantenimiento }

    override fun comisiones() { this.saldo -= cuotaMantenimiento }

    override fun intereses(){ this.saldo += this.saldo * this.tipoDeInteres }


}