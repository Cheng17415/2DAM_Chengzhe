class CCuentaAhorro(nombre: String, cuenta: String,
                    saldo: Double, tipoDeInteres: Double,
                    private var cuotaMantenimiento: Double): CCuenta(nombre, cuenta, saldo, tipoDeInteres) {

    fun asignarCuotaManten(cuota: Double){
        this.cuotaMantenimiento = if (cuota < 0.0) this.cuotaMantenimiento else cuota
    }

    fun obtenerCuotaManten(): Double{ return this.cuotaMantenimiento }

    override fun comisiones() {
        this.saldo -= cuotaMantenimiento
    }

    override fun intereses() {
        TODO("Not yet implemented")
        /*
        * val interesMensual = saldo * (obtenerTipoDeInteres() / 100) / 12
        saldo += interesMensual*/
    }

}

fun main(){
    var cuenta = CCuentaAhorro("Cheng", "45454", 1500.0, 0.0, 20.5)
    cuenta.ingreso(500.0)
    println(cuenta.estado())
}