package classes

import java.io.File

data class Producto(val nombre: String, val precio: Double, var cantidad: Int) {
    companion object {
        fun leerProductosDesdeArchivo(rutaArchivo: String): List<Producto> {
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

        fun seleccionarProducto(productos: List<Producto>) {
            var continuar = true
            while (continuar) {
                println("Productos disponibles:")
                println("0. Volver al menú principal")
                productos.forEachIndexed { index, producto ->
                    println("${index + 1}. ${producto.nombre} - Precio: $${producto.precio} - Cantidad disponible: ${producto.cantidad}")
                }
                println("Seleccione un producto (escriba el número):")
                val opcion = readLine()!!.toInt()
                if (opcion == 0) {
                    functions.limpiarPantalla()
                    continuar = false
                } else {
                    val productoSeleccionado = productos.getOrNull(opcion - 1)
                    productoSeleccionado?.let {
                        procesoDeCompra(it)
                    } ?: println("Opción no válida.")
                }
            }
        }

        fun procesoDeCompra(producto: Producto) {
            functions.limpiarPantalla()
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
                        confirmarCompra(producto, unidades)
                        cantidadValida = true
                    }
                    else -> {
                        // Si la cantidad es inválida, mostramos un mensaje y repetimos el ciclo
                        println("Entrada no válida o cantidad no disponible. Intente de nuevo.")
                    }
                }
            }
        }

        fun confirmarCompra(producto: Producto, cantidad: Int) {
            functions.limpiarPantalla()
            println("Confirmación de compra")
            println("Producto: ${producto.nombre}")
            println("Cantidad: $cantidad")
            println("Subtotal: $${producto.precio * cantidad}")
            println("¿Desea confirmar la compra? (S/n)")
            when (readLine()!!.toUpperCase()) {
                "S" -> {
                    functions.limpiarPantalla()
                    // Aquí agregaríamos al carrito, pero por ahora solo simulamos
                    println("Compra confirmada.")
                    // actualizar la cantidad del producto
                    producto.cantidad -= cantidad
                }
                else -> procesoDeCompra(producto)
            }
        }
    }
}
