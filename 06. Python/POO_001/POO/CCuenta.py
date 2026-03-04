from abc import ABC, abstractmethod
from datetime import datetime

class CCuenta(ABC):
    """
    Clase que representa una cuenta bancaria.
    Los tipos de datos double en otros lenguajes se mapean a float en Python.
    """

    def __init__(self, nombre: str, cuenta: str, saldo: float, tipoDeInteres: float):
        """Constructor de la clase. Inicia los datos de la cuenta."""
        self.__nombre = nombre
        self.__cuenta = cuenta
        self.__saldo = saldo
        self.__tipoDeInteres = tipoDeInteres

    # --- Métodos Asignar (Setters) y Obtener (Getters) ---
    def asignarNombre(self, nombre: str):
        """Permite asignar el dato nombre."""
        self.__nombre = nombre

    def obtenerNombre(self) -> str:
        """Retorna el dato nombre."""
        return self.__nombre

    def asignarCuenta(self, cuenta: str):
        """Permite asignar el dato cuenta."""
        self.__cuenta = cuenta

    def obtenerCuenta(self) -> str:
        """Retorna el dato cuenta."""
        return self.__cuenta

    def asignarTipoDeInteres(self, tipoDeInteres: float):
        """Permite asignar el dato tipoDeInteres."""
        self.__tipoDeInteres = tipoDeInteres

    def obtenerTipoDeInteres(self) -> float:
        """Permite obtener el dato tipoDeInteres."""
        return self.__tipoDeInteres

    # --- Métodos de Operación ---
    def ingreso(self, cantidad: float):
        """Añade la cantidad especificada al saldo actual."""
        if cantidad > 0:
            self.__saldo += cantidad
        else:
            print("Error: La cantidad de ingreso debe ser positiva.")

    def reintegro(self, cantidad: float):
        """Resta la cantidad especificada al saldo actual."""
        if cantidad > 0:
            if self.__saldo >= cantidad:
                self.__saldo -= cantidad
            else:
                print("Error: Saldo insuficiente.")
        else:
            print("Error: La cantidad de reintegro debe ser positiva.")

    def estado(self) -> float:
        """Retorna el saldo de la cuenta."""
        return self.__saldo

    # --- Métodos Abstractos ---
    @abstractmethod
    def comisiones(self):
        """
        Método abstracto. Se ejecutará el día 1 de cada mes
        para cobrar el mantenimiento.
        """
        pass

    @abstractmethod
    def intereses(self):
        """Método abstracto. Calcula los intereses producidos."""
        pass

class CCuentaAhorro(CCuenta):
    def __init__(self, nombre, cuenta, saldo, tipoDeInteres, cuotaMantenimiento):
        # Inicia los atributos de la clase base
        super().__init__(nombre, cuenta, saldo, tipoDeInteres)
        # Atributo específico de la subclase
        self.__cuotaMantenimiento = float(cuotaMantenimiento)

    def asignarCuotaManten(self, cuota):
        self.__cuotaMantenimiento = float(cuota)

    def obtenerCuotaManten(self):
        return self.__cuotaMantenimiento

    def comisiones(self):
        # Se resta la cuota de mantenimiento al saldo actual
        # Usamos el método reintegro o accedemos indirectamente vía estado()
        hoy = datetime.now()

        if hoy.day == 1:
            saldo_actual = self.estado()
            self.reintegro(self.__cuotaMantenimiento)
            print(f"comisión realizado el día {hoy.strftime('%d/%m/%Y')}: "
                  f"Comisión mensual aplicada: {self.__cuotaMantenimiento}. Nuevo saldo: {self.estado()}")
        else:
            # Si no es día 1, no se aplica el cargo
            print(f"Hoy es día {hoy.day}. Las comisiones solo se cobran el día 1.")

    def intereses(self):
        hoy = datetime.now()

        if hoy.day == 1:
            # Cálculo: (Saldo actual * Porcentaje de interés) / 12 meses / 100
            # Usamos obtenerTipoDeInteres() de la clase base
            interes_mensual = (self.estado() * self.obtenerTipoDeInteres()) / 12 / 100

            # Se añade el interés al saldo mediante el método ingreso
            self.ingreso(interes_mensual)

            print(f"Intereses abonados el {hoy.strftime('%d/%m/%Y')}: +{interes_mensual:.2f}")
            return interes_mensual
        else:
            print(f"Hoy es día {hoy.day}. Los intereses se abonan únicamente el día 1.")
            return 0.0


