/**
 * La aventura en sí, controla todos los eventos y batallas que sucederan a lo largo de la aventura,
 * culminando en la batalla final en la que terminará la aventura.
 */
class Adventure {

    companion object {
        const val ADVENTURE_LENGTH = 5 //Longitud de la aventura
    }

    private var events = mutableListOf<Event>(OldMan(),Damsel(),Roshambo(),ShopEncounter()) //Todos los eventos que pueden ocurrir

    private var enemyPool = listOf<List<Enemy>>(listOf(Slime()), //Lista de todas los posibles combinaciones de enemigos que pueden ser encontradas
                                                listOf(Slime(),Slime()),
                                                listOf(Slime(),Slime(),Slime()),
                                                listOf(Goblin()),
                                                listOf(Slime(),Goblin()),
                                                listOf(Goblin(),Slime(),Goblin()),
                                                listOf(Goblin(),Goblin()),
                                                listOf(Ogre()),
                                                listOf(Ogre(),Goblin()),
                                                listOf(Goblin(),Ogre(),Goblin()),
                                                listOf(TinyMage()),
                                                listOf(TinyMage(),Slime()),
                                                listOf(MudFace()),
                                                listOf(TinyMage(),MudFace()),
                                                listOf(Ghost()),
                                                listOf(Ghost(),Slime()),
                                                listOf(Ghost(),Ghost()),
                                                listOf(Ghost(),Ghost(),Ghost()),
                                                listOf(Ghost(),MudFace(),TinyMage()))


    /**
     * Función que controla la aventura en sí, empieza detallando la historia y después pasa al bucle principal
     * (a menos que elijas no participar). El bucle dura cuantas 'rondas/areas' dicte [ADVENTURE_LENGTH], cada ronda/area
     * par será un evento aleatorio y el resto serán batallas, cuando se llegue a la última ronda/area tomará lugar el evento
     * de la batalla final y terminará la aventura después.
     *
     * @param player El jugador que participará en la aventura, ya viene creado anteriormente.
     */
    fun adventure(player: Player) {

        var startAdventure = false //Controla el bucle de la aventura

        println("Once upon a time, in a village in the middle of nowhere...")
        println("There lived happy villagers who did villager stuff on a daily basis.")
        Thread.sleep(100)
        println("Until the Evil Wizard came along and created a massive tower nearby!")
        println("The tower continued to spawn monsters that endangered the villagers.")
        Thread.sleep(100)
        println("And the worst part... the Evil Wizard threatened to destroy the village!")
        Thread.sleep(100)
        println("JUST SO HE COULD PUT A SUPERMARKET IN ITS PLACE!")
        Thread.sleep(100)
        println("That's where YOU come in! You must save the village from the Evil Wizard!")
        Thread.sleep(100)
        println("What do you say? Ready to set off on an adventure?")
        println("1. YES      2. NO")

        //Estos bucles de elección son muy comunes durante todo el programa,
        //ya que los considero muy útiles a la hora de elegir entre diversas
        //opciones de manera simple y eficiente.
        var choice: Int?
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {
            1 -> startAdventure = true
            2 -> startAdventure = false
        }

        if (startAdventure) {
            println("Knew we could count on you!")
            println("Now go forth brave ${player.name}, your adventure has just begun!")
            Thread.sleep(100)
        }
        else { //Final malo si eliges no ir en la aventura.
            println("...Oh.")
            Thread.sleep(100)
            println("...Well I can't really force you to go so-")
            Thread.sleep(100)
            println("Why are you even playing this if you don't want to go on an adventure???")
            Thread.sleep(100)
            println("Talk about stupid.")
            Thread.sleep(100)
            println("--- THE END ---")
        }

        var areaCount = 1 //Contador de las areas/encuentros/rondas de la aventura

        while(startAdventure) {
            println("You march forth...")
            Thread.sleep(100)
            if ((areaCount % 2) == 0) { //Areas/encuentros/rondas pares serán eventos
                val event = events.random() //Se escoge evento de la lista predeterminada de eventos
                event.startEvent(player)
                if (event != ShopEncounter()) { //Todos los eventos solo ocurren una vez en la aventura, excepto las tiendas que pueden aparecer cuando sea
                    events.remove(event)
                }
            }
            else if(areaCount == ADVENTURE_LENGTH) { //Si se llega al final comienza la Batalla Final
                FinalBattle().startEvent(player)
                startAdventure = false
            }
            else { //El resto de areas/rondas/encuentros serán batallas
                println("You were suddenly attacked by monsters!")
                Thread.sleep(100)
                val enemies = enemyPool.random() //Se escoge un grupo de enemigos predeterminados de la lsita, se pueden repetir
                val resultBattle = Battle().startBattle(player, enemies)
                if (!resultBattle) { //Si se pierde una batalla, termina la aventura.
                    startAdventure = false
                }
            }
            areaCount++ //A la siguiente area/encuentro/ronda.
        }
    }
}