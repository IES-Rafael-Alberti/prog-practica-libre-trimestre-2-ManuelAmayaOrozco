/**
 * Interfaz que detalla todas los valores que tendrá un enemigo y las funciones que será capaz de hacer.
 */
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

//BASIC ENEMIES/ENEMIGOS BÁSICOS

/**
 * Enemigo identificado como Slime (Limo), el enemigo más débil y sencillo, sin habilidades especiales.
 */
class Slime: Enemy {
    companion object {
        const val BASE_HP = 10 //Vida con la que empieza el enemigo
        const val BASE_ATK = 2 //El máximo daño capáz de hacer el enemigo
        const val CHANCE_OF_ATTACK = 70 //La probabilidad que tendrá de hacer un ataque o vaguear y perder su turno.
    }

    override val type = EnemyType.NORMAL //El tipo de enemigo que es

    override val weakness = Element.FIRE //La debilidad del enemigo

    override val moneyDrop = (1..10).random() //La cantidad de dinero que puede soltar, será sumada con la de todos los otros enemigos en batalla

    override var hp = BASE_HP //La vida del enemigo que irá bajando conforme sea atacado

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Slime"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
    override fun attack(player: Player) {
        val damage = (1..BASE_ATK).random() //El umbral de daño que puede causar el enemigo
        if (player.defStatus) { //Si el jugador se está defendiendo, el daño del ataque será reducido a la mitad
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Slime -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__SLIME__")
        Thread.sleep(50)
        println("Very weak enemy, but can prove problematic when in large numbers.")
        Thread.sleep(50)
        println("WEAKNESS: FIRE.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Slime was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguear dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

/**
 * Enemigo identificado como Goblin (Trasgo), enemigo ligéramente más fuerte y con una habilidad especial.
 */
class Goblin: Enemy {
    companion object {
        const val BASE_HP = 20
        const val BASE_ATK = 3
        const val BACKSTAB_DAMAGE = 5 //Daño de la habilidad especial
        const val CHANCE_OF_ATTACK = 50
        const val CHANCE_OF_BACKSTAB = 35 //Posibilidad de realizar la habilidad especial
    }

    override val type = EnemyType.NORMAL

    override val weakness = Element.FIRE

    override val moneyDrop = (5..20).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Goblin"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Habilidad especial de Goblin, un ataque algo más poderoso que un ataque básico.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Goblin -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__GOBLIN__")
        Thread.sleep(50)
        println("Average enemy, troublesome in groups, beware their deadly backstabs.")
        Thread.sleep(50)
        println("WEAKNESS: FIRE.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Goblin was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguear dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

/**
 * Enemigo identificado como Ogre (Ogro), enemigo mucho más fuerte, pero sigue siendo básico.
 */
class Ogre: Enemy {
    companion object {
        const val BASE_HP = 45
        const val BASE_ATK = 5
        const val HEAVY_SLAM_DAMAGE = 10 //Daño ataque especial
        const val CHANCE_OF_ATTACK = 80
        const val CHANCE_OF_HEAVY_SLAM = 10 //Porcentaje de hacer ataque especial
    }

    override val type = EnemyType.NORMAL

    override val weakness = Element.FIRE

    override val moneyDrop = (20..50).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Ogre"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Ataque especial de Ogre, un ataque más devastador que el ataque normal.
     */
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

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Ogre -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__OGRE__")
        Thread.sleep(50)
        println("Strong enemies, lots of health and power, should be prioritised.")
        Thread.sleep(50)
        println("WEAKNESS: FIRE.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Ogre was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguear dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

/**
 * Enemigo identificado como Tiny Mage (Mago Enano), de dificultad media y capaz de paralizar al jugador con sus ataques eléctricos.
 */
class TinyMage: Enemy {
    companion object {
        const val BASE_HP = 15
        const val BASE_ATK = 3
        const val LIGHTNING_DAMAGE = 8 //Daño ataque especial
        const val CHANCE_OF_ATTACK = 70
        const val CHANCE_OF_LIGHTNING = 15 //Porcentaje de hacer ataque especial
        const val INFLICT_PARALYSIS = 20 //Porcentaje de ser paralizado por el ataque especial
    }

    override val type = EnemyType.NORMAL

    override val weakness = Element.WATER

    override val moneyDrop = (10..25).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Tiny Mage"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Ataque especial de Tiny Mage, de daño considerable y capaz de causar parálisis.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Tiny Mage -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__TINY MAGE__")
        Thread.sleep(50)
        println("A short fellow in mage clothes, they talk like 'mimimimimi', dislikes baths.")
        Thread.sleep(50)
        println("WEAKNESS: WATER.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Tiny Mage was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguar dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

/**
 * Enemigo identificado como Mud-Face (Cara-Barro), de dificultad media y capaz de envenenar al jugador.
 */
class MudFace: Enemy {
    companion object {
        const val BASE_HP = 25
        const val BASE_ATK = 5
        const val SPIT_DAMAGE = 7 //Daño ataque especial
        const val CHANCE_OF_ATTACK = 70
        const val CHANCE_OF_SPIT = 25 //Porcentaje de hacer el ataque especial
        const val INFLICT_POISON = 50 //Probabilidad de envenenar al jugador
    }

    override val type = EnemyType.NORMAL

    override val weakness = Element.WATER

    override val moneyDrop = (10..25).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Mud-Face"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Ataque especial de Mud-Face, escupe al jugador, causando daño considerable con la probabilidad de envenenar.
     *
     * @param player El jugador que será atacado.
     */
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

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Mud-Face -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__MUD-FACE__")
        Thread.sleep(50)
        println("A creature made out of mud, their toxic spit can poison you.")
        Thread.sleep(50)
        println("WEAKNESS: WATER.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Mud-Face was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguar dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

/**
 * Enemigo identificado como Ghost (Fantasma), sencillo, pero dificil de atacar gracias a su inmunidad a ataques básicos.
 */
class Ghost: Enemy {
    companion object {
        const val BASE_HP = 10
        const val BASE_ATK = 3
        const val CHANCE_OF_ATTACK = 66
    }

    override val type = EnemyType.UNDEAD //UNDEAD/NO MUERTO ignoran los ataques normales y las bombas que no sean elementales.

    override val weakness = Element.ELECTRIC

    override val moneyDrop = (10..15).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Ghost"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Ghost -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__GHOST__")
        Thread.sleep(50)
        println("A silly round ghost, intangible to normal attacks, but elemental attacks will do the trick.")
        Thread.sleep(50)
        println("WEAKNESS: ELECTRIC.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Ghost was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguar dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

//BOSSES/JEFES

/**
 * Jefe opcional Siren (Sirena), encontrada únicamente en un evento, de dificultad alta y capaz de causar sueño, además de tener dos habilidades especiales.
 */
class Siren: Enemy {
    companion object {
        const val BASE_HP = 50
        const val BASE_ATK = 7
        const val SCREECH_DAMAGE = 10 //Daño ataque especial
        const val CHANCE_OF_ATTACK = 80
        const val CHANCE_OF_SCREECH = 40 //Porcentaje de hacer ataque especial
        const val CHANCE_OF_SONG = 50 //Porcentaje de usar habilidad especial
        const val INFLICT_SLEEP = 35 //Porcentaje de inflijir sueño
    }

    override val type = EnemyType.BOSS //No se puede huir de un combate contra un jefe

    override val weakness = Element.ELECTRIC

    override val moneyDrop = (50..85).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Siren"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
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

    /**
     * Ataque especial de Siren que causa más daño que el ataque básico.
     *
     * @param player El jugador que será atacado.
     */
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

    /**
     * Habilidad especial de Siren, capaz de hacer que el jugador se duerma en combate. No causa daño.
     *
     * @param player El jugador que se verá afectado.
     */
    private fun sing(player: Player) {
        val ailment = randPercentage(INFLICT_SLEEP)
        if (player.defStatus) { //Si el jugador se defiende, el ataque no tendrá efecto.
            println("You covered your ears just in time!")
        }
        else {
            if (ailment) {
                player.status = Ailment.SLEEP
                println("You fell asleep!")
            }
            else {
                println("You managed to avoid falling asleep!")
            }
        }
    }

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Siren -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__SIREN__")
        Thread.sleep(50)
        println("A powerful songstress that disguises as a woman to lure in adventurers, very dangerous.")
        Thread.sleep(50)
        println("WEAKNESS: ELECTRIC.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Siren was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguar dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
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

//FINAL BOSS/JEFE FINAL

/**
 * El villano principal y último oponente de la aventura, el enemigo más complejo y poderoso de todos.
 * Es capaz de cambiar su elemento durante el combate y utilizar diferentes ataques dependiendo de su elemento
 * actual, su debilidad también cambiará y será la misma que la de su elemento actual.
 */
class EvilWizard: Enemy {
    companion object {
        const val BASE_HP = 100
        const val BASE_ATK = 10
        const val FIRE_DAMAGE = 10 //Daño ataque especial 1
        const val WATER_DAMAGE = 10 //Daño ataque especial 2
        const val ELEC_DAMAGE = 8 //Daño ataque especial 3
        const val CHANCE_OF_ATTACK = 80
        const val CHANCE_OF_MAGIC_ATTACK = 40 //Porcentaje de hacer ataque especial
        const val CHANCE_OF_ELE_CHANGE = 30 //Porcentaje de realizar cambio de elemento
        const val CHANCE_OF_ELE_CHANGE_FIRE = 30 //Porcentaje de que el elemento nuevo sea fuego
        const val CHANCE_OF_ELE_CHANGE_WATER = 30 //Porcentaje de que el elemento nuevo sea agua
        const val INFLICT_PARALYSIS = 25 //Probabilidad de infligir parálisis
    }

    override val type = EnemyType.BOSS

    override var weakness = Element.NONE //Empieza no teniendo debilidad, pero cambia conforme sigue el combate

    override val moneyDrop = (50..85).random()

    override var hp = BASE_HP

    /**
     * Función que retorna el nombre del enemigo cuando sea llamada
     */
    override fun toString(): String {
        return "Evil Wizard"
    }

    /**
     * Función que permite al enemigo atacar al jugador.
     *
     * @param player El jugador que será atacado
     */
    override fun attack(player: Player) {
        val damage = (5..BASE_ATK).random()
        if (player.defStatus) {
            player.hp -= (damage / 2)
            println("Took ${damage / 2} damage!")
        }
        else {
            player.hp -= damage
            println("Took $damage damage!")
        }
    }

    /**
     * Habilidad especial en la que Evil Wizard cambia su elemento y debilidad a otro.
     */
    private fun eleChange() {
        val change1 = randPercentage(CHANCE_OF_ELE_CHANGE_FIRE)
        if (change1) {
            if (weakness == Element.FIRE) {
                val change2 = randPercentage(CHANCE_OF_ELE_CHANGE_WATER)
                if (change2) {
                    if (weakness == Element.WATER) {
                        println("The Evil Wizard changes his element to ELEC!")
                        Thread.sleep(100)
                        weakness = Element.ELECTRIC
                    }
                    else {
                        println("The Evil Wizard changes his element to WATER!")
                        Thread.sleep(100)
                        weakness = Element.WATER
                    }
                }
            }
            else {
                println("The Evil Wizard changes his element to FIRE!")
                Thread.sleep(100)
                weakness = Element.FIRE
            }
        }
        else {
            val change2 = randPercentage(CHANCE_OF_ELE_CHANGE_WATER)
            if (change2) {
                if (weakness == Element.WATER) {
                    println("The Evil Wizard changes his element to ELEC!")
                    Thread.sleep(100)
                    weakness = Element.ELECTRIC
                }
                else {
                    println("The Evil Wizard changes his element to WATER!")
                    Thread.sleep(100)
                    weakness = Element.WATER
                }
            }
            else {
                println("The Evil Wizard changes his element to ELEC!")
                Thread.sleep(100)
                weakness = Element.ELECTRIC
            }
        }
    }

    /**
     * Ataque especial de Evil Wizard, cambia dependiendo de su debilidad actual, haciendo más o menos daño,
     * siendo capaz de sobrepasar las defensas del jugador e incluso pudiendo inflijir parálisis.
     *
     * @param player El jugador que será atacado
     */
    private fun magicAttack(player: Player) {
        if (weakness == Element.FIRE) {
            val damage = (7..FIRE_DAMAGE).random()
            if (player.defStatus) {
                player.hp -= (damage / 2)
                println("Took ${damage / 2} damage!")
            }
            else {
                player.hp -= damage
                println("Took $damage damage!")
            }
        }
        else if (weakness == Element.WATER) {
            val damage = (3..WATER_DAMAGE).random()
            player.hp -= damage
            println("Took $damage damage!")
        }
        else if (weakness == Element.ELECTRIC) {
            val damage = (1..ELEC_DAMAGE).random()
            val ailment = randPercentage(INFLICT_PARALYSIS)
            if (player.defStatus) {
                player.hp -= (damage / 2)
                println("Took ${damage / 2} damage!")
            }
            else {
                if (ailment) {
                    player.hp -= damage
                    println("Took $damage damage!")
                    player.status = Ailment.PARALYSIS
                    println("You were paralyzed!")
                }
                else {
                    player.hp -= damage
                    println("Took $damage damage!")
                }
            }
        }
        else {
            val damage = (7..BASE_ATK).random()
            if (player.defStatus) {
                player.hp -= (damage / 2)
                println("Took ${damage / 2} damage!")
            }
            else {
                player.hp -= damage
                println("Took $damage damage!")
            }
        }
    }

    /**
     * Función que muestra el nombre del enemigo y su vida máxima y restante, usado durante el turno del jugador
     * para mantenerlo informado de su progreso en combate.
     */
    override fun desc(): String {
        return "Evil Wizard -> HP $hp/$BASE_HP"
    }

    /**
     * Función que muestra una descripción detallada del enemigo tras usar el objeto Scanner. Informa al jugador
     * de la debilidad del enemigo tras una breve descripción.
     */
    override fun scan() {
        println("\n__EVIL WIZARD__")
        Thread.sleep(50)
        println("The ultimate evil, his fancy tricolored robes allow him to use all elements, but they become vulnerable to the same element in return.")
        Thread.sleep(50)
        println("WEAKNESS: ???.\n")
        Thread.sleep(100)
    }

    /**
     * Función que avisa al jugador cuando un enemigo es derrotado en combate.
     */
    override fun defeat() {
        println("Evil Wizard was defeated!")
    }

    /**
     * Función que hace que el enemigo elija entre atacar o vaguar dependiendo de su probabilidad
     * de ataque, si un enemigo tiene ataques o habilidades especiales también se elegirán entre
     * ellas.
     *
     * @param player El jugador que será atacado.
     */
    override fun action(player: Player) {
        val result1 = randPercentage(CHANCE_OF_ATTACK)
        if (result1) {
            val result2 = randPercentage(CHANCE_OF_ELE_CHANGE)
            if (result2) {
                val result3 = randPercentage(CHANCE_OF_MAGIC_ATTACK)
                if (result3) {
                    if (weakness == Element.FIRE) {
                        println("The evil wizard casts massive pillars of fire!")
                        magicAttack(player)
                    }
                    else if (weakness == Element.WATER) {
                        println("The evil wizard summons a medium-sized tsunami!")
                        magicAttack(player)
                    }
                    else if (weakness == Element.ELECTRIC) {
                        println("The evil wizard shoots bolts from his fingers!")
                        magicAttack(player)
                    }
                    else {
                        println("The evil wizard shoots a slightly fancier orb of darkness!")
                        magicAttack(player)
                    }
                }
                else {
                    println("The evil wizard changes his element!")
                    eleChange()
                }
            }
            else {
                println("The evil wizard shoots an orb of darkness at you!")
                attack(player)
            }
        }
        else {
            println("The evil wizard shows off his cool robes while posing!")
        }
    }
}