package functions

fun limpiarPantalla() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
        } else {
            Runtime.getRuntime().exec("clear")
        }
    } catch (e: Exception) {
        repeat(50) { println() } // Si no puede limpiar la pantalla, imprime varias líneas en blanco como fallback
    }
}
