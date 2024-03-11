interface Enemy {
    fun attack(player: Player)
    fun desc(): String
    fun scan()
    fun defeat()
    fun action(player: Player)
    val weakness: Element
    var hp: Int
    val moneyDrop: Int
}

class Slime: Enemy {
    companion object {
        const val BASE_HP = 10
        const val BASE_ATK = 2
        const val CHANCE_OF_ATTACK = 70
    }

    override val weakness = Element.FIRE

    override val moneyDrop = (1..10).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Slime"
    }

    override fun attack(player: Player) {
        val damage = (1..BASE_ATK).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    override fun desc(): String {
        return "Slime -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__SLIME__")
        Thread.sleep(50)
        println("Very weak enemy, but can prove problematic when in large numbers.")
        Thread.sleep(50)
        println("WEAKNESS: FIRE.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Slime was defeated!")
    }

    override fun action(player: Player) {
        val result = randPercentage(CHANCE_OF_ATTACK)
        if (result) {
            println("The Slime attacks!")
            attack(player)
        }
        else {
            println("The slime wobbles around lazily!")
        }
    }
}

class Goblin: Enemy {
    companion object {
        const val BASE_HP = 20
        const val BASE_ATK = 3
        const val BACKSTAB_DAMAGE = 5
        const val CHANCE_OF_ATTACK = 50
        const val CHANCE_OF_BACKSTAB = 35
    }

    override val weakness = Element.FIRE

    override val moneyDrop = (5..20).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Goblin"
    }

    override fun attack(player: Player) {
        val damage = (1..BASE_ATK).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    private fun backstab(player: Player) {
        val damage = (1..BACKSTAB_DAMAGE).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    override fun desc(): String {
        return "Goblin -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__GOBLIN__")
        Thread.sleep(50)
        println("Average enemy, troublesome in groups, beware their deadly backstabs.")
        Thread.sleep(50)
        println("WEAKNESS: FIRE.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Goblin was defeated!")
    }

    override fun action(player: Player) {
        val result1 = randPercentage(CHANCE_OF_ATTACK)
        if (result1) {
            val result2 = randPercentage(CHANCE_OF_BACKSTAB)
            if (result2) {
                println("The goblin pulls a sneaky backstab!")
                backstab(player)
            }
            else {
                println("The goblin takes a swing at you!")
                attack(player)
            }
        }
        else {
            println("The goblin picks its nose, nasty!")
        }
    }
}

class Ogre: Enemy {
    companion object {
        const val BASE_HP = 45
        const val BASE_ATK = 5
        const val HEAVY_SLAM_DAMAGE = 10
        const val CHANCE_OF_ATTACK = 80
        const val CHANCE_OF_HEAVY_SLAM = 10
    }

    override val weakness = Element.FIRE

    override val moneyDrop = (20..50).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Ogre"
    }

    override fun attack(player: Player) {
        val damage = (1..BASE_ATK).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    private fun heavySlam(player: Player) {
        val damage = (3..HEAVY_SLAM_DAMAGE).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    override fun desc(): String {
        return "Ogre -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__OGRE__")
        Thread.sleep(50)
        println("Strong enemies, lots of health and power, should be prioritised.")
        Thread.sleep(50)
        println("WEAKNESS: FIRE.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Ogre was defeated!")
    }

    override fun action(player: Player) {
        val result1 = randPercentage(CHANCE_OF_ATTACK)
        if (result1) {
            val result2 = randPercentage(CHANCE_OF_HEAVY_SLAM)
            if (result2) {
                println("The ogre slams you with a huge boulder!")
                heavySlam(player)
            }
            else {
                println("The ogre punches you!")
                attack(player)
            }
        }
        else {
            println("The ogre scratches its butt.")
        }
    }
}