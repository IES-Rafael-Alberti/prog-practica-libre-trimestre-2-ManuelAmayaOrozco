interface Enemy {
    fun attack(player: Player)
    fun desc(): String
    fun scan()
    fun defeat()
    fun action(player: Player)
    val weakness: Element
    var hp: Int
    val type: EnemyType
    val moneyDrop: Int
}

//BASIC ENEMIES

class Slime: Enemy {
    companion object {
        const val BASE_HP = 10
        const val BASE_ATK = 2
        const val CHANCE_OF_ATTACK = 70
    }

    override val type = EnemyType.NORMAL

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

    override val type = EnemyType.NORMAL

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

    override val type = EnemyType.NORMAL

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

class TinyMage: Enemy {
    companion object {
        const val BASE_HP = 15
        const val BASE_ATK = 3
        const val LIGHTNING_DAMAGE = 8
        const val CHANCE_OF_ATTACK = 70
        const val CHANCE_OF_LIGHTNING = 15
        const val INFLICT_PARALYSIS = 20
    }

    override val type = EnemyType.NORMAL

    override val weakness = Element.WATER

    override val moneyDrop = (10..25).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Tiny Mage"
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

    private fun lightning(player: Player) {
        val damage = (5..LIGHTNING_DAMAGE).random()
        val ailment = randPercentage(INFLICT_PARALYSIS)
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
            if (ailment) {
                player.status = Ailment.PARALYSIS
                println("You were inflicted with paralysis!")
            }
        }
    }

    override fun desc(): String {
        return "Tiny Mage -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__TINY MAGE__")
        Thread.sleep(50)
        println("A short fellow in mage clothes, they talk like 'mimimimimi', dislikes baths.")
        Thread.sleep(50)
        println("WEAKNESS: WATER.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Tiny Mage was defeated!")
    }

    override fun action(player: Player) {
        val result1 = randPercentage(CHANCE_OF_ATTACK)
        if (result1) {
            val result2 = randPercentage(CHANCE_OF_LIGHTNING)
            if (result2) {
                println("The tiny mage casts lightning upon you!")
                lightning(player)
            }
            else {
                println("The tiny mage smacks you with its wand!")
                attack(player)
            }
        }
        else {
            println("The tiny mage runs at you but ends up tripping!")
        }
    }
}

class MudFace: Enemy {
    companion object {
        const val BASE_HP = 25
        const val BASE_ATK = 5
        const val SPIT_DAMAGE = 7
        const val CHANCE_OF_ATTACK = 70
        const val CHANCE_OF_SPIT = 25
        const val INFLICT_POISON = 50
    }

    override val type = EnemyType.NORMAL

    override val weakness = Element.WATER

    override val moneyDrop = (10..25).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Mud-Face"
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

    private fun spit(player: Player) {
        val damage = (3..SPIT_DAMAGE).random()
        val ailment = randPercentage(INFLICT_POISON)
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
            if (ailment) {
                player.status = Ailment.POISON
                println("You were poisoned!")
            }
        }
    }

    override fun desc(): String {
        return "Mud-Face -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__MUD-FACE__")
        Thread.sleep(50)
        println("A creature made out of mud, their toxic spit can poison you.")
        Thread.sleep(50)
        println("WEAKNESS: WATER.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Mud-Face was defeated!")
    }

    override fun action(player: Player) {
        val result1 = randPercentage(CHANCE_OF_ATTACK)
        if (result1) {
            val result2 = randPercentage(CHANCE_OF_SPIT)
            if (result2) {
                println("The mud-face spits toxic waste at you!")
                spit(player)
            }
            else {
                println("The mud-face smacks you with its mud arm!")
                attack(player)
            }
        }
        else {
            println("The mud-mouth yawns.")
        }
    }
}

class Ghost: Enemy {
    companion object {
        const val BASE_HP = 10
        const val BASE_ATK = 3
        const val CHANCE_OF_ATTACK = 66
    }

    override val type = EnemyType.UNDEAD

    override val weakness = Element.ELECTRIC

    override val moneyDrop = (10..15).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Ghost"
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
        return "Ghost -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__GHOST__")
        Thread.sleep(50)
        println("A silly round ghost, intangible to normal attacks, but elemental attacks will do the trick.")
        Thread.sleep(50)
        println("WEAKNESS: ELECTRIC.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Ghost was defeated!")
    }

    override fun action(player: Player) {
        val result = randPercentage(CHANCE_OF_ATTACK)
        if (result) {
            println("The ghost licks your face!")
            attack(player)
        }
        else {
            println("The ghost makes a grimace at you!")
        }
    }
}

//BOSSES

class Siren: Enemy {
    companion object {
        const val BASE_HP = 50
        const val BASE_ATK = 7
        const val SCREECH_DAMAGE = 10
        const val CHANCE_OF_ATTACK = 80
        const val CHANCE_OF_SCREECH = 40
        const val CHANCE_OF_SONG = 50
        const val INFLICT_SLEEP = 35
    }

    override val type = EnemyType.BOSS

    override val weakness = Element.ELECTRIC

    override val moneyDrop = (50..85).random()

    override var hp = BASE_HP

    override fun toString(): String {
        return "Siren"
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

    private fun screech(player: Player) {
        val damage = (3..SCREECH_DAMAGE).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    private fun sing(player: Player) {
        val ailment = randPercentage(INFLICT_SLEEP)
        if (player.defStatus) {
            println("You covered your ears just in time!")
        }
        else {
            println("You managed to avoid falling asleep!")
            if (ailment) {
                player.status = Ailment.SLEEP
                println("You fell asleep!")
            }
        }
    }

    override fun desc(): String {
        return "Siren -> HP $hp/$BASE_HP"
    }

    override fun scan() {
        println("\n__SIREN__")
        Thread.sleep(50)
        println("A powerful songstress that disguises as a woman to lure in adventurers, very dangerous.")
        Thread.sleep(50)
        println("WEAKNESS: ELECTRIC.\n")
        Thread.sleep(100)
    }

    override fun defeat() {
        println("Siren was defeated!")
    }

    override fun action(player: Player) {
        val result1 = randPercentage(CHANCE_OF_ATTACK)
        if (result1) {
            val result2 = randPercentage(CHANCE_OF_SCREECH)
            if (result2) {
                val result3 = randPercentage(CHANCE_OF_SONG)
                if (result3) {
                    println("The siren sings a soothing lullaby!")
                    sing(player)
                }
                else {
                    println("The siren screeches loudly at you!")
                    screech(player)
                }
            }
            else {
                println("The siren bites at you!")
                attack(player)
            }
        }
        else {
            println("The siren licks her mouth menacingly.")
        }
    }
}