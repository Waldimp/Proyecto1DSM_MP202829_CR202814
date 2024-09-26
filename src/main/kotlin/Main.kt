import classes.Producto
import classes.CarritoCompras
import functions.limpiarPantalla

val nombreTxt: String = "productos.txt"

fun main() {
    val carrito = CarritoCompras()
    mostrarMenu(carrito)
}

fun mostrarMenu(carrito: CarritoCompras) {
    limpiarPantalla()
    println("******************************************")
    println("**       Bienvenido a swSolutions        **")
    println("******************************************")
    println("1. Ver productos")
    println("2. Ver carrito de compras")
    println("3. Finalizar compra")
    println("4. Salir")
    println("******************************************")

    var opcionValida = false
    while (!opcionValida) {
        try {
            println("Por favor, seleccione una opción:")
            val input = readLine()!!

            // Verificar si la entrada está vacía
            if (input.isBlank()) {
                println("Debe ingresar un número.")
            } else {
                val opcion = input.toInt()
                when (opcion) {
                    1 -> {
                        verProductos(carrito)
                        opcionValida = true
                    }
                    2 -> {
                        carrito.mostrarCarrito(nombreTxt)  // Aquí regresará correctamente si seleccionas "0"
                        mostrarMenu(carrito)  // Volver al menú después de mostrar el carrito
                        opcionValida = true
                    }
                    3 -> {
                        finalizarCompra(carrito)
                        opcionValida = true
                    }
                    4 -> {
                        salir()
                        opcionValida = true
                    }
                    else -> {
                        println("Opción no válida, intenta de nuevo.")
                    }
                }
            }
        } catch (e: NumberFormatException) {
            println("Error: Debe ingresar un número válido.")
        }
    }
}

fun verProductos(carrito: CarritoCompras) {
    limpiarPantalla()
    val productos = Producto.leerProductosDesdeArchivo(nombreTxt)
    Producto.seleccionarProducto(productos, nombreTxt, carrito)
    mostrarMenu(carrito)
}

fun finalizarCompra(carrito: CarritoCompras) {
    limpiarPantalla()
    println("¿Está seguro de que desea finalizar la compra? (S/n)")
    val confirmacion = readLine()!!.toUpperCase()

    if (confirmacion == "S") {
        carrito.generarFactura()  // Generar y mostrar la factura
        carrito.vaciarCarrito()  // Vaciar el carrito después de la compra
        println("Gracias por su compra.")
    } else {
        println("Compra cancelada. Regresando al menú principal.")
    }
    println("Presione Enter para continuar.")
    readLine()
    mostrarMenu(carrito)
}

fun salir() {
    println("Gracias por visitar SW Solutions. ¡Hasta luego!")
}
