class Player(val name: String) {
    companion object {
        const val BASE_HP = 20
        const val BASE_ATK = 5
        const val STARTING_MONEY = 10
    }

    var inventory = mutableListOf<Item>(Scanner(), FireBomb())

    private var weaponAtk = 0

    var defStatus = true

    var maxHP = BASE_HP

    var hp = maxHP

    private var atk = BASE_ATK + weaponAtk

    var element = Element.NONE

    var money = STARTING_MONEY

    fun attack(enemy: Enemy) {
        val atkRes = ((atk/4)..atk).random()
        if (element == enemy.weakness) {
            enemy.hp -= (atkRes * 2)
        }
        else {
            enemy.hp -= atkRes
        }
        println("Dealt $atkRes damage to $enemy!")
    }

    fun defend() {
        defStatus = true
    }
}