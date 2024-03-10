fun main() {
    val player = Player("Hero")

    val enemies = listOf<Enemy>(Slime(), Goblin(), Goblin())

    val battle = Battle(player, enemies)

    battle.startBattle(player, enemies)
}

fun randPercentage(chance: Int): Boolean {
    val result = (1..100).random()

    return result <= chance
}