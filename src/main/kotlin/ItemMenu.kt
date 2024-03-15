/**
 * Menú para controlar todos los objetos que posee el jugador durante la batalla.
 */
class ItemMenu {

    /**
     * Función que permite al jugador moverse por el menú y elegir los objetos que desea utilizar,
     * obtener una descripción de dichos objetos o salir del menú.
     *
     * @param player El jugador que accede al menú.
     * @param enemies La lista de enemigos actuales en batalla.
     */
    fun itemMenu(player: Player, enemies: List<Enemy>) {

        var menuBrowse = true //Mantiene al jugador dentro del menú hasta que elija salir

        while (menuBrowse) { //Muestra todos los objetos por pantalla + la última opción para salir
            var itemCount = 1
            for (item in player.inventory) {
                println("$itemCount. $item")
                itemCount++
            }
            println("$itemCount. Exit Item Menu")
            println("Which item do you want to use?")

            var choice: Int? //Elección del jugador de cual objeto quiere usar o si quiere salir
            do {
                choice = readln().toIntOrNull()
                if (choice == null || choice <= 0 || choice > (player.inventory.size + 1)) {
                    println("Invalid choice, try again.")
                }
            } while (choice == null || choice <= 0 || choice > (player.inventory.size + 1))

            if (choice == itemCount) { //Si se elige la última opción, sale del menú y termina
                menuBrowse = false
            }
            else {
                val chosenItem = player.inventory[choice - 1] //Objeto elegido
                var itSelec = false

                while (!itSelec) {
                    println("What would you like to do with this item?")
                    println("1. USE     2. DESCRIPTION     3.CANCEL")

                    var itChoice: Int? //Elección de lo que desea hacer con el objeto o si desea cancelar su elección
                    do {
                        itChoice = readln().toIntOrNull()
                        if (itChoice == null || itChoice <= 0 || itChoice >= 4) {
                            println("Invalid choice, try again.")
                        }
                    } while (itChoice == null || itChoice <= 0 || itChoice >= 4)

                    when (itChoice) {

                        1 -> { //USAR (Dependiendo del tipo de objeto dará la posibilidad de elegir entre varios enemigos o no) El objeto usado se pierde después
                            if (chosenItem is Scanner) {

                                println("On which enemy do you want to use it?")
                                var enemyCount = 1
                                for (enemy in enemies) {
                                    println("$enemyCount. " + enemy.desc())
                                    enemyCount++
                                }

                                var eneChoice: Int?
                                do {
                                    eneChoice = readln().toIntOrNull()
                                    if (eneChoice == null ||eneChoice <= 0 || eneChoice >= enemies.size) {
                                        println("Invalid option, try again.")
                                    }
                                } while (eneChoice == null || eneChoice <= 0 || eneChoice >= enemies.size)

                                val chosenEnemy = enemies[eneChoice - 1]

                                chosenItem.effect(chosenEnemy)
                                player.inventory.remove(chosenItem)
                            }
                            if (chosenItem is Bomb) {
                                chosenItem.effect(enemies)
                                player.inventory.remove(chosenItem)
                            }
                            if (chosenItem is Potion) {
                                chosenItem.effect(player)
                                player.inventory.remove(chosenItem)
                            }
                            if (chosenItem is TomeOfPower) {
                                println("On which enemy do you want to use it?")
                                var enemyCount = 1
                                for (enemy in enemies) {
                                    println("$enemyCount. " + enemy.desc())
                                    enemyCount++
                                }

                                var eneChoice: Int?
                                do {
                                    eneChoice = readln().toIntOrNull()
                                    if (eneChoice == null ||eneChoice <= 0 || eneChoice >= enemies.size) {
                                        println("Invalid option, try again.")
                                    }
                                } while (eneChoice == null || eneChoice <= 0 || eneChoice >= enemies.size)

                                val chosenEnemy = enemies[eneChoice - 1]

                                chosenItem.effect(chosenEnemy)
                                player.inventory.remove(chosenItem)
                            }

                            itSelec = true
                            menuBrowse = false
                        }

                        2 ->  { //DESCRIPCIÓN OBJETO
                            chosenItem.desc()
                        }

                        3 -> { //SALIR DEL MENÚ
                            itSelec = true
                            menuBrowse = false
                        }
                    }
                }
            }
        }
    }
}