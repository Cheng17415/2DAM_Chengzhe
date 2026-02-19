fun main() {
    var banco: CBanco = CBanco()
    menu(banco)
}

fun menu(banco: CBanco){
    val opciones: List<String> = listOf("Saldo", "Buscar siguiente", "Ingreso", "Reintegro", "Anadir", "Eliminar", "Mantenimiento", "Salir")

    var opcion: Int = -1
    do{
        println(String.format("%10s","MENU"))
        for (i in 0 until opciones.size){
            println("${i+1}. ${opciones[i]}")
        }
        print("> ")
        try{
            opcion = readln().toInt()
        } catch (e: NumberFormatException){ println("ERROR - Debe introducir un numero entero") }

        when (opcion){
            1 -> {
                println("Introduzca el nombre o numero de la cuenta a buscar")
                var valor: String = readln()
                var cuenta = banco.buscar(valor)
                if(cuenta is CCuenta){
                    println("Saldo: " + cuenta.estado())
                }
                else{
                    println("No se ha encontrado esa cuenta")
                }

            }
            2 -> { println("No implementado") }
            3 -> {
                println("Introduzca el nombre o numero de la cuenta")
                var valor: String = readln()
                var cuenta = banco.buscar(valor)
                println("Introduzca la cantidad a ingresar")
                var cantidad: Double = readln().toDouble()
                if (cuenta is CCuenta) {
                    cuenta.ingreso(cantidad)
                    println("Ingreso exitoso. Saldo nuevo: ${cuenta.estado()}")

                } else{
                    println("No se ha encontrado esa cuenta")
                }
            }
            4 -> {
                println("Introduzca el nombre o numero de la cuenta")
                var valor: String = readln()
                var cuenta = banco.buscar(valor)
                println("Introduzca la cantidad a reintegrar")
                var cantidad: Double = readln().toDouble()
                if(cuenta is CCuenta){
                    cuenta.reintegro(cantidad)
                    println("Reintegro exitoso. Saldo nuevo: ${cuenta.estado()}")
                }else{
                    println("No se ha encontrado esa cuenta")
                }
            }

            5 -> anadir(banco)
            6 -> {
                print("Cuenta a eliminar: ")
                val cuenta:String = readln()
                if(banco.eliminarCliente(cuenta)){
                    println("Cuenta eliminada con exito")
                } else{
                    println("No se ha encontrado la cuenta")
                }
            }

            7 -> {
                var clientes = banco.obtenerClientes()
                for (cliente in clientes){
                    cliente.intereses()
                    cliente.comisiones()
                }
            }
            8 -> println("Saliendo del sistema")
            else -> println("Opcion no valida")
        }
        if(opcion != opciones.size){
            println("Pulse enter para continuar...")
            readln()
        }
    } while(opcion != opciones.size)
}


fun anadir(banco: CBanco){
    println("1. Ahorro  2. Corriente  3. Corriente con interes")
    var opcion: Int
    try{
        opcion = readln().toInt()
        when (opcion){
            1 -> banco.insertarClientes(crearCuentaAhorro())
            2 -> banco.insertarClientes(crearCuentaCorriente())
            3 -> banco.insertarClientes(crearCuentaCorrienteConIn())
            else -> println("Opcion no valida")
        }
    } catch (e: NumberFormatException){ println("ERROR - Debe introducir un numero entero") }
}

fun crearCuenta(): List<Any>{
    println("Nombre del propietario: ")
    val nombre: String = readln()
    println("Numero de la cuenta: ")
    val cuenta: String = readln()
    var saldo: Double
    try{
        println("Saldo de la cuenta: ")
        saldo = readln().toDouble()
    } catch (e: NumberFormatException){
        println("Error al introducir el saldo. Estableciendo a 0")
        saldo = 0.0
    }
    var tipoDeInteres: Double
    try{
        println("Tipo de interes: ")
        tipoDeInteres= readln().toDouble()
    }catch (e: NumberFormatException){
        println("Error al introducir el tipo de interes. Estableciendo a 0")
        tipoDeInteres = 0.0
    }

    return listOf(nombre, cuenta, saldo, tipoDeInteres)
}

fun crearCuentaAhorro(): CCuentaAhorro{
    val atributos: List<Any> = crearCuenta()
    var cuotaMantenimiento: Double
    try{
        println("Cuota de mantenimiento: ")
        cuotaMantenimiento= readln().toDouble()
    }catch (e: NumberFormatException){
        println("Error al introducir la cuota de mantenimiento. Estableciendo a 0")
        cuotaMantenimiento = 0.0
    }
    return CCuentaAhorro(atributos[0].toString(), atributos[1].toString(),
        atributos[2] as Double, atributos[3] as Double, cuotaMantenimiento)
}

fun crearCuentaCorriente(): CCuentaCorriente{
    val atributos: List<Any> = crearCuenta()
    var importe: Double
    try{
        println("Importe por transacciones: ")
        importe= readln().toDouble()
    }catch (e: NumberFormatException){
        println("Error al introducir el importe por trasaccion. Estableciendo a 50 ")
        importe = 50.0
    }
    var exentas: Int
    try{
        println("Transacciones exentas: ")
        exentas= readln().toInt()
    }catch (e: NumberFormatException){
        println("Error al introducir el transacciones exentas. Estableciendo a 0 ")
        exentas = 0
    }
    return CCuentaCorriente(atributos[0].toString(), atributos[1].toString(),
        atributos[2] as Double, atributos[3] as Double,0,
        importe, exentas)
}

fun crearCuentaCorrienteConIn(): CCuentaCorrienteConIn{
    val atributos: List<Any> = crearCuenta()
    var importe: Double
    try{
        println("Importe por transacciones: ")
        importe= readln().toDouble()
    }catch (e: NumberFormatException){
        println("Error al introducir el importe por trasaccion. Estableciendo a 50 ")
        importe = 50.0
    }
    var exentas: Int
    try{
        println("Transacciones exentas: ")
        exentas= readln().toInt()
    }catch (e: NumberFormatException){
        println("Error al introducir el transacciones exentas. Estableciendo a 0 ")
        exentas = 0
    }
    return CCuentaCorrienteConIn(atributos[0].toString(), atributos[1].toString(),
        atributos[2] as Double, atributos[3] as Double,0,
        importe, exentas)
}