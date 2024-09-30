## Integrantes ✒️

* **Walter Daniel Mejía Palacios** - **MP202829**
* **Samuel Fernando Calderón Reyes** - **CR202814**

## Simulación de Interacción con el Sistema

A continuación, se muestra una simulación de cómo un usuario interactuaría con el sistema, navegando por el menú, viendo productos, agregando productos al carrito, y finalizando una compra.

******************************************
**       Bienvenido a swSolutions        **
******************************************
1. Ver productos
2. Ver carrito de compras
3. Finalizar compra
4. Salir
******************************************  
Por favor, seleccione una opción:

### 1. Seleccionar opción 1 - Ver productos

Productos disponibles:
0. Volver al menú principal
1. Teclado - Precio: $50.0 - Cantidad disponible: 10
2. Monitor - Precio: $200.0 - Cantidad disponible: 5
3. Mouse - Precio: $30.0 - Cantidad disponible: 20  
   Seleccione un producto (escriba el número):

**Selecciono 1 (Teclado)**:  
Ha seleccionado el producto: Teclado  
Precio por unidad: $50.0  
Unidades disponibles: 10  
¿Cuántas unidades desea comprar? (0 para volver)

**Ingreso 2**:  
Confirmación de compra  
Producto: Teclado  
Cantidad: 2  
Subtotal: $100.0  
¿Desea confirmar la compra? (S/n)

**Ingreso S**:  
Compra confirmada.  
2 unidad(es) de Teclado agregado(s) al carrito.

### 2. Volver al menú principal

******************************************
**       Bienvenido a swSolutions        **
******************************************
1. Ver productos
2. Ver carrito de compras
3. Finalizar compra
4. Salir
******************************************  
Por favor, seleccione una opción:

### 3. Seleccionar opción 2 - Ver carrito de compras

Carrito de Compras:
1. Teclado - Cantidad: 2 - Subtotal: $100.0

Total Final: $100.0
0. Volver al menú principal

**Ingreso 0** para regresar al menú.

### 4. Seleccionar opción 3 - Finalizar compra

¿Está seguro de que desea finalizar la compra? (S/n)

**Ingreso S**:

******************** FACTURA ********************  
Producto      Cantidad      Precio Unitario    Subtotal  
Teclado       2             $50.0             $100.0
-------------------------------------------------  
Subtotal: $100.00  
Impuestos (13%): $13.00  
Total Final: $113.00
*************************************************  
Gracias por su compra.

### 5. Fin del programa

Gracias por visitar SW Solutions. ¡Hasta luego!

# Documentación Breve del Proyecto

## Descripción General

Este proyecto es una aplicación de consola que simula un sistema de compra para una tienda de periféricos de computadora. Los usuarios pueden ver productos, agregarlos a un carrito de compras, eliminar productos, y finalizar una compra generando una factura con detalles de los productos comprados e impuestos.

## Estructura del Código

### 1. Main.kt
- Archivo principal que inicia la aplicación y maneja el flujo principal del sistema.
- **Función mostrarMenu**: muestra el menú de opciones donde el usuario puede ver productos, ver el carrito, finalizar la compra, o salir.
- **Función verProductos**: muestra los productos disponibles y permite al usuario seleccionar productos para agregarlos al carrito.
- **Función finalizarCompra**: permite al usuario confirmar la compra, genera la factura y vacía el carrito.
- **Función salir**: finaliza la aplicación.

### 2. Producto.kt
- Clase que representa los productos disponibles para la venta.
- **Función leerProductosDesdeArchivo**: carga los productos desde un archivo .txt.
- **Función escribirProductosEnArchivo**: guarda los productos actualizados (con las cantidades restantes) en el archivo.
- **Función seleccionarProducto**: permite al usuario seleccionar productos para comprarlos.
- **Función procesoDeCompra y confirmarCompra**: manejan la lógica de compra de productos y agregan los productos al carrito.

### 3. CarritoCompras.kt
- Clase que maneja el carrito de compras.
- **Función agregarProducto**: agrega un producto al carrito o aumenta su cantidad si ya existe en el carrito.
- **Función eliminarProducto**: permite eliminar un producto del carrito y devuelve la cantidad eliminada al inventario.
- **Función mostrarCarrito**: muestra el contenido del carrito y permite eliminar productos.
- **Función generarFactura**: genera una factura detallada, incluyendo subtotales e impuestos.
- **Función vaciarCarrito**: vacía el carrito después de una compra.
- Funciones adicionales: como calcular el total del carrito, verificar si está vacío, entre otras.

### Archivos

- **productos.txt**: Este archivo contiene los productos disponibles en el formato nombre,precio,cantidad. Es cargado por la aplicación y actualizado cuando los productos son comprados.

## Cómo Ejecutar la Aplicación

### 1. Requisitos Previos
- Tener instalado un entorno de desarrollo Kotlin (por ejemplo, IntelliJ IDEA con el plugin de Kotlin).
- Asegurarse de que el archivo productos.txt esté en el mismo directorio que el código de la aplicación y contenga productos en el siguiente formato:

  teclado,50.0,10
  monitor,200.0,5
  mouse,30.0,20


### 2. Pasos para Ejecutar
- Descargue o clone el código del proyecto en su entorno de desarrollo.
- Asegúrese de que todos los archivos de código estén en su lugar, incluyendo las clases Producto.kt, CarritoCompras.kt, y las funciones generales como limpiarPantalla().
- Abra el archivo Main.kt y ejecute el método main() desde su entorno.
- Interactúe con el sistema utilizando las opciones que aparecen en la consola. Use los números del menú para navegar a través de las funcionalidades como ver productos, agregar productos al carrito, o finalizar una compra.

## Consideraciones Finales
- *Archivos Externos*: El sistema depende de productos.txt para cargar la información de los productos. Asegúrese de actualizar este archivo para mantener un inventario correcto.
- *Errores*: El código maneja errores comunes como la entrada incorrecta del usuario utilizando bloques try-catch para prevenir que la aplicación falle por un mal ingreso de datos.
