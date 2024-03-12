class Battle(player: Player, fieldEnemies: List<Any>) {

    companion object {
        const val CHANCE_OF_ESCAPE = 75
    }

    fun startBattle(player: Player, fieldEnemies: List<Enemy>): Boolean {

        val enemyList = fieldEnemies.toMutableList()

        val itemMenu = ItemMenu(player)

        var moneyReward = 0
        for (enemy in fieldEnemies) {
            moneyReward += enemy.moneyDrop
        }

        val battleState = true
        println("THE BATTLE BEGINS!")

        while (battleState) {

            if (enemyList.isEmpty()) {
                println("VICTORY!")
                Thread.sleep(100)
                player.money += moneyReward
                println("You got $moneyReward coins!")
                Thread.sleep(100)
                return true
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
                    println("Invalid option, try again.")
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
                                    println("Invalid option, try again.")
                                }
                            } while (eneChoice == null || eneChoice <= 0 || eneChoice > enemyList.size)

                            if (player.status == Ailment.PARALYSIS) {
                                val relief = randPercentage(player.chance_of_ailment_relief)
                                if (relief) {
                                    println("You were cured of your paralysis!")
                                    Thread.sleep(100)
                                    println("${player.name} attacks!")
                                    player.attack(enemyList[eneChoice - 1])
                                }
                                else {
                                    println("The paralysis doesn't allow you to move!")
                                    Thread.sleep(100)
                                }
                            }
                            else {
                                println("${player.name} attacks!")
                                player.attack(enemyList[eneChoice - 1])
                            }
                    }
                    else {
                        if (player.status == Ailment.PARALYSIS) {
                            val relief = randPercentage(player.chance_of_ailment_relief)
                            if (relief) {
                                println("You were cured of your paralysis!")
                                Thread.sleep(100)
                                println("${player.name} attacks!")
                                player.attack(enemyList[0])
                            }
                            else {
                                println("The paralysis doesn't allow you to move!")
                                Thread.sleep(100)
                            }
                        }
                        else {
                            println("${player.name} attacks!")
                            player.attack(enemyList[0])
                        }
                    }
                }

                2 -> {
                    println("${player.name} brazed for impact!")
                    player.defend()
                }

                3 -> {
                    itemMenu.itemMenu(player, enemyList)
                }

                4 -> {
                    val result = randPercentage(CHANCE_OF_ESCAPE)
                    if (result) {
                        println("Got away safely!")
                        return true
                    }
                    else {
                        println("You couldn't escape!")
                    }
                }

            }

            Thread.sleep(100)

            if (enemyList.isNotEmpty()) {
                var i = 0
                while (i <= enemyList.size) {
                    if (enemyList[i].hp <= 0) {
                        enemyList[i].defeat()
                        enemyList.remove(enemyList[i])
                    }
                    if (enemyList.isEmpty()) {
                        break
                    }
                    i++
                }
            }

            Thread.sleep(100)

            if (enemyList.isEmpty()) {
                println("VICTORY!")
                Thread.sleep(100)
                player.money += moneyReward
                println("You got $moneyReward coins!")
                Thread.sleep(100)
                return true
            }

            Thread.sleep(100)

            if (battleState) {
                for (enemy in enemyList) {
                    enemy.action(player)
                }
            }

            if (player.hp <= 0) {
                println("YOU WERE SLAIN!")
                Thread.sleep(100)
                return false
            }

        }
        return true
    }
}