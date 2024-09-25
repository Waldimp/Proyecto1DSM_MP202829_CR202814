package classes

import classes.Producto
class CarritoCompras {

    // Lista de productos en el carrito
    private val productosEnCarrito = mutableListOf<Producto>()

    // Función para agregar productos al carrito
    fun agregarProducto(producto: Producto, cantidad: Int) {
        val productoEnCarrito = productosEnCarrito.find { it.nombre == producto.nombre }
        if (productoEnCarrito != null) {
            // Si el producto ya está en el carrito, aumentar la cantidad
            productoEnCarrito.cantidad += cantidad
        } else {
            // Si no está en el carrito, agregarlo
            productosEnCarrito.add(Producto(producto.nombre, producto.precio, cantidad))
        }
        println("${cantidad} unidad(es) de ${producto.nombre} agregado(s) al carrito.")
    }

    // Función para eliminar productos del carrito
    fun eliminarProducto(nombreProducto: String) {
        val productoEnCarrito = productosEnCarrito.find { it.nombre == nombreProducto }
        if (productoEnCarrito != null) {
            productosEnCarrito.remove(productoEnCarrito)
            println("Producto ${nombreProducto} eliminado del carrito.")
        } else {
            println("El producto ${nombreProducto} no se encuentra en el carrito.")
        }
    }

    // Función para mostrar el contenido del carrito
    fun mostrarCarrito() {
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
