/**
 * Interfaz para la creación de eventos
 */
interface Event {
    /**
     * Función que incializa el evento.
     *
     * @param player El jugador que participará en el evento.
     */
    fun startEvent(player: Player)
}

/**
 * Evento en el que te encuentras con un Anciano que te pide dinero y puedes dárselo a cambio de un objeto aleatorio,
 * pero cuidado, porque puede que resulte que el Anciano sea un ladrón y te robe tu dinero.
 */
class OldMan: Event {

    companion object {
        const val CHANCE_OF_EVIL = 10 //Probabilidad de que el Anciano te robe
    }

    private val askMoney = (5..30).random() //El dinero que pedirá el Anciano

    private val rewardItem = listOf(Bomb(), Potion(), HighPotion(), TomeOfPower(), Antidote(), Paralyheal()) //Lista de objetos de recompensa

    private val evil = randPercentage(CHANCE_OF_EVIL) //Calculamos si el Anciano será malvado o no

    override fun startEvent(player: Player) {
        println("\nYou encounter an Old Man!")
        println("The Old Man speaks to you.")
        Thread.sleep(100)
        println("Old Man: 'Hello there young adventurer, care to help an old man?'")
        println("Old Man: 'I could really use $askMoney coins right now.'")
        Thread.sleep(100)
        println("Old Man: 'Think you could spare some for me please?'")
        println("1. YES      2. NO")
        println("What will you choose?")

        var choice: Int? //Elección de ayudar al Anciano
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {

            1 -> { //AYUDAR
                if (askMoney > player.money) { //Dinero insuficiente
                    println("\nOld Man: 'Oh my, sorry youngster but it seems you don't have enough money.'")
                    println("Old Man: 'I'll be seeing you around then.")
                    Thread.sleep(100)
                    println("The Old Man walked away...\n")
                    Thread.sleep(100)
                }
                else {
                    println("\nOld Man: 'Oh thank you kindly!'")
                    if (!evil) {
                        println("Old Man: 'Here, as a token of my gratitude, you can have this.")
                        player.getItem(rewardItem.random()) //Recompensa
                        Thread.sleep(100)
                        println("The Old Man walked away...\n")
                        Thread.sleep(100)
                    }
                    else { //Si el Anciano es malvado
                        println("Old Man: '...For being a total idiot!'")
                        println("Old Man: 'See you around sucker!'")
                        Thread.sleep(100)
                        println("The Old Thief Man ran off with your money...\n")
                        Thread.sleep(100)
                    }
                }
            }

            2 -> { //NO AYUDAR
                println("Old Man: 'Oh, that's a shame then...'")
                Thread.sleep(100)
                println("The Old Man walked away...\n")
                Thread.sleep(100)
            }
        }
    }
}

/**
 * Evento en el que ayudas a una Damisela en apuros y la salvas de unos Trasgos. Puedes obtener
 * objetos muy buenos y curarte por completo, pero si resulta ser malvada tendrás que luchar contra
 * un jefe.
 */
class Damsel: Event {

    companion object {
        const val CHANCE_OF_EVIL = 10
    }

    private val rewardMoney = (20..60).random() //Dinero de recompensa

    private val rewardItem = listOf(HighPotion(), TomeOfPower(), TomeOfLightning(), TomeOfFire(), TomeOfWater()) //Lista objetos de recompensa

    private val evil = randPercentage(CHANCE_OF_EVIL)

    override fun startEvent(player: Player) {
        println("\nYou encounter a Damsel in distress!")
        println("She seems to be surrounded by goblins.")
        Thread.sleep(100)
        println("Damsel: 'HEEELP!!! Please won't someone come help me?!'")
        Thread.sleep(100)
        println("What will you do?")
        println("1. HELP      2. IGNORE")

        var choice: Int? //Eleccion de ayudar a la Damisela
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {

            1 -> { //AYUDAR
                println("You decide to help her out.")
                println("You approach the goblins and prepare to fight!\n")
                Thread.sleep(100)
                Battle().startBattle(player, listOf(Goblin(),Goblin(),Goblin())) //Combate contra los Trasgos
                println("\nYou managed to defeat the goblins!")
                println("The Damsel approaches you.")
                Thread.sleep(100)
                println("Damsel: 'Thank you so much for saving me!'")
                if (evil) { //Si resulta ser malvada
                    println("The Damsel suddenly transforms into a horrifying beast!")
                    println("Siren: 'Let me reward you by making you MY MEAL!")
                    Thread.sleep(100)
                    Battle().startBattle(player, listOf(Siren())) //Combate contra la Sirena
                    println("Siren: 'NOOOOOO!!! HOW COULD I HAVE BEEN DEFEATEEEED?!'")
                    println("The Siren melts into a puddle of goop.")
                    println("She left something behind...")
                    Thread.sleep(100)
                    player.getItem(rewardItem.random()) //Objeto de recompensa por derrotarla
                }
                else {
                    println("Damsel: 'Here, you can have this as a thanks!'")
                    Thread.sleep(100)
                    player.getMoney(rewardMoney) //Dinero de recompensa
                    player.getItem(rewardItem.random()) //Objeto de recompensa
                    Thread.sleep(100)
                    println("Damsel: I wish you the best of luck on your quest!")
                    println("The kindness of the Damsel reached deep into your heart.")
                    println("Your MAX HP was increased!")
                    Thread.sleep(100)
                    player.maxHP += 10 //Aumento de vida máxima
                    player.hp = player.maxHP //Cura completa
                }
            }

            2 -> { //IGNORAR
                println("You decide to leave the Damsel to her luck...")
                Thread.sleep(100)
            }
        }
    }
}

