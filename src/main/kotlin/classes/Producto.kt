
package classes

import functions.limpiarPantalla
import java.io.File

data class Producto(val nombre: String, val precio: Double, var cantidad: Int) {
    companion object {
        fun leerProductosDesdeArchivo(rutaArchivo: String): MutableList<Producto> {
            val productos = mutableListOf<Producto>()
            val archivo = File(rutaArchivo)

            if (archivo.exists()) {
                archivo.forEachLine { linea ->
                    val partes = linea.split(",")
                    if (partes.size == 3) {
                        val nombre = partes[0]
                        val precio = partes[1].toDoubleOrNull() ?: 0.0
                        val cantidad = partes[2].toIntOrNull() ?: 0
                        productos.add(Producto(nombre, precio, cantidad))
                    }
                }
            } else {
                println("El archivo no existe.")
            }
            return productos
        }

        private fun escribirProductosEnArchivo(rutaArchivo: String, productos: List<Producto>) {
            val archivo = File(rutaArchivo)
            archivo.bufferedWriter().use { writer ->
                productos.forEach { producto ->
                    writer.write("${producto.nombre},${producto.precio},${producto.cantidad}")
                    writer.newLine()
                }
            }
        }

        fun seleccionarProducto(productos: MutableList<Producto>, rutaArchivo: String) {
            var continuar = true
            while (continuar) {
                limpiarPantalla()
                println("Productos disponibles:")
                println("0. Volver al menú principal")
                productos.forEachIndexed { index, producto ->
                    println("${index + 1}. ${producto.nombre} - Precio: $${producto.precio} - Cantidad disponible: ${producto.cantidad}")
                }
                println("Seleccione un producto (escriba el número):")
                val opcion = readLine()!!.toInt()

                when {
                    opcion == 0 -> {
                        // Volver al menú principal
                        continuar = false
                    }
                    opcion in 1..productos.size -> {
                        val productoSeleccionado = productos[opcion - 1]
                        procesoDeCompra(productoSeleccionado, productos, rutaArchivo)
                    }
                    else -> {
                        println("Opción no válida, intente nuevamente.")
                    }
                }
            }
        }

        private fun procesoDeCompra(producto: Producto, productos: MutableList<Producto>, rutaArchivo: String) {
            limpiarPantalla()
            println("Ha seleccionado el producto: ${producto.nombre}")
            println("Precio por unidad: $${producto.precio}")
            println("Unidades disponibles: ${producto.cantidad}")

            var cantidadValida = false
            while (!cantidadValida) {
                println("¿Cuántas unidades desea comprar? (0 para volver)")
                val unidades = readLine()!!.toInt()

                when {
                    unidades == 0 -> {
                        // Si el usuario ingresa 0, volvemos a la lista de productos
                        return
                    }
                    unidades > 0 && unidades <= producto.cantidad -> {
                        // Si la cantidad es válida, se continúa a la confirmación
                        Producto.confirmarCompra(producto, unidades, rutaArchivo, productos)
                        cantidadValida = true
                    }
                    else -> {
                        // Si la cantidad es inválida, mostramos un mensaje y repetimos el ciclo
                        println("Entrada no válida o cantidad no disponible. Intente de nuevo.")
                    }
                }
            }
        }

        private fun confirmarCompra(producto: Producto, cantidad: Int, rutaArchivo: String, productos: MutableList<Producto>) {
            limpiarPantalla()
            println("Confirmación de compra")
            println("Producto: ${producto.nombre}")
            println("Cantidad: $cantidad")
            println("Subtotal: $${producto.precio * cantidad}")
            println("¿Desea confirmar la compra? (S/n)")

            when (readLine()!!.toUpperCase()) {
                "S" -> {
                    limpiarPantalla()
                    println("Compra confirmada.")
                    // Reducir la cantidad disponible del producto
                    producto.cantidad -= cantidad
                    // Actualizar el archivo con la nueva cantidad
                    escribirProductosEnArchivo(rutaArchivo, productos)
                }
                else -> procesoDeCompra(producto, productos, rutaArchivo)
            }
        }
    }
}
