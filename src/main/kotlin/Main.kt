fun main() {
    val player = Player("Hero")

    Adventure().adventure(player)
}

/**
 * Función que devuelve verdadero o falso dependiendo de si cae dentro del procentaje
 * proveido, usada durante toda la aventura.
 */
fun randPercentage(chance: Int): Boolean {
    val result = (1..100).random()

    return result <= chance
}