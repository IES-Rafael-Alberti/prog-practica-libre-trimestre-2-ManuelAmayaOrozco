class Battle(player: Player, fieldEnemies: List<Any>) {

    companion object {
        const val CHANCE_OF_ESCAPE = 75
    }

    fun startBattle(player: Player, fieldEnemies: List<Enemy>) {

        val enemyList = fieldEnemies.toMutableList()

        var battleState = true
        println("THE BATTLE BEGINS!")

        while (battleState) {

            if (enemyList.isEmpty()) {
                println("VICTORY!")
                Thread.sleep(100)
                battleState = false
            }

            player.defStatus = false

            var enemyCount = 1
            for (enemy in enemyList) {
                println("$enemyCount. " + enemy.desc())
                enemyCount++
            }

            println("What will you do?")
            println("HP ${player.hp}/${player.maxHP}")
            println("1. ATTACK      2. DEFEND")
            println("3. ITEM        4. RUN")

            var choice: Int?
            do {
                choice = readln().toIntOrNull()
                if (choice == null || choice <= 0 || choice >= 5) {
                    println("Opción no válida, vuelve a intentarlo.")
                }
            } while (choice == null || choice <= 0 || choice >= 5)

            Thread.sleep(100)

            when (choice) {

                1 -> {
                    if (enemyList.size > 1) {
                        println("Which enemy will you attack?")

                        var eneChoice: Int?
                        do {
                            eneChoice = readln().toIntOrNull()
                            if (eneChoice == null || eneChoice <= 0 || eneChoice > enemyList.size) {
                                println("Opción no válida, vuelve a intentarlo.")
                            }
                        } while (eneChoice == null || eneChoice <= 0 || eneChoice > enemyList.size)

                        println("${player.name} attacks!")
                        player.attack(enemyList[eneChoice - 1])
                    }
                    else {
                        println("${player.name} attacks!")
                        player.attack(enemyList[0])
                    }
                }

                2 -> {
                    println("${player.name} brazed for impact!")
                    player.defend()
                }

                3 -> {

                }

                4 -> {
                    val result = randPercentage(CHANCE_OF_ESCAPE)
                    if (result) {
                        println("Got away safely!")
                        battleState = false
                    }
                    else {
                        println("You couldn't escape!")
                    }
                }

            }

            Thread.sleep(100)

            if (enemyList.isNotEmpty()) {
                for (enemy in enemyList) {
                    if (enemy.hp <= 0) {
                        enemy.defeat()
                        enemyList.remove(enemy)
                    }
                    if (enemyList.isEmpty()) {
                        break
                    }
                }
            }

            Thread.sleep(100)

            if (enemyList.isEmpty()) {
                println("VICTORY!")
                battleState = false
            }

            Thread.sleep(100)

            if (battleState) {
                for (enemy in enemyList) {
                    enemy.action(player)
                }
            }

            if (player.hp <= 0) {
                println("YOU WERE SLAIN!")
                battleState = false
            }

        }
    }

}