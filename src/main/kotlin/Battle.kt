/**
 * Clase para las batallas, un sistema complejo que va determinado por un bucle principal, las
 * batallas no terminan hasta que todos los enemigos sean derrotados, el jugador sea derrotado
 * o se escape de la batalla. Escapar no te dará monedas y puede hacer que pierdas el turno.
 */
class Battle {

    companion object {
        const val CHANCE_OF_ESCAPE = 75 //Porcentaje de escapar de una batalla
    }

    /**
     * Función que controla la batalla, funciona a través de un bucle principal en el que siempre va
     * primero el jugador y después los enemigos.
     *
     * @param player El jugador, necesario para acceder a sus datos.
     * @param fieldEnemies Los enemigos que estarán en la batalla.
     *
     * @return Booleano que será true si se lográ derrotar a todos los enemigos o escapar de la batalla,
     * y que será false si el jugador es derrotado.
     */
    fun startBattle(player: Player, fieldEnemies: List<Enemy>): Boolean {

        val enemyList = fieldEnemies.toMutableList() //Se pasa la lista de enemigos a una lista mutable única para la batalla.

        val itemMenu = ItemMenu() //Acceso al menú de objetos del jugador

        var moneyReward = 0 //Recompensa de monedas, depende de todos los enemigos que haya en batalla.
        for (enemy in fieldEnemies) {
            moneyReward += enemy.moneyDrop
        }

        val battleState = true //Controla el bucle principal
        println("THE BATTLE BEGINS!")

        var sleepTurn = 0 //Contador de turnos en los que el jugador está dormido

        while (battleState) {

            if (enemyList.isEmpty()) { //Checkeo secundario de la lista de enemigos en caso de que sean derrotados tras su turno.
                println("VICTORY!")
                Thread.sleep(100)
                player.money += moneyReward
                println("You got $moneyReward coins!")
                Thread.sleep(100)
                return true
            }

            player.defStatus = false //Tras cada turno, el jugador pierde su estado de defensa

            if (player.status == Ailment.SLEEP) { //Si el jugador está dormido, no podrá hacer nada hasta que pasen los turnos necesarios
                println("You're fast asleep!")
                Thread.sleep(100)
                sleepTurn++
            }

            if (sleepTurn == player.turnsOfSleep) { //Una vez pasen los turnos necesarios, el jugador despierta y vuelve a la normalidad
                println("You wake up!")
                player.status = Ailment.NONE
                Thread.sleep(100)
                sleepTurn = 0
            }

            if (player.status != Ailment.SLEEP) { //TURNO JUGADOR (Si el jugador está dormido no podrá acceder a su turno.)
                var enemyCount = 1 //Este mini-bucle muestra todos los enemigos en pantalla y su vida actual al inicio.
                for (enemy in enemyList) {
                    println("$enemyCount. " + enemy.desc())
                    enemyCount++
                }

                println("What will you do?") //Muestra las opciones al jugador
                println("HP ${player.hp}/${player.maxHP}")
                println("1. ATTACK      2. DEFEND")
                println("3. ITEM        4. RUN")

                var choice: Int? //El jugador ha de elegir una opción válida del 1 al 4
                do {
                    choice = readln().toIntOrNull()
                    if (choice == null || choice <= 0 || choice >= 5) {
                        println("Invalid option, try again.")
                    }
                } while (choice == null || choice <= 0 || choice >= 5)

                Thread.sleep(100)

                when (choice) { //Todas las opciones

                    1 -> { //ATAQUE
                        if (enemyList.size > 1) { //Si la lista de enemigos tiene más de un integrante, el jugador ha de elegir a quién atacar
                            println("Which enemy will you attack?")

                            var eneChoice: Int? //Elige el enemigo al que atacar
                            do {
                                eneChoice = readln().toIntOrNull()
                                if (eneChoice == null || eneChoice <= 0 || eneChoice > enemyList.size) {
                                    println("Invalid option, try again.")
                                }
                            } while (eneChoice == null || eneChoice <= 0 || eneChoice > enemyList.size)

                            if (player.status == Ailment.PARALYSIS) { //Si el jugador tiene parálisis, no podrá atacar, pero también hay una posibilidad de que se cure automaticamente
                                val relief = randPercentage(player.chanceOfAilmentRelief)
                                if (relief) {
                                    println("You were cured of your paralysis!")
                                    player.status = Ailment.NONE
                                    Thread.sleep(100)
                                    println("${player.name} attacks!")
                                    player.attack(enemyList[eneChoice - 1])
                                }
                                else {
                                    println("The paralysis doesn't allow you to move!")
                                    Thread.sleep(100)
                                }
                            }
                            else { //El jugador ataca de manera normal
                                println("${player.name} attacks!")
                                player.attack(enemyList[eneChoice - 1])
                            }
                        }
                        else { //Si solo hay un enemigo, no hará falta elegir y el jugador lo atacará automáticamente
                            if (player.status == Ailment.PARALYSIS) {
                                val relief = randPercentage(player.chanceOfAilmentRelief)
                                if (relief) {
                                    println("You were cured of your paralysis!")
                                    player.status = Ailment.NONE
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

                    2 -> { //DEFENSA
                        println("${player.name} brazed for impact!")
                        player.defend() //Activa el estado de defensa
                    }

                    3 -> { //OBJETOS
                        itemMenu.itemMenu(player, enemyList) //Accede al menú de objetos
                    }

                    4 -> { //HUIR
                        var containsBoss = false //Miramos la lista de enemigos para ver si hay algún JEFE
                        for (enemy in enemyList) {
                            if (enemy.type == EnemyType.BOSS) {
                                containsBoss = true
                            }
                        }

                        if (containsBoss) { //Si hay un jefe, no será posible escapar de la batalla
                            println("Unable to escape such a powerful foe!")
                        }
                        else { //Solo se podrá huir si el resultado del porcentaje sale a nuestro favor
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

                }
            }


            Thread.sleep(100)

            if (enemyList.isNotEmpty()) { //Aquí nos deshacemos de los enemigos que sean derrotados tras el turno del jugador, avisándole cuando ocurra
                var i = 0
                while (i <= enemyList.size) {
                    if (enemyList[i].hp <= 0) {
                        enemyList[i].defeat()
                        enemyList.remove(enemyList[i])
                    }
                    if (enemyList.isEmpty()) { //Si la lista de enemigos se vuelve vacía mientras se hace el proceso, salimos del bucle
                        break
                    }
                    i++
                }
            }

            Thread.sleep(100)

            if (enemyList.isEmpty()) { //Miramos si todos los enemigos han sido derrotados para recibir la recompensa
                println("VICTORY!")
                Thread.sleep(100)
                player.money += moneyReward
                println("You got $moneyReward coins!")
                Thread.sleep(100)
                return true
            }

            Thread.sleep(100)

            if (battleState) { //TURNO DE LOS ENEMIGOS
                for (enemy in enemyList) { //Cada enemigo realiza una acción
                    enemy.action(player)
                }
            }

            if (player.hp <= 0) { //Si tu vida llega a 0 o menos, habrás sido derrotado y perderá el combate
                println("YOU WERE SLAIN!")
                Thread.sleep(100)
                return false
            }

        }
        return true
    }
}