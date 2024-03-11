interface Item {
    fun desc()
}

class Scanner: Item {

    override fun toString(): String {
        return "Scanner"
    }

    override fun desc() {
        println("\nFunky rock item that allows you to scan an enemy and find out its weakness.\n")
    }

    fun effect(enemy: Enemy) {
        enemy.scan()
    }
}

open class Bomb: Item {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    override fun toString(): String {
        return "Bomb"
    }

    override fun desc() {
        println("\nA bomb. Plain and simple. Deals 10 damage to all enemies.\n")
    }

    open fun effect(enemies: List<Enemy>) {
        println("The bomb explodes!")
        Thread.sleep(100)
        for (enemy in enemies) {
            enemy.hp -= BOMB_DAMAGE
        }
        println("Dealt $BOMB_DAMAGE damage to all enemies!")
    }
}

class FireBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    val element = Element.FIRE

    override fun toString(): String {
        return "Fire Bomb"
    }

    override fun desc() {
        println("\nA specially fiery bomb. Deals 10 fire damage to all enemies.\n")
    }

    override fun effect(enemies: List<Enemy>) {
        println("The fire bomb explodes!")
        Thread.sleep(100)
        for (enemy in enemies) {
            if (enemy.weakness == element) {
                enemy.hp -= (BOMB_DAMAGE * 2)
                println("Hit the enemy's weakness! Dealt ${BOMB_DAMAGE * 2} damage to $enemy!")
            }
            else {
                enemy.hp -= BOMB_DAMAGE
                println("Dealt $BOMB_DAMAGE damage to $enemy!")
            }
        }
    }
}

class Potion: Item {
    companion object {
        const val POTION_HEAL = 10
    }

    override fun toString(): String {
        return "Potion"
    }

    override fun desc() {
        println("\nA potion that heals 10 HP. Tastes like cough syrup.\n")
    }

    fun effect(player: Player) {
        println("You drink the potion.")
        Thread.sleep(100)
        player.hp += POTION_HEAL
        println("The potion healed $POTION_HEAL HP!")
    }
}