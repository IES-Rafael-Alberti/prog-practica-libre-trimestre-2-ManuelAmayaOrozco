class Player(val name: String) {
    companion object {
        const val BASE_HP = 20
        const val BASE_ATK = 5
    }

    var inventory = mutableListOf<Item<Any>>()

    private var weaponAtk = 0

    var defStatus = true

    var maxHP = BASE_HP

    var hp = maxHP

    private var atk = BASE_ATK + weaponAtk

    fun gainItem(item: Item<Any>) {
        println("Obtained ${item.name}!")
        inventory.add(item)
    }

    fun accessItem() {

    }

    fun attack(enemy: Enemy) {
        val atkRes = ((atk/4)..atk).random()
        enemy.hp -= atkRes
        println("Dealt $atkRes damage!")
    }

    fun defend() {
        defStatus = true
    }
}