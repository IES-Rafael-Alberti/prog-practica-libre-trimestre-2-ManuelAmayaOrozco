interface Item {
    fun desc()
}

//SCANNER

class Scanner: Item {

    override fun toString(): String {
        return "Scanner"
    }

    override fun desc() {
        println("\nFunky rock item that allows you to scan an enemy and find out its weakness.\n")
    }

    fun effect(enemy: Enemy) {
        println("You scan the $enemy!")
        Thread.sleep(100)
        enemy.scan()
    }
}

//BOMBS

open class Bomb: Item {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    override fun toString(): String {
        return "Bomb"
    }

    override fun desc() {
        println("\nA bomb. Plain and simple. Deals 10 damage to all enemies.\n")
        Thread.sleep(100)
    }

    open fun effect(enemies: List<Enemy>) {
        println("The bomb explodes!")
        Thread.sleep(100)
        for (enemy in enemies) {
            if (enemy.type == EnemyType.UNDEAD) {
                println("The explosion phases through the $enemy")
                Thread.sleep(100)
            }
            else {
                enemy.hp -= BOMB_DAMAGE
                println("Dealt $BOMB_DAMAGE damage to $enemy!")
                Thread.sleep(100)
            }
        }
    }
}

class FireBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    private val element = Element.FIRE

    override fun toString(): String {
        return "Fire Bomb"
    }

    override fun desc() {
        println("\nA specially fiery bomb. Deals 10 fire damage to all enemies.\n")
        Thread.sleep(100)
    }

    override fun effect(enemies: List<Enemy>) {
        println("The fire bomb explodes!")
        Thread.sleep(100)
        for (enemy in enemies) {
            if (enemy.weakness == element) {
                enemy.hp -= (BOMB_DAMAGE * 2)
                println("Hit the enemy's weakness! Dealt ${BOMB_DAMAGE * 2} damage to $enemy!")
                Thread.sleep(100)
            }
            else {
                enemy.hp -= BOMB_DAMAGE
                println("Dealt $BOMB_DAMAGE damage to $enemy!")
                Thread.sleep(100)
            }
        }
    }
}

class WaterBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    private val element = Element.FIRE

    override fun toString(): String {
        return "Water Bomb"
    }

    override fun desc() {
        println("\nA wobbly water bomb. Deals 10 water damage to all enemies.\n")
        Thread.sleep(100)
    }

    override fun effect(enemies: List<Enemy>) {
        println("The water bomb bursts!")
        Thread.sleep(100)
        for (enemy in enemies) {
            if (enemy.weakness == element) {
                enemy.hp -= (BOMB_DAMAGE * 2)
                println("Hit the enemy's weakness! Dealt ${BOMB_DAMAGE * 2} damage to $enemy!")
                Thread.sleep(100)
            }
            else {
                enemy.hp -= BOMB_DAMAGE
                println("Dealt $BOMB_DAMAGE damage to $enemy!")
                Thread.sleep(100)
            }
        }
    }
}

class ShockBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    private val element = Element.ELECTRIC

    override fun toString(): String {
        return "Shock Bomb"
    }

    override fun desc() {
        println("\nA tingly bomb full of electricity. Deals 10 electric damage to all enemies.\n")
        Thread.sleep(100)
    }

    override fun effect(enemies: List<Enemy>) {
        println("The shock bomb scatters!")
        Thread.sleep(100)
        for (enemy in enemies) {
            if (enemy.weakness == element) {
                enemy.hp -= (BOMB_DAMAGE * 2)
                println("Hit the enemy's weakness! Dealt ${BOMB_DAMAGE * 2} damage to $enemy!")
                Thread.sleep(100)
            }
            else {
                enemy.hp -= BOMB_DAMAGE
                println("Dealt $BOMB_DAMAGE damage to $enemy!")
                Thread.sleep(100)
            }
        }
    }
}

//POTIONS

class Potion: Item {
    companion object {
        const val POTION_HEAL = 10
    }

    override fun toString(): String {
        return "Potion"
    }

    override fun desc() {
        println("\nA potion that heals 10 HP. Tastes like cough syrup.\n")
        Thread.sleep(100)
    }

    fun effect(player: Player) {
        println("You drink the potion.")
        Thread.sleep(100)
        player.hp += POTION_HEAL
        println("The potion healed $POTION_HEAL HP!")
        Thread.sleep(100)
    }
}

//TOMES

open class TomeOfPower: Item {
    companion object {
        const val TOME_DAMAGE = 10
    }

    override fun toString(): String {
        return "Tome of Power"
    }

    override fun desc() {
        println("\nA tome of ancient magic, deals 10 damage to an enemy, no matter their type.\n")
        Thread.sleep(100)
    }

    open fun effect(enemy: Enemy) {
        println("The tome shoots a magical orb!")
        Thread.sleep(100)
        enemy.hp -= TOME_DAMAGE
        println("Dealt $TOME_DAMAGE damage to $enemy!")
        Thread.sleep(100)
    }
}

class TomeOfFire: Item, TomeOfPower() {
    companion object {
        const val TOME_DAMAGE = 5
    }

    private val element = Element.FIRE

    override fun toString(): String {
        return "Tome of Fire"
    }

    override fun desc() {
        println("\nA tome that casts large flames, deals 5 damage to an enemy, no matter their type.\n")
    }

    override fun effect(enemy: Enemy) {
        println("The tome casts powerful flames!")
        Thread.sleep(100)
        if (enemy.weakness == element) {
            enemy.hp -= (TOME_DAMAGE * 2)
            println("Hit the enemy's weakness! Dealt ${TOME_DAMAGE * 2} damage to $enemy!")
            Thread.sleep(100)
        }
        else {
            enemy.hp -= TOME_DAMAGE
            println("Dealt $TOME_DAMAGE damage to $enemy!")
            Thread.sleep(100)
        }
    }
}

class TomeOfWater: Item, TomeOfPower() {
    companion object {
        const val TOME_DAMAGE = 5
    }

    private val element = Element.WATER

    override fun toString(): String {
        return "Tome of Water"
    }

    override fun desc() {
        println("\nA tome that casts many waves, deals 5 damage to an enemy, no matter their type.\n")
    }

    override fun effect(enemy: Enemy) {
        println("The tome casts massive waves!")
        Thread.sleep(100)
        if (enemy.weakness == element) {
            enemy.hp -= (TOME_DAMAGE * 2)
            println("Hit the enemy's weakness! Dealt ${TOME_DAMAGE * 2} damage to $enemy!")
            Thread.sleep(100)
        }
        else {
            enemy.hp -= TOME_DAMAGE
            println("Dealt $TOME_DAMAGE damage to $enemy!")
            Thread.sleep(100)
        }
    }
}

class TomeOfLightning: Item, TomeOfPower() {
    companion object {
        const val TOME_DAMAGE = 5
    }

    private val element = Element.ELECTRIC

    override fun toString(): String {
        return "Tome of Lightning"
    }

    override fun desc() {
        println("\nA tome that casts sudden lightning, deals 5 damage to an enemy, no matter their type.\n")
    }

    override fun effect(enemy: Enemy) {
        println("The tome casts several lightning bolts!")
        Thread.sleep(100)
        if (enemy.weakness == element) {
            enemy.hp -= (TOME_DAMAGE * 2)
            println("Hit the enemy's weakness! Dealt ${TOME_DAMAGE * 2} damage to $enemy!")
            Thread.sleep(100)
        }
        else {
            enemy.hp -= TOME_DAMAGE
            println("Dealt $TOME_DAMAGE damage to $enemy!")
            Thread.sleep(100)
        }
    }
}