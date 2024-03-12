class ItemMenu(player: Player) {

    fun itemMenu(player: Player, enemies: List<Enemy>) {

        var menuBrowse = true

        while (menuBrowse) {
            var itemCount = 1
            for (item in player.inventory) {
                println("$itemCount. $item")
                itemCount++
            }
            println("$itemCount. Exit Item Menu")
            println("Which item do you want to use?")

            var choice: Int?
            do {
                choice = readln().toIntOrNull()
                if (choice == null || choice <= 0 || choice > (player.inventory.size + 1)) {
                    println("Invalid choice, try again.")
                }
            } while (choice == null || choice <= 0 || choice > (player.inventory.size + 1))

            if (choice == itemCount) {
                menuBrowse = false
            }
            else {
                val chosenItem = player.inventory[choice - 1]
                var itSelec = false

                while (!itSelec) {
                    println("What would you like to do with this item?")
                    println("1. USE     2. DESCRIPTION     3.CANCEL")

                    var itChoice: Int?
                    do {
                        itChoice = readln().toIntOrNull()
                        if (itChoice == null || itChoice <= 0 || itChoice >= 4) {
                            println("Invalid choice, try again.")
                        }
                    } while (itChoice == null || itChoice <= 0 || itChoice >= 4)

                    when (itChoice) {

                        1 -> {
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

                        2 ->  {
                            chosenItem.desc()
                        }

                        3 -> {
                            itSelec = true
                            menuBrowse = false
                        }
                    }
                }
            }
        }
    }
}