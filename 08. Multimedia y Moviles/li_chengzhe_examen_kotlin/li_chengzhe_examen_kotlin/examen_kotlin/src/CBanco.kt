class CBanco{
    private var clientes : MutableList<CCuenta> = mutableListOf()

    constructor()

    fun obtenerClientes(): MutableList<CCuenta>{ return this.clientes }

    fun insertarClientes(cliente:CCuenta){ clientes.add(cliente) }

    fun eliminarCliente(cuenta: String): Boolean{
        for(cliente in clientes){
            if(cliente.obtenerCuenta().trim().equals(cuenta.trim(),true)){
                clientes.remove(cliente)
                return true
            }
        }
        return false
    }

    fun longitud(): Int{ return clientes.size }

    fun buscar(valor: String): CCuenta? {
        for(cliente in clientes){
            if(cliente.obtenerCuenta().contains(valor,true)||
                cliente.obtenerNombre().contains(valor, true)){
                return cliente
            }
        }
        return null
    }
}