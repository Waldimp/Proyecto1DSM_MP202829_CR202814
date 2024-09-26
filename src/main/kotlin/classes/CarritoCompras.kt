package classes
import functions.limpiarPantalla
import classes.Producto
class CarritoCompras {

    // Lista de productos en el carrito
    private val productosEnCarrito = mutableListOf<Producto>()

    // Función para agregar productos al carrito
    fun agregarProducto(producto: Producto, cantidad: Int) {
        val productoEnCarrito = productosEnCarrito.find { it.nombre == producto.nombre }
        if (productoEnCarrito != null) {
            productoEnCarrito.cantidad += cantidad
        } else {
            productosEnCarrito.add(Producto(producto.nombre, producto.precio, cantidad))
        }
        println("${cantidad} unidad(es) de ${producto.nombre} agregado(s) al carrito.")
    }

    // Función para eliminar productos del carrito
    fun eliminarProducto(nombreProducto: String, rutaArchivo: String) {
        val productoEnCarrito = productosEnCarrito.find { it.nombre == nombreProducto }
        if (productoEnCarrito != null) {
            val productos = Producto.leerProductosDesdeArchivo(rutaArchivo)
            val productoOriginal = productos.find { it.nombre == nombreProducto }
            if (productoOriginal != null) {
                productoOriginal.cantidad += productoEnCarrito.cantidad
                Producto.escribirProductosEnArchivo(rutaArchivo, productos)
            }
            productosEnCarrito.remove(productoEnCarrito)
            println("Producto ${nombreProducto} eliminado del carrito y cantidad devuelta al inventario.")
        } else {
            println("El producto ${nombreProducto} no se encuentra en el carrito.")
        }
    }

    // Función para mostrar el contenido del carrito con la opción de eliminar
    fun mostrarCarrito(rutaArchivo: String) {
        limpiarPantalla()
        if (estaVacio()) {
            println("El carrito está vacío.")
            println("Presione Enter para volver al menú principal.")
            readLine()
        } else {
            var continuar = true
            while (continuar) {
                limpiarPantalla()
                mostrarContenidoCarrito()
                println("\nTotal Final: $${calcularTotal()}")
                println("0. Volver al menú principal")
                var opcionValida = false
                while (!opcionValida) {
                    try {
                        println("Ingrese el número del producto que desea eliminar o 0 para regresar:")
                        val opcion = readLine()!!.toInt()

                        when {
                            opcion == 0 -> continuar = false
                            opcion in 1..obtenerCantidadProductos() -> {
                                val productoSeleccionado = obtenerProductoPorIndice(opcion - 1)
                                confirmarEliminacionProducto(productoSeleccionado, rutaArchivo)
                                opcionValida = true
                            }
                            else -> {
                                println("Opción no válida, intente nuevamente.")
                            }
                        }
                    } catch (e: NumberFormatException) {
                        println("Error: Ingrese un número válido.")
                    }
                }
            }
        }
    }

    fun confirmarEliminacionProducto(producto: Producto, rutaArchivo: String) {
        limpiarPantalla()
        var confirmacionValida = false
        while (!confirmacionValida) {
            try {
                println("¿Está seguro de que desea eliminar ${producto.nombre} del carrito? (S/n)")
                val confirmacion = readLine()!!.toUpperCase()

                when (confirmacion) {
                    "S" -> {
                        eliminarProducto(producto.nombre, rutaArchivo)
                        confirmacionValida = true
                    }
                    "N" -> {
                        println("Operación cancelada.")
                        confirmacionValida = true
                    }
                    else -> {
                        println("Entrada no válida. Por favor ingrese S o N.")
                    }
                }
            } catch (e: Exception) {
                println("Error: Ocurrió un problema, por favor intente nuevamente.")
            }
        }
    }

    // Función para mostrar el contenido del carrito
    private fun mostrarContenidoCarrito() {
        if (productosEnCarrito.isEmpty()) {
            println("El carrito está vacío.")
        } else {
            println("Carrito de Compras:")
            productosEnCarrito.forEachIndexed { index, producto ->
                println("${index + 1}. ${producto.nombre} - Cantidad: ${producto.cantidad} - Subtotal: $${producto.precio * producto.cantidad}")
            }
        }
    }

    // Función para calcular el total de todos los productos en el carrito
    fun calcularTotal(): Double {
        return productosEnCarrito.sumOf { it.precio * it.cantidad }
    }

    // Función para verificar si el carrito está vacío
    fun estaVacio(): Boolean {
        return productosEnCarrito.isEmpty()
    }

    // Función para obtener el número de productos en el carrito
    fun obtenerCantidadProductos(): Int {
        return productosEnCarrito.size
    }

    // Función para obtener un producto por su índice en el carrito
    fun obtenerProductoPorIndice(indice: Int): Producto {
        return productosEnCarrito[indice]
    }
}