/**
 * Evento en el que juegas al Piedra, Papel o Tijera contra un Bufón, si ganas tus monedas actuales
 * serán duplicadas, si pierdes perderás la mitad de tu dinero. Si empatas o no tienes dinero recibirás
 * monedas como compensación.
 */
class Roshambo: Event {

    companion object {
        const val COMPENSATION_MONEY_WIN = 250 //Dinero compensación victoria (Si no tienes monedas)
        const val COMPENSATION_MONEY_TIE = 150 //Dinero compensación empate
        const val COMPENSATION_MONEY_LOSE = 50 //Dinero compensación derrota (Si no tienes monedas)
    }

    /**
     * Clase enumerada que permite elegir entre Piedra, Papel o Tijera para el juego.
     */
    enum class RPS {
        ROCK,
        PAPER,
        SCISSORS
    }

    private val optFoe = listOf(RPS.ROCK, RPS.PAPER, RPS.SCISSORS).random() //Elección del Bufón durante el juego.

    override fun startEvent(player: Player) {
        println("You're suddenly ambushed by Games the Jester!")
        Thread.sleep(100)
        println("Games: 'Hey hey hey~! Games' the name and Roshambo's what I play~!'")
        Thread.sleep(100)
        println("Games: 'Wanna test your luck? Beat me and i'll double your current money~!'")
        println("Games: 'But if you lose then i'll be taking half of it~!'")
        Thread.sleep(100)
        println("Games: 'What do you say chummy chum~?'")
        println("1. YES      2. NO")

        var choice: Int? //Elección de jugar o no
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {

            1 -> { //JUGAR
                println("Games: 'Heeheehee~! Wise choice~!'")
                Thread.sleep(100)
                println("Games: 'Readyyyy~? ...ROOOOCK, PAPEEEEERRR-")
                println("1. ROCK!      2. PAPER!      3. SCISSORS!")

                var rpsChoice: Int? //Elige entre piedra, papel o tijera
                do {
                    rpsChoice = readln().toIntOrNull()
                    if (rpsChoice == null || rpsChoice <= 0 || rpsChoice >= 4) {
                        println("Invalid option, try again.")
                    }
                } while (rpsChoice == null || rpsChoice <= 0 || rpsChoice >= 4)

                var resPlayer: RPS? = null //La elección del jugador

                when (rpsChoice) {
                    1 -> resPlayer = RPS.ROCK
                    2 -> resPlayer = RPS.PAPER
                    3 -> resPlayer = RPS.SCISSORS
                }

                //El bufón dirá algo diferente dependiendo de su resultado
                if (optFoe == RPS.ROCK) {
                    println("ROCK!!!")
                }
                else if (optFoe == RPS.PAPER) {
                    println("PAPER!!!")
                }
                else {
                    println("SCISSORS!!!")
                }

                Thread.sleep(100)

                if ((resPlayer == RPS.ROCK && optFoe == RPS.SCISSORS) ||
                    (resPlayer == RPS.PAPER && optFoe == RPS.ROCK) ||
                    (resPlayer == RPS.SCISSORS && optFoe == RPS.PAPER)) { //VICTORIA
                    println("Games: 'WHAAAT??? NO WAY! YOU ACTUALLY WON!'")
                    Thread.sleep(100)
                    println("Games: 'Well, i'm a jester of my word, so here's your reward~!'")
                    Thread.sleep(100)
                    if (player.money == 0) { //Si el jugador no tiene dinero
                        println("Games: 'Huh, you seem to have no money! What a shame!'")
                        println("Games: 'Well, you still beat me, so allow me to give you this!'")
                        Thread.sleep(100)
                        player.getMoney(COMPENSATION_MONEY_WIN) //Compensación
                        println("Games: 'Don't spend it all in one place now~!'")
                        println("The Jester scampered off...\n")
                        Thread.sleep(100)
                    }
                    else {
                        player.money *= 2 //Duplicación del dinero
                        println("Your money was doubled to ${player.money} coins!")
                        Thread.sleep(100)
                        println("Games: 'Don't spend it all in one place now~!'")
                        println("The Jester scampered off...\n")
                    }
                }
                else if (resPlayer == optFoe) { //EMPATE
                    println("Games: 'Huh? A tie?! Well then, it is what it is~!'")
                    println("Games: 'Allow me to give you some cash as compensation~!'")
                    Thread.sleep(100)
                    player.getMoney(COMPENSATION_MONEY_TIE) //Compensación
                    println("Games: 'Don't spend it all in one place now~!'")
                    println("The Jester scampered off...\n")
                    Thread.sleep(100)
                }
                else { //DERROTA
                    println("Games: 'Whoops~! It seems that you have lost~!'")
                    println("Games: 'I'll be taking half of those coins now thank you very much~!'")
                    if (player.money == 0) { //Si el jugador no tiene dinero
                        println("Games: 'Huh, you seem to have no money! What a shame!'")
                        println("Games: 'That's no fun at all! I suppose you can have this, out of pity~'")
                        Thread.sleep(100)
                        player.getMoney(COMPENSATION_MONEY_LOSE) //Compensación
                        println("Games: 'Don't spend it all in one place now~!'")
                        println("The Jester scampered off...\n")
                        Thread.sleep(100)
                    }
                    else {
                        player.money /= 2 //Dinero a la mitad
                        println("Your money was reduce to ${player.money} coins...")
                        Thread.sleep(100)
                        println("Games: 'Better luck next time chummy chum~! Buh-bye~!'")
                        println("The Jester scampered off...\n")
                        Thread.sleep(100)
                    }
                }
            }

            2 -> { //NO JUGAR
                println("Games: 'Nooo? Boohoo then~! Maybe next time~!'")
                println("The Jester scampered off...\n")
                Thread.sleep(100)
            }
        }
    }
}

