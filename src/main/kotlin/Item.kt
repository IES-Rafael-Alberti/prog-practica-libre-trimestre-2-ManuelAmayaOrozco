/**
 * Interfaz que identifica a los objetos.
 */
interface Item {
    /**
     * Función que provee una breve descripción del objeto y sus efectos.
     */
    fun desc()
}

//SCANNER/ESCANER

/**
 * Objeto único que es capaz de escanear a un enemigo y mostrar sus debilidades.
 */
class Scanner: Item {

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Scanner"
    }

    override fun desc() {
        println("\nFunky rock item that allows you to scan an enemy and find out its weakness.\n")
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemy El enemigo que será afectado por el objeto.
     */
    fun effect(enemy: Enemy) {
        println("You scan the $enemy!")
        Thread.sleep(100)
        enemy.scan()
    }
}

//BOMBS/BOMBAS

/**
 * Objeto de tipo bomba, un ataque de un solo uso que hace un daño específico a todos los enemigos en una batalla.
 */
open class Bomb: Item {

    companion object {
        const val BOMB_DAMAGE = 10 //Daño de la bomba
    }

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Bomb"
    }

    override fun desc() {
        println("\nA bomb. Plain and simple. Deals 10 damage to all enemies.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemies Todos los enemigos que serán afectados por el objeto.
     */
    open fun effect(enemies: List<Enemy>) {
        println("The bomb explodes!")
        Thread.sleep(100)
        for (enemy in enemies) {
            if (enemy.type == EnemyType.UNDEAD) { //Los UNDEAD/NO MUERTOS no reciben daño de las bombas normales.
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

/**
 * Objeto de tipo bomba, siendo una variante de fuego de la clase anterior.
 */
class FireBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    private val element = Element.FIRE //Elemento de la bomba

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Fire Bomb"
    }

    override fun desc() {
        println("\nA specially fiery bomb. Deals 10 fire damage to all enemies.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemies Todos los enemigos que serán afectados por el objeto.
     */
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

/**
 * Objeto de tipo bomba, siendo una variante de agua de la clase anterior.
 */
class WaterBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    private val element = Element.WATER

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Water Bomb"
    }

    override fun desc() {
        println("\nA wobbly water bomb. Deals 10 water damage to all enemies.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemies Todos los enemigos que serán afectados por el objeto.
     */
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

/**
 * Objeto de tipo bomba, siendo una variante eléctrica de la clase anterior.
 */
class ShockBomb: Bomb() {

    companion object {
        const val BOMB_DAMAGE = 10
    }

    private val element = Element.ELECTRIC

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Shock Bomb"
    }

    override fun desc() {
        println("\nA tingly bomb full of electricity. Deals 10 electric damage to all enemies.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemies Todos los enemigos que serán afectados por el objeto.
     */
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

//POTIONS/POCIONES

/**
 * Objeto poción que cura una cantidad de vida determinada
 */
open class Potion: Item {
    companion object {
        const val POTION_HEAL = 10 //Cantidad de vida que cura la poción
    }

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Potion"
    }

    override fun desc() {
        println("\nA potion that heals 10 HP. Tastes like cough syrup.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param player El jugador afectado por el objeto.
     */
    open fun effect(player: Player) {
        println("You drink the potion.")
        Thread.sleep(100)
        player.hp += POTION_HEAL
        println("The potion healed $POTION_HEAL HP!")
        Thread.sleep(100)
    }
}

/**
 * Variante de poción que cura más vida que la normal.
 */
class HighPotion: Potion() {
    companion object {
        const val POTION_HEAL = 50
    }

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "High Potion"
    }

    override fun desc() {
        println("\nA potion that heals 50 HP. Tastes even more like cough syrup.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param player El jugador afectado por el objeto.
     */
    override fun effect(player: Player) {
        println("You drink the high potion.")
        Thread.sleep(100)
        player.hp += POTION_HEAL
        println("The high potion healed $POTION_HEAL HP!")
        Thread.sleep(100)
    }
}

/**
 * Objeto antídoto que cura al jugador del envenenamiento.
 */
class Antidote: Item {

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Antidote"
    }

    override fun desc() {
        println("\nA mysterious concoction that heals you from Poison. Tastes like soda.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param player El jugador afectado por el objeto.
     */
    fun effect(player: Player) {
        println("You drink the antidote.")
        Thread.sleep(100)
        if (player.status == Ailment.POISON) {
            player.status = Ailment.NONE
            println("You were healed from the poison!")
        }
        else {
            println("...But it did nothing.")
        }
        Thread.sleep(100)
    }
}

/**
 * Objeto que cura la parálisis.
 */
class Paralyheal: Item {

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Paralyheal"
    }

    override fun desc() {
        println("\nA weird tea brand that for some reason is able to cure paralysis. Tastes like bathwater.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param player El jugador afectado por el objeto.
     */
    fun effect(player: Player) {
        println("You drink the paralyheal.")
        Thread.sleep(100)
        if (player.status == Ailment.PARALYSIS) {
            player.status = Ailment.NONE
            println("You were healed from the paralysis!")
        }
        else {
            println("...But it did nothing.")
        }
        Thread.sleep(100)
    }
}

//TOMES

/**
 * Tomo que hace un daño determinado a un solo enemigo.
 */
open class TomeOfPower: Item {
    companion object {
        const val TOME_DAMAGE = 20 //Daño del Tomo
    }

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Tome of Power"
    }

    override fun desc() {
        println("\nA tome of ancient magic, deals 10 damage to an enemy, no matter their type.\n")
        Thread.sleep(100)
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemy El enemigo que será afectado por el objeto.
     */
    open fun effect(enemy: Enemy) {
        println("The tome shoots a magical orb!")
        Thread.sleep(100)
        enemy.hp -= TOME_DAMAGE
        println("Dealt $TOME_DAMAGE damage to $enemy!")
        Thread.sleep(100)
    }
}

/**
 * Tomo que hace daño de fuego a un enemigo en concreto.
 */
class TomeOfFire: TomeOfPower() {
    companion object {
        const val TOME_DAMAGE = 10
    }

    private val element = Element.FIRE //Elemento del Tomo

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Tome of Fire"
    }

    override fun desc() {
        println("\nA tome that casts large flames, deals 5 damage to an enemy, no matter their type.\n")
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemy El enemigo que será afectado por el objeto.
     */
    override fun effect(enemy: Enemy) {
        println("The tome casts powerful flames!")
        Thread.sleep(100)
        if (enemy.weakness == element) { //Si ele enemigo es debil a ese elemento, recibe el doble de daño
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

/**
 * Tomo que hace daño de agua a un enemigo en concreto.
 */
class TomeOfWater: TomeOfPower() {
    companion object {
        const val TOME_DAMAGE = 10
    }

    private val element = Element.WATER

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Tome of Water"
    }

    override fun desc() {
        println("\nA tome that casts many waves, deals 5 damage to an enemy, no matter their type.\n")
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemy El enemigo que será afectado por el objeto.
     */
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

/**
 * Tomo que hace daño eléctrico a un enemigo en concreto.
 */
class TomeOfLightning: TomeOfPower() {
    companion object {
        const val TOME_DAMAGE = 10
    }

    private val element = Element.ELECTRIC

    /**
     * Función que retorna el nombre del objeto.
     */
    override fun toString(): String {
        return "Tome of Lightning"
    }

    override fun desc() {
        println("\nA tome that casts sudden lightning, deals 5 damage to an enemy, no matter their type.\n")
    }

    /**
     * Función que aplica el efecto del objeto.
     *
     * @param enemy El enemigo que será afectado por el objeto.
     */
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

//BUFFS

/**
 * Objetos que no se usan durante la batalla, se obtienen de tiendas y aplican un boost o buff al jugador
 * cuando son comprados de las tiendas, se sobreescribenentre sí.
 */
interface Equipment {
    /**
     * El boost o buff que ocurre cuando el objeto es comprado de la tienda.
     *
     * @param player El jugador que será afectado.
     */
    fun equip(player: Player)

    /**
     * Muestra una descripción breve del objeto.
     */
    fun desc()
}

/**
 * Objeto equipable básico que da un boost al ataque.
 */
class IronSword: Equipment {
    companion object {
        const val WEAPON_DAMAGE = 5 //Boost de daño al equiparlo
    }

    override fun equip(player: Player) {
        player.weaponAtk = WEAPON_DAMAGE
        println("Equipped the Iron Sword!")
    }

    override fun desc() {
        println("\nAn average steel sword, gives you 5 extra damage.\n")
    }

    override fun toString(): String {
        return "Iron Sword"
    }
}

/**
 * Objeto equipable que provee un boost de daño y el elemento fuego.
 */
class FlameSword: Equipment {
    companion object {
        const val WEAPON_DAMAGE = 7
        val element = Element.FIRE //Elemento del objeto
    }

    override fun equip(player: Player) {
        player.weaponAtk = WEAPON_DAMAGE
        player.element = element
        println("Equipped the Flame Sword!")
    }

    override fun desc() {
        println("\nA powerful sword imbued in flames, gives 7 extra damage and fire attacks.\n")
    }

    override fun toString(): String {
        return "Flame Sword"
    }
}

/**
 * Objeto equipable que provee un boost de daño y el elemento fuego.
 */
class WaveSword: Equipment {
    companion object {
        const val WEAPON_DAMAGE = 6
        val element = Element.WATER
    }

    override fun equip(player: Player) {
        player.weaponAtk = WEAPON_DAMAGE
        player.element = element
        println("Equipped the Wave Sword!")
    }

    override fun desc() {
        println("\nA powerful sword with the power of the sea, gives 6 extra damage and water attacks.\n")
    }

    override fun toString(): String {
        return "Wave Sword"
    }
}

/**
 * Objeto equipable que provee un boost de daño y el elemento fuego.
 */
class ThunderSword: Equipment {
    companion object {
        const val WEAPON_DAMAGE = 8
        val element = Element.ELECTRIC
    }

    override fun equip(player: Player) {
        player.weaponAtk = WEAPON_DAMAGE
        player.element = element
        println("Equipped the Thunder Sword!")
    }

    override fun desc() {
        println("\nA powerful sword that roars like thunder, gives 8 extra damage and electric attacks.\n")
    }

    override fun toString(): String {
        return "Thunder Sword"
    }
}