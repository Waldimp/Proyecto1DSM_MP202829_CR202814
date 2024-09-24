import classes.Producto

fun main() {
    mostrarMenu()
}

fun mostrarMenu() {
    functions.limpiarPantalla()
    println("******************************************")
    println("**     Bienvenido a SW solutions        **")
    println("******************************************")
    println("1. Ver productos")
    println("2. Ver carrito de compras")
    println("3. Salir")
    println("******************************************")
    println("Por favor, seleccione una opción:")

    val opcion = readLine()!!.toInt()

    when(opcion) {
        1 -> verProductos()
        2 -> verCarrito()
        3 -> salir()
        else -> {
            println("Opción no válida, intenta de nuevo.")
            mostrarMenu()
        }
    }
}

fun verProductos() {
    functions.limpiarPantalla()
    val productos = Producto.leerProductosDesdeArchivo("productos.txt")
    Producto.seleccionarProducto(productos)
    mostrarMenu()
}


fun verCarrito() {
    // Aquí luego implementaremos la funcionalidad del carrito
    println("Mostrando carrito de compras...")
}

fun realizarCompra() {
    // Aquí luego se realizará la compra
    println("Procesando compra...")
}

fun salir() {
    println("Gracias por visitar TechStore. ¡Hasta luego!")
}