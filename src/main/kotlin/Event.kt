interface Event {
    fun startEvent(player: Player)
}

class OldMan: Event {

    companion object {
        const val CHANCE_OF_EVIL = 10
    }

    private val askMoney = (5..30).random()

    private val rewardItem = listOf(Bomb(), Potion(), TomeOfPower())

    private val evil = randPercentage(CHANCE_OF_EVIL)

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

        var choice: Int?
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {

            1 -> {
                if (askMoney > player.money) {
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
                        player.getItem(rewardItem.random())
                        Thread.sleep(100)
                        println("The Old Man walked away...\n")
                        Thread.sleep(100)
                    }
                    else {
                        println("Old Man: '...For being a total idiot!'")
                        println("Old Man: 'See you around sucker!'")
                        Thread.sleep(100)
                        println("The Old Thief Man ran off with your money...\n")
                        Thread.sleep(100)
                    }
                }
            }

            2 -> {
                println("Old Man: 'Oh, that's a shame then...'")
                Thread.sleep(100)
                println("The Old Man walked away...\n")
                Thread.sleep(100)
            }
        }
    }
}

class Damsel: Event {

    companion object {
        const val CHANCE_OF_EVIL = 10
    }

    private val rewardMoney = (20..60).random()

    private val rewardItem = listOf(Potion(), TomeOfPower(), TomeOfLightning(), TomeOfFire(), TomeOfWater())

    private val evil = randPercentage(CHANCE_OF_EVIL)

    override fun startEvent(player: Player) {
        println("\nYou encounter a Damsel in distress!")
        println("She seems to be surrounded by goblins.")
        Thread.sleep(100)
        println("Damsel: 'HEEELP!!! Please won't someone come help me?!'")
        Thread.sleep(100)
        println("What will you do?")
        println("1. HELP      2. IGNORE")

        var choice: Int?
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {

            1 -> {
                println("You decide to help her out.")
                println("You approach the goblins and prepare to fight!\n")
                Thread.sleep(100)
                Battle().startBattle(player, listOf(Goblin(),Goblin(),Goblin()))
                println("\nYou managed to defeat the goblins!")
                println("The Damsel approaches you.")
                Thread.sleep(100)
                println("Damsel: 'Thank you so much for saving me!'")
                if (evil) {
                    println("The Damsel suddenly transforms into a horrifying beast!")
                    println("Siren: 'Let me reward you by making you MY MEAL!")
                    Thread.sleep(100)
                    Battle().startBattle(player, listOf(Siren()))
                    println("Siren: 'NOOOOOO!!! HOW COULD I HAVE BEEN DEFEATEEEED?!'")
                    println("The Siren melts into a puddle of goop.")
                    println("She left something behind...")
                    Thread.sleep(100)
                    player.getItem(rewardItem.random())
                }
                else {
                    println("Damsel: 'Here, you can have this as a thanks!'")
                    Thread.sleep(100)
                    player.getMoney(rewardMoney)
                    player.getItem(rewardItem.random())
                    Thread.sleep(100)
                    println("Damsel: I wish you the best of luck on your quest!")
                    println("The kindness of the Damsel reached deep into your heart.")
                    println("Your MAX HP was increased!")
                    Thread.sleep(100)
                    player.maxHP += 10
                    player.hp = player.maxHP
                }
            }

            2 -> {
                println("You decide to leave the Damsel to her luck...")
                Thread.sleep(100)
            }
        }
    }
}

class Roshambo: Event {

    companion object {
        const val COMPENSATION_MONEY_WIN = 250
        const val COMPENSATION_MONEY_TIE = 150
        const val COMPENSATION_MONEY_LOSE = 50
    }

    enum class RPS {
        ROCK,
        PAPER,
        SCISSORS
    }

    val optFoe = listOf(RPS.ROCK, RPS.PAPER, RPS.SCISSORS).random()

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

        var choice: Int?
        do {
            choice = readln().toIntOrNull()
            if (choice == null || choice <= 0 || choice >= 3) {
                println("Invalid option, try again.")
            }
        } while (choice == null || choice <= 0 || choice >= 3)

        Thread.sleep(100)

        when (choice) {

            1 -> {
                println("Games: 'Heeheehee~! Wise choice~!'")
                Thread.sleep(100)
                println("Games: 'Readyyyy~? ...ROOOOCK, PAPEEEEERRR-")
                println("1. ROCK!      2. PAPER!      3. SCISSORS!")

                var rpsChoice: Int?
                do {
                    rpsChoice = readln().toIntOrNull()
                    if (rpsChoice == null || rpsChoice <= 0 || rpsChoice >= 4) {
                        println("Invalid option, try again.")
                    }
                } while (rpsChoice == null || rpsChoice <= 0 || rpsChoice >= 4)

                var resPlayer: RPS? = null

                when (rpsChoice) {
                    1 -> resPlayer = RPS.ROCK
                    2 -> resPlayer = RPS.PAPER
                    3 -> resPlayer = RPS.SCISSORS
                }

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
                    (resPlayer == RPS.SCISSORS && optFoe == RPS.PAPER)) {
                    println("Games: 'WHAAAT??? NO WAY! YOU ACTUALLY WON!'")
                    Thread.sleep(100)
                    println("Games: 'Well, i'm a jester of my word, so here's your reward~!'")
                    Thread.sleep(100)
                    if (player.money == 0) {
                        println("Games: 'Huh, you seem to have no money! What a shame!'")
                        println("Games: 'Well, you still beat me, so allow me to give you this!'")
                        Thread.sleep(100)
                        player.getMoney(COMPENSATION_MONEY_WIN)
                        println("Games: 'Don't spend it all in one place now~!'")
                        println("The Jester scampered off...\n")
                        Thread.sleep(100)
                    }
                    else {
                        player.money *= 2
                        println("Your money was doubled to ${player.money} coins!")
                        Thread.sleep(100)
                        println("Games: 'Don't spend it all in one place now~!'")
                        println("The Jester scampered off...\n")
                    }
                }
                else if (resPlayer == optFoe) {
                    println("Games: 'Huh? A tie?! Well then, it is what it is~!'")
                    println("Games: 'Allow me to give you some cash as compensation~!'")
                    Thread.sleep(100)
                    player.getMoney(COMPENSATION_MONEY_TIE)
                    println("Games: 'Don't spend it all in one place now~!'")
                    println("The Jester scampered off...\n")
                    Thread.sleep(100)
                }
                else {
                    println("Games: 'Whoops~! It seems that you have lost~!'")
                    println("Games: 'I'll be taking half of those coins now thank you very much~!'")
                    if (player.money == 0) {
                        println("Games: 'Huh, you seem to have no money! What a shame!'")
                        println("Games: 'That's no fun at all! I suppose you can have this, out of pity~'")
                        Thread.sleep(100)
                        player.getMoney(COMPENSATION_MONEY_LOSE)
                        println("Games: 'Don't spend it all in one place now~!'")
                        println("The Jester scampered off...\n")
                        Thread.sleep(100)
                    }
                    else {
                        player.money /= 2
                        println("Your money was reduce to ${player.money} coins...")
                        Thread.sleep(100)
                        println("Games: 'Better luck next time chummy chum~! Buh-bye~!'")
                        println("The Jester scampered off...\n")
                    }
                }
            }
        }
    }
}