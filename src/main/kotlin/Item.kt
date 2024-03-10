interface Item<in T> {
    val name: String
    val type: String
    fun desc()
}

interface SelfItem: Item<SelfItem> {
    fun effect(player: Player)
}

interface EneItem: Item<EneItem> {
    fun effect(enemy: Enemy)
}

interface MultiEneItem: Item<MultiEneItem> {
    fun effect(enemies: List<Enemy>)
}

class Scanner: EneItem{
    override val name = "Scanner"

    override val type = "EneItem"

    override fun desc() {
        println("\nFunky rock item that allows you to scan an enemy and find out its weakness.\n")
    }

    override fun effect(enemy: Enemy) {
        enemy.scan()
    }
}