/**
 * Evento que invoca el menú de la tienda donde el jugador podrá comprar objetos.
 */
class ShopEncounter: Event {
    override fun startEvent(player: Player) {
        ShopMenu().shop(player)
    }
}

/**
 * Evento de la batalla final, con su dialogo y batalla contra el jefe final, después termina la aventura.
 */
class FinalBattle: Event {

    override fun startEvent(player: Player) {
        println("You've finally made it to the Evil Wizard's lair...")
        println("The Evil Wizard sits upon his throne, he has been expecting you...")
        Thread.sleep(100)
        println("Evil Wizard: 'So you're the one who has been trying to reach my tower?'")
        Thread.sleep(100)
        println("Evil Wizard: 'You don't seem all that impressive in person...'")
        Thread.sleep(100)
        println("Evil Wizard: 'But if you've made it this far then it must mean that you're quite powerful.'")
        Thread.sleep(100)
        println("Evil Wizard: 'In that case, I have an offer for you...'")
        Thread.sleep(100)
        println("Evil Wizard: 'JOIN ME! Give up on that stupid village and become my apprentice!'")
        Thread.sleep(100)
        println("Evil Wizard: 'Together we will be able to spread my brand and replace those dirty villages with- I dunno- huge supermarkets!'")
        Thread.sleep(100)
        println("Evil Wizard: 'Surely you're smart enough to accept my proposal... So what do you say?'")
        Thread.sleep(100)
        println("1. NO      2. YES")

        var choice: Int? //Elección de unirte a Evil Wizard y obtener un final malo
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {
            1 -> { //COMBATE FINAL
                println("Evil Wizard: 'THEN HAVE IT YOUR WAY! PREPARE TO DIE!'")
                Thread.sleep(100)
                println("THE FINAL BATTLE IS HERE!")
                Battle().startBattle(player, listOf(EvilWizard()))
                println("Evil Wizard: 'N-NO! IMPOSSIBLE! HOW COULD I HAVE BEEN DEFEATED?!'")
                Thread.sleep(100)
                println("Evil Wizard: 'H-HEAR ME OUT ${player.name.uppercase()}! YOU MAY HAVE WON THIS TIME-'")
                Thread.sleep(100)
                println("Evil Wizard: 'BUT AS LONG AS CAPITALISM AND EVIL LINGERS IN THIS WORLD, I TOO WILL RETURN!")
                Thread.sleep(100)
                println("Evil Wizard: 'SO JUST YOU WAIT- I WILL BUILD THAT COOL SUPERMARKET, YOU'LL SEEEEEEE!!!")
                Thread.sleep(100)
                println("Finally, the Evil Wizard had been felled, and the village was safe once more...")
                Thread.sleep(100)
                println("You return back home to your people, victorious, as they throw a feast in your honour.")
                Thread.sleep(100)
                println("Evil may reemerge once more in the future, but they all can rest safely knowing you'll be there to protect them.")
                Thread.sleep(100)
                println("--- THE END ---")
            }

            2 -> { //FINAL MALO
                println("Evil Wizard: 'Excellent... Now let's prepare to make our great plan...'")
                Thread.sleep(100)
                println("You decided to join the Evil Wizard and build cool supermarkets all over the world.")
                Thread.sleep(100)
                println("You two became rich together as partners in crime and created the most successful brand int he world.")
                Thread.sleep(100)
                println("Eventually, you decided to marry the Evil Wizard just cause you thought it'd be funny.")
                Thread.sleep(100)
                println("Now the world is a desolate wasteland of supermarkets and some other shops, feels pretty nice.")
                Thread.sleep(100)
                println("--- THE END? ---")
            }
        }
    }
}