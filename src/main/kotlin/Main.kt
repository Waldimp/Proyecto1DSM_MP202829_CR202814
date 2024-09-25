import classes.Producto
import classes.CarritoCompras
import functions.limpiarPantalla

val nombreTxt: String = "productos.txt"

fun main() {
    // Crear una instancia del carrito de compras
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
    println("Por favor, seleccione una opción:")

    val opcion = readLine()!!.toInt()

    when(opcion) {
        1 -> verProductos(carrito)
        2 -> mostrarCarrito(carrito)
        3 -> finalizarCompra(carrito)
        4 -> salir()
        else -> {
            println("Opción no válida, intenta de nuevo.")
            mostrarMenu(carrito)
        }
    }
}

fun verProductos(carrito: CarritoCompras) {
    limpiarPantalla()
    val productos = Producto.leerProductosDesdeArchivo(nombreTxt)
    Producto.seleccionarProducto(productos, nombreTxt, carrito)
    mostrarMenu(carrito)
}

fun mostrarCarrito(carrito: CarritoCompras) {
    limpiarPantalla()
    if (carrito.estaVacio()) {
        println("El carrito está vacío.")
        println("Presione Enter para volver al menú principal.")
        readLine()
        mostrarMenu(carrito)
    } else {
        var continuar = true
        while (continuar) {
            limpiarPantalla()
            carrito.mostrarCarrito()
            println("\nTotal Final: $${carrito.calcularTotal()}")
            println("0. Volver al menú principal")
            println("Ingrese el número del producto que desea eliminar o 0 para regresar:")
            val opcion = readLine()!!.toInt()

            when {
                opcion == 0 -> {
                    // Volver al menú principal
                    continuar = false
                    mostrarMenu(carrito) // Corregido: llamar al menú principal
                }
                opcion in 1..carrito.obtenerCantidadProductos() -> {
                    val productoSeleccionado = carrito.obtenerProductoPorIndice(opcion - 1)
                    confirmarEliminacionProducto(carrito, productoSeleccionado)
                }
                else -> {
                    println("Opción no válida, intente nuevamente.")
                }
            }
        }
    }
}

fun confirmarEliminacionProducto(carrito: CarritoCompras, producto: Producto) {
    limpiarPantalla()
    println("¿Está seguro de que desea eliminar ${producto.nombre} del carrito? (S/n)")
    val confirmacion = readLine()!!.toUpperCase()

    if (confirmacion == "S") {
        carrito.eliminarProducto(producto.nombre)
    } else {
        println("Operación cancelada.")
    }
    println("Presione Enter para continuar.")
    readLine()
}

fun finalizarCompra(carrito: CarritoCompras) {
    // Aquí luego se realizará la compra
    println("Procesando compra...")
}

fun salir() {
    println("Gracias por visitar TechStore. ¡Hasta luego!")
}
