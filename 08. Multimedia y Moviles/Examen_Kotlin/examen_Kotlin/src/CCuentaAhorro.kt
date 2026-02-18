class CCuentaAhorro(nombre: String, cuenta: String,
                    saldo: Double, tipoDeInteres: Double,
                    private var cuotaMantenimiento: Double): CCuenta(nombre, cuenta, saldo, tipoDeInteres) {

    fun asignarCuotaManten(cuota: Double){
        this.cuotaMantenimiento = if (cuota < 0.0) this.cuotaMantenimiento else cuota
    }

    fun obtenerCuotaManten(): Double{ return this.cuotaMantenimiento }

    override fun comisiones() {
        var saldo = this.saldo - this.cuotaMantenimiento
        this.saldo = if (saldo < 0.0) 0.0 else saldo
    }

    override fun intereses(): Double {
        return this.saldo * this.tipoDeInteres
    }

}

fun main(){
    var cuenta = CCuentaAhorro("Cheng", "45454", 1500.0, 0.0, 20.5)
    cuenta.ingreso(500.0)
    println(cuenta.estado())
}