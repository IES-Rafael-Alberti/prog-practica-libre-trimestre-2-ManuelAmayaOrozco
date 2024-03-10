class ItemMenu(player: Player) {

    fun itemMenu(player: Player, enemies: List<Enemy>) {

        var menuBrowse = true

        while (menuBrowse) {
            var itemCount = 1
            for (item in player.inventory) {
                println("$itemCount. ${item.name}")
                itemCount++
            }
            println("$itemCount. Exit Item Menu")
            println("Which item do you want to use?")

            var choice: Int?
            do {
                choice = readln().toIntOrNull()
                if (choice == null || choice <= 0 || choice >= player.inventory.size) {
                    println("Opción no válida, vuelve a intentarlo.")
                }
            } while (choice == null || choice <= 0 || choice >= player.inventory.size)

            if (choice == itemCount) {
                menuBrowse = false
            }
            else {
                val chosenItem = player.inventory[choice - 1]
                val itSelec = false

                while (!itSelec) {
                    println("What would you like to do with this item?")
                    println("1. USE     2. DESCRIPTION     3.CANCEL")

                    var itChoice: Int?
                    do {
                        itChoice = readln().toIntOrNull()
                        if (itChoice == null || itChoice <= 0 || itChoice >= 4) {
                            println("Opción no válida, vuelve a intentarlo.")
                        }
                    } while (itChoice == null || itChoice <= 0 || itChoice >= 4)

                    when (itChoice) {

                        1 -> {
                            if (chosenItem.type == "EneItem") {


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
                                        println("Opción no válida, vuelve a intentarlo.")
                                    }
                                } while (eneChoice == null || eneChoice <= 0 || eneChoice >= enemies.size)

                                val chosenEnemy = enemies[eneChoice - 1]


                            }
                        }

                    }
                }
            }
        }
    }
}

class ItemGestion<T>(private val type: T) {
    fun use(item: EneItem, enemy: Enemy) {
        item.effect(enemy)
    }
}