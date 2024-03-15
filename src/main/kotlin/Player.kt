/**
 * La clase que identifica al jugador, viene ya creada por defecto.
 *
 * @param name El nombre que tendrá el jugador durante la aventura.
 */
class Player(val name: String) {
    companion object {
        const val BASE_HP = 50 //Vida del jugador al empezar
        const val BASE_ATK = 5 //Ataque base del jugador
        const val STARTING_MONEY = 20 //Dinero que tendrá el jugador al empezar
    }

    var inventory = mutableListOf<Item>(Scanner(), FireBomb(), Potion()) //Inventario del jugador, será actualizado conforme reciba objetos o los use

    var weaponAtk = 0 //Bonus de ataque dependiendo de las armas que el jugador obtenga

    var status = Ailment.NONE //Efecto negativo que tiene el jugador actualmente

    var defStatus = false //Estado de defensa, permite recibir la mitad de daño de un ataque enemigo si se activa

    var maxHP = BASE_HP //Vida máxmia del jugador, puede aumentar durante la aventura

    var hp = maxHP //Vida actual del jugador

    private var atk = BASE_ATK + weaponAtk //Ataque máximo del jugador

    var element = Element.NONE //Elemento actual de los ataques básicos del jugador

    val chanceOfAilmentRelief = 10 //Probabilidad de poder aliviarse de los efectos negativos sin usar objetos

    val turnsOfSleep = 3 //Turnos que el jugador tarda en desperrarse del sueño

    var money = STARTING_MONEY //Dinero actual del jugador

    /**
     * Función que permite al jugador atacar al enemigo, si el jugador posee un elemento será capaz de hacer el
     * doble de daño a un enemigo que sea debil a ese elemento. Los enemigos UNDEAD/NO MUERTO serán invulnerables
     * si no se tiene elemento
     *
     * @param enemy El enemigo que será atacado
     */
    fun attack(enemy: Enemy) {
        if (enemy.type == EnemyType.UNDEAD) {
            if (element == Element.NONE) {
                println("The attack phases through the $enemy")
                Thread.sleep(100)
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

    /**
     * Función que permite activar la defensa del jugador durante un turno, la defensa activa permite
     * bloquear la mitad de la mayoría de ataques de los enemigos.
     */
    fun defend() {
        defStatus = true
    }

    /**
     * Función que actualiza el dinero del jugador.
     *
     * @param rewardMoney El dinero que será añadido.
     */
    fun getMoney(rewardMoney: Int) {
        println("Obtained $rewardMoney coins!")
        Thread.sleep(100)
        money += rewardMoney
    }

    /**
     * Función que actualiza el inventario del jugador y le añade un objeto nuevo.
     *
     * @param item El objeto que será añadido al inventario.
     */
    fun getItem(item: Item) {
        println("You received $item")
        Thread.sleep(100)
        inventory.add(item)
    }
}