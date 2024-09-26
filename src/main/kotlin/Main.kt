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
            val opcion = readLine()!!.toInt()
            when (opcion) {
                1 -> {
                    verProductos(carrito)
                    opcionValida = true
                }
                2 -> {
                    carrito.mostrarCarrito(nombreTxt)
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
        } catch (e: NumberFormatException) {
            println("Error: Debe ingresar un número. Intente de nuevo.")
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
    println("Procesando compra...")
}

fun salir() {
    println("Gracias por visitar SW Solutions. ¡Hasta luego!")
}
