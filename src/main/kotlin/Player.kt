class Player(val name: String) {
    companion object {
        const val BASE_HP = 20
        const val BASE_ATK = 5
        const val STARTING_MONEY = 10
    }

    var inventory = mutableListOf<Item>(Scanner(), FireBomb())

    private var weaponAtk = 0

    var status = Ailment.NONE

    var defStatus = true

    var maxHP = BASE_HP

    var hp = maxHP

    private var atk = BASE_ATK + weaponAtk

    private var element = Element.NONE

    val chanceOfAilmentRelief = 10

    val turnsOfSleep = 3

    var money = STARTING_MONEY

    fun attack(enemy: Enemy) {
        if (enemy.type == EnemyType.UNDEAD) {
            if (element == Element.NONE) {
                println("The attack phases through the $enemy")
                Thread.sleep(100)
            }
        }
        else {
            val atkRes = ((atk/4)..atk).random()
            if (element == enemy.weakness) {
                enemy.hp -= (atkRes * 2)
            }
            else {
                enemy.hp -= atkRes
            }
            println("Dealt $atkRes damage to $enemy!")
            Thread.sleep(100)
        }
    }

    fun defend() {
        defStatus = true
    }

    fun getMoney(rewardMoney: Int) {
        println("Obtained $rewardMoney coins!")
        Thread.sleep(100)
        money += rewardMoney
    }

    fun getItem(item: Item) {
        println("You received $item")
        Thread.sleep(100)
        inventory.add(item)
    }
}