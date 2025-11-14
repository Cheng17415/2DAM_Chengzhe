#EJERCICIO BANCO

##PRELIMINARES
- Eclipse IDE
- MySQL server y workbench.

##REQUISITOS
Pedir un **SCHEMA**, crearla si no existe, junto con la creacion de la tabla CAJERO.
La tabla CAJERO debe contener todos los valores de 500eu a 0.01eu con una cantidad inicial de 10 para cada moneda. 

	1. Listar toda la tabla CAJERO del banco
	2. Realizar compra
      - Introducir la cantidad de euros que se va a pagar: 92.25
      - Introducir la cantidad y el billete a pagar con el siguiente formato: 1-50#3-20 (1 de 50eu, 3 de 20eu)
	    Si los euros introducidos es mayor de lo que se va a pagar, devolver cambio. Ademas, sumar la cantidad al CAJERO.
      Si es menor, no ejecutar la suma de cantidad al cajero. 
	    Mensaje a mostrar: 
        A devolver 17.75 euros
		    1-10
        1-5
        1-2
        1-0.5
        1-0.2
        1-0.05
	3. Introducir dinero al cajero 1-100#5-20#10-0.5
	4. Transferir **TODO** el dinero de un banco a otro.
	5. Cambiar de banco (SCHEMA)
	6. Salir