class CCuentaCorriente(CCuenta):
    def __init__(self, nombre, cuenta, saldo, tipoDeInteres, importePorTrans, transExentas):
        # Inicializa los atributos de la clase base
        super().__init__(nombre, cuenta, saldo, tipoDeInteres)
        # Atributos específicos de CCuentaCorriente
        self.__transacciones = 0
        self.__importePorTrans = float(importePorTrans)
        self.__transExentas = int(transExentas)
    # Métodos específicos
    def decrementarTransacciones(self):
        if self.__transacciones > 0:
            self.__transacciones -= 1
    def asignarImportePorTrans(self, importe): self.__importePorTrans = float(importe)
    def obtenerImportePorTrans(self): return self.__importePorTrans
    def asignarTransExentas(self, exentas): self.__transExentas = int(exentas)
    def obtenerTransExentas(self): return self.__transExentas

    # Sobrescritura de métodos para contabilizar transacciones
    def ingreso(self, cantidad):
        super().ingreso(cantidad)
        self.__transacciones += 1

    def reintegro(self, cantidad):
        # Verificamos si hay saldo antes de contar la transacción
        saldo_previo = self.estado()
        super().reintegro(cantidad)
        if self.estado() < saldo_previo:  self.__transacciones += 1

    def comisiones(self):
        """
        Se ejecuta el día 1. Cobra transacciones no exentas
        y reinicia el contador a cero.
        """
        hoy = datetime.now()
        if hoy.day == 1:
            # Calcular transacciones que superan el límite gratuito
            n_cobrar = max(0, self._CCuentaCorriente__transacciones - self._CCuentaCorriente__transExentas)
            importe_total = n_cobrar * self.obtenerImportePorTrans()

            # Realizar el cobro si el importe es mayor a 0
            if importe_total > 0:
                # Usamos el reintegro de la clase base para no sumar esta operación como transacción
                super().reintegro(importe_total)
                print(
                    f"Comisiones cobradas: {importe_total}. Transacciones procesadas: {self._CCuentaCorriente__transacciones}")

            # Reiniciar contador de transacciones
            self._CCuentaCorriente__transacciones = 0
        else:
            print(f"Hoy es día {hoy.day}. Las comisiones se procesan el día 1.")

    def intereses(self):
        """
        Se ejecuta el día 1.
        Interés: hasta 3000€ al 0.5%, el resto al tipo de interés establecido.
        """
        hoy = datetime.now()
        if hoy.day == 1:
            saldo_actual = self.estado()
            interes_anual = 0.0

            if saldo_actual <= 3000:
                # Todo el saldo al 0.5%
                interes_anual = saldo_actual * 0.5 / 100
            else:
                # Primeros 3000 al 0.5%, el excedente al tipoDeInteres de la cuenta
                interes_tramo1 = 3000 * 0.5 / 100
                interes_tramo2 = (saldo_actual - 3000) * self.obtenerTipoDeInteres() / 100
                interes_anual = interes_tramo1 + interes_tramo2

            # Convertir a mensual e ingresar
            interes_mensual = interes_anual / 12
            super().ingreso(interes_mensual)
            print(f"Intereses mensuales abonados: {interes_mensual:.2f}")
        else:
            print(f"Hoy es día {hoy.day}. Los intereses se abonan el día 1.")


class CCuentaCorrienteConIn(CCuentaCorriente):
    def __init__(self, nombre, cuenta, saldo, tipoDeInteres, importePorTrans, transExentas):
        # Inicia los atributos heredados de CCuentaCorriente
        super().__init__(nombre, cuenta, saldo, tipoDeInteres, importePorTrans, transExentas)

    def intereses(self):
        """
        Calcula e ingresa intereses mensuales solo si el saldo supera los 3000€.
        Se ejecuta el día 1 de cada mes.
        """
        hoy = datetime.now()
        if hoy.day == 1:
            saldo_actual = self.estado()

            # Condición de saldo mínimo para generar intereses
            if saldo_actual >= 3000:
                # Se aplica el tipoDeInteres establecido sobre el total del saldo
                interes_mensual = (saldo_actual * self.obtenerTipoDeInteres()) / 12 / 100
                # Usamos el método de la clase base para no contar esto como transacción
                super(CCuentaCorriente, self).ingreso(interes_mensual)
                print(f"Intereses abonados (Cuenta Con Inversión): +{interes_mensual:.2f}")
            else:
                print("Saldo inferior a 3000€. No se generan intereses este mes.")
        else:
            print(f"Hoy es día {hoy.day}. Los intereses se calculan el día 1.")


class CBanco:
    def __init__(self):
        # Inicia la lista de clientes vacía
        self.__clientes = []

    def insertarCliente(self, cliente):
        """Añade un objeto de alguna de las subclases de CCuenta."""
        if isinstance(cliente, CCuenta):
            self.__clientes.append(cliente)
        else:
            print("Error: El objeto debe ser una instancia de CCuenta.")

    def obtenerClientes(self):
        """Retorna la lista completa de clientes."""
        return self.__clientes

    def eliminarCliente(self, numero_cuenta):
        """Elimina el objeto que coincide con el número de cuenta."""
        for cliente in self.__clientes:
            if cliente.obtenerCuenta() == numero_cuenta:
                self.__clientes.remove(cliente)
                print(f"Cuenta {numero_cuenta} eliminada con éxito.")
                return
        print("Cuenta no encontrada.")

    def longitud(self):
        """Devuelve el número de clientes."""
        return len(self.__clientes)

    def buscar(self, criterio):
        """
        Busca clientes cuyo nombre o cuenta coincidan (total o parcialmente)
        con el valor pasado como argumento.
        """
        resultados = []
        criterio = str(criterio).lower()

        for cliente in self.__clientes:
            nombre = cliente.obtenerNombre().lower()
            cuenta = cliente.obtenerCuenta().lower()

            if criterio in nombre or criterio in cuenta:
                resultados.append(cliente)

        return resultados


import sys


# --- CLASE PRINCIPAL DE LA APLICACIÓN ---
class AplicacionBancaria:
    def __init__(self):
        self.banco = CBanco()
        self.ultimo_indice_busqueda = -1

    def mostrar_menu(self):
        menu_text = f"""
                {"=" * 30}
                        CAJERO AUTOMÁTICO
                {"=" * 30}
                1. Saldo
                2. Buscar siguiente
                3. Ingreso
                4. Reintegro
                5. Añadir
                6. Eliminar
                7. Mantenimiento (Día 1)
                8. Salir
                {"-" * 30}"""

        print(menu_text)

        try:
            opcion = input("➤ Seleccione una opción: ").strip()
            # Validamos que sea un número del 1 al 8
            if not opcion.isdigit() or not (1 <= int(opcion) <= 8):
                raise ValueError("Opción fuera de rango.")
            return opcion
        except ValueError:
            print("⚠️ Error: Por favor, introduce un número válido (1-8).")
            return None  # Retornamos None para que el match caiga en el caso por defecto

    def ejecutar(self):
        while True:
            opcion = self.mostrar_menu()

            match opcion:
                case "1":
                    self.ver_saldo()
                case "2":
                    self.buscar_siguiente()
                case "3":
                    self.realizar_ingreso()
                case "4":
                    self.realizar_reintegro()
                case "5":
                    self.añadir_cuenta()
                case "6":
                    self.eliminar_cuenta()
                case "7":
                    self.ejecutar_mantenimiento()
                case "8":
                    print("Cerrando sesión. ¡Gracias por usar nuestro sistema!")
                    break
                case _:
                    print("Opción no reconocida. Intente de nuevo.")

    # --- MÉTODOS DE SOPORTE ---

    def ver_saldo(self):
        cta = input("Ingrese número de cuenta: ")
        encontrados = self.banco.buscar(cta)
        if encontrados:
            for c in encontrados:
                print(f"Cliente: {c.obtenerNombre()} | Saldo: {c.estado()}€")
        else:
            print("Cuenta no encontrada.")

    def buscar_siguiente(self):
        criterio = input("Ingrese nombre o cuenta a buscar: ")
        resultados = self.banco.buscar(criterio)
        if resultados:
            # Lógica simple para rotar entre resultados
            self.ultimo_indice_busqueda = (self.ultimo_indice_busqueda + 1) % len(resultados)
            c = resultados[self.ultimo_indice_busqueda]
            print(f"Resultado: {c.obtenerNombre()} [{c.obtenerCuenta()}] - Saldo: {c.estado()}€")
        else:
            print("No se encontraron coincidencias.")

    def realizar_ingreso(self):
        cta = input("Cuenta para ingreso: ")
        res = self.banco.buscar(cta)
        if res:
            monto = float(input("Cantidad a ingresar: "))
            res[0].ingreso(monto)
            print("Ingreso realizado.")
        else:
            print("Cuenta no encontrada.")

    def realizar_reintegro(self):
        cta = input("Cuenta para reintegro: ")
        res = self.banco.buscar(cta)
        if res:
            monto = float(input("Cantidad a retirar: "))
            res[0].reintegro(monto)
        else:
            print("Cuenta no encontrada.")

    def añadir_cuenta(self):
        print("\n--- TIPO DE CUENTA ---")
        print("1. Ahorro")
        print("2. Corriente")
        print("3. Corriente Con Inversión")
        tipo = input("Seleccione el tipo: ")

        # Datos comunes a todas las cuentas
        nom = input("Nombre del titular: ")
        num = input("Número de cuenta: ")
        sal = float(input("Saldo inicial (€): "))
        int_anual = float(input("Tipo de interés anual (%): "))

        match tipo:
            case "1":
                cuota = float(input("Cuota de mantenimiento mensual: "))
                nueva = CCuentaAhorro(nom, num, sal, int_anual, cuota)

            case "2" | "3":
                imp = float(input("Importe por cada transacción: "))
                exe = int(input("Número de transacciones exentas: "))

                # Diferenciamos según la opción elegida
                if tipo == "2":
                    nueva = CCuentaCorriente(nom, num, sal, int_anual, imp, exe)
                else:
                    nueva = CCuentaCorrienteConIn(nom, num, sal, int_anual, imp, exe)

            case _:
                print("❌ Error: Tipo de cuenta no válido. Operación cancelada.")
                return

        # Insertamos el objeto final en el banco
        self.banco.insertarCliente(nueva)
        print(f"✅ Cuenta {type(nueva).__name__} creada correctamente.")

    def eliminar_cuenta(self):
        cta = input("Número de cuenta a eliminar: ")
        self.banco.eliminarCliente(cta)

    def ejecutar_mantenimiento(self):
        print("Ejecutando procesos de fin de mes (Día 1)...")
        for cliente in self.banco.obtenerClientes():
            print(f"\nProcesando cuenta: {cliente.obtenerCuenta()}")
            cliente.comisiones()
            cliente.intereses()


# --- EJECUCIÓN ---
if __name__ == "__main__":
    app = AplicacionBancaria()
    app.ejecutar()