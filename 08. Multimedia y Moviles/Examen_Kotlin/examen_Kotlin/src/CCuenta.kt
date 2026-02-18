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
    abstract fun intereses()
}

/*class CCuentaCorrienteConIn(
    nombre: String,
    cuenta: String,
    saldo: Double,
    tipoDeInteres: Double,
    transExentas: Int,
    importePorTrans: Double
) : CCuentaCorriente(nombre, cuenta, saldo, tipoDeInteres, transExentas, importePorTrans) {

    override fun intereses() {
        if (estado() >= 3000) {
            val interesMensual = estado() * (obtenerTipoDeInteres() / 100) / 12
            saldo += interesMensual
        }
    }
}
*/

/*
* class CBanco {

    private val clientes: MutableList<CCuenta> = mutableListOf()

    fun obtenerClientes(): List<CCuenta> = clientes

    fun insertarCliente(cuenta: CCuenta) {
        clientes.add(cuenta)
    }

    fun eliminarCliente(numCuenta: String) {
        clientes.removeIf { it.obtenerCuenta() == numCuenta }
    }

    fun longitud(): Int = clientes.size

    fun buscar(valor: String): List<CCuenta> {
        return clientes.filter {
            it.obtenerNombre().contains(valor, true) ||
            it.obtenerCuenta().contains(valor, true)
        }
    }
}
*/


/*fun main() {

    val banco = CBanco()
    var opcion: Int

    do {
        println("\n1. Saldo")
        println("2. Buscar")
        println("3. Ingreso")
        println("4. Reintegro")
        println("5. Añadir")
        println("6. Eliminar")
        println("7. Mantenimiento")
        println("8. Salir")

        opcion = readLine()?.toIntOrNull() ?: 8

        when (opcion) {

            1 -> {
                print("Cuenta: ")
                val num = readLine()!!
                val cuenta = banco.buscar(num).firstOrNull()
                println("Saldo: ${cuenta?.estado()}")
            }

            3 -> {
                print("Cuenta: ")
                val num = readLine()!!
                print("Cantidad: ")
                val cantidad = readLine()!!.toDouble()
                banco.buscar(num).firstOrNull()?.ingreso(cantidad)
            }

            4 -> {
                print("Cuenta: ")
                val num = readLine()!!
                print("Cantidad: ")
                val cantidad = readLine()!!.toDouble()
                banco.buscar(num).firstOrNull()?.reintegro(cantidad)
            }

            5 -> {
                println("1. Ahorro  2. Corriente  3. CorrienteConIn")
                when (readLine()?.toInt()) {
                    1 -> banco.insertarCliente(
                        CCuentaAhorro("ClienteA", "111", 1000.0, 1.5, 5.0)
                    )
                    2 -> banco.insertarCliente(
                        CCuentaCorriente("ClienteB", "222", 2000.0, 2.0, 3, 1.0)
                    )
                    3 -> banco.insertarCliente(
                        CCuentaCorrienteConIn("ClienteC", "333", 4000.0, 2.5, 3, 1.0)
                    )
                }
            }

            6 -> {
                print("Cuenta a eliminar: ")
                val num = readLine()!!
                banco.eliminarCliente(num)
            }

            7 -> {
                banco.obtenerClientes().forEach {
                    it.comisiones()
                    it.intereses()
                }
                println("Mantenimiento aplicado.")
            }
        }

    } while (opcion != 8)
}
*/