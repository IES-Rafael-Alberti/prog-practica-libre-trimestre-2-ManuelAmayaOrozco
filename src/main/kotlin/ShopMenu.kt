/**
 * Clase que visualiza la tienda y su menú, creado especificamente para el evento Tienda.
 */
class ShopMenu {

    companion object {
        val itemList = listOf<Pair<Item,Int>>(Scanner() to 15, //Lista de todos los objetos y sus precios
                                              Potion() to 10,
                                              HighPotion() to 20,
                                              Bomb() to 15,
                                              FireBomb() to 20,
                                              WaterBomb() to 20,
                                              ShockBomb() to 20,
                                              Antidote() to 10,
                                              Paralyheal() to 10,
                                              TomeOfPower() to 25,
                                              TomeOfFire() to 25,
                                              TomeOfWater() to 25,
                                              TomeOfLightning() to 25)

        val equipList = listOf<Pair<Equipment,Int>>(IronSword() to 20, //Lista de todos los objetos equipables/no usables en batalla y sus precios
                                                    FlameSword() to 30,
                                                    WaveSword() to 25,
                                                    ThunderSword() to 35)

        const val AMOUNT_SHOP_ITEMS = 6 //Cantidad de objetos que puede haber en la tienda
        const val AMOUNT_SHOP_EQUIPMENT = 2 //Cantidad de objetos equipables que puede haber en la tienda
    }

    /**
     * Función que simula el menú de la tienda en sí y actualiza al jugador cuando compra un objeto.
     *
     * @param player El jugador que compra en la tienda.
     */
    fun shop(player: Player) {
        val items = generateShopItems() //Llamada a la función para elegir los objetos que estarán en la tienda.
        val equips = generateShopEquips() //Llamada a la función para elegir los objetos equipables que estarán en la tienda.
        var shopping = true //Mantiene el bucle de la tienda

        println("You arrive at a shop!")
        println("Shopkeep: 'Welcome! What can I get ya?'")

        while(shopping) {
            println("1. BUY ITEMS     2. BUY EQUIPMENT     3. LEAVE")

            var choice: Int? //Elección de lo que quieras comprar o salir de la tienda
            do {
                choice = readln().toIntOrNull()
                if (choice == null || choice <= 0 || choice >= 4) {
                    println("Invalid option, try again.")
                }
            } while (choice == null || choice <= 0 || choice >= 4)

            Thread.sleep(100)

            when(choice) {

                1 -> { //COMPRAR OBJETOS
                    println("Shopkeep: 'Which item do ya wanna buy?'\n")

                    var itemCount = 1 //Muestra todos los objetos de la tienda
                    for (item in items) {
                        println("$itemCount. ${item.first} - ${item.second} Coins")
                        itemCount++
                    }
                    println("$itemCount. Cancel\n")

                    var browseItems = true //Bucle menú compra objetos

                    while(browseItems) {
                        println("Select an item.")
                        var itemChoice: Int? //Elige un objeto o la última opción para cancelar
                        do {
                            itemChoice = readln().toIntOrNull()
                            if (itemChoice == null || itemChoice <= 0 || itemChoice > (items.size + 1)) {
                                println("Invalid choice, try again.")
                            }
                        } while (itemChoice == null || itemChoice <= 0 || itemChoice > (items.size + 1))

                        if (itemChoice != items.size) {
                            if (player.money < items[itemChoice].second) { //Dinero insuficiente para comprar
                                println("Shopkeep: 'Sorry pal, you don't have enough cash!'")
                            }
                            else {
                                println("Shopkeep: 'Thanks for the purchase!'")
                                player.getItem(items[itemChoice].first) //Se añade el objeto al inventario
                                player.money -= items[itemChoice].second //Se resta el precio
                            }
                        }
                        else { //Salir del menú compra objetos
                            browseItems = false
                        }
                    }
                }

                2 -> { //COMPRA EQUIPABLES
                    println("Shopkeep: 'Which equipment do ya wanna buy?'\n")

                    var equipCount = 1 //Muestra todos los equipables
                    for (equip in equips) {
                        println("$equipCount. ${equip.first} - ${equip.second} Coins")
                        equipCount++
                    }
                    println("$equipCount. Cancel\n")

                    var browseEquips = true //Mantiene el bucle del menú equipables

                    while(browseEquips) {
                        println("Select an equipment.")
                        var equipChoice: Int? //Elige un equipable o la última opción para cancelar
                        do {
                            equipChoice = readln().toIntOrNull()
                            if (equipChoice == null || equipChoice <= 0 || equipChoice > (items.size + 1)) {
                                println("Invalid choice, try again.")
                            }
                        } while (equipChoice == null || equipChoice <= 0 || equipChoice > (items.size + 1))

                        if (equipChoice != equips.size) {
                            if (player.money < equips[equipChoice].second) { //Dinero insuficiente para comprar
                                println("Shopkeep: 'Sorry pal, you don't have enough cash!'")
                                Thread.sleep(100)
                            }
                            else {
                                println("Shopkeep: 'Thanks for the purchase!'")
                                Thread.sleep(100)
                                equips[equipChoice].first.equip(player) //Aplica el buff del equipable
                                player.money -= equips[equipChoice].second //Resta precio
                            }
                        }
                        else { //Salir del menú
                            browseEquips = false
                        }
                    }
                }

                3 -> { //SALIR DE LA TIENDA
                    println("Shopkeep: 'Be seein' you around pal!'")
                    println("You left the shop...")
                    Thread.sleep(100)
                    shopping = false
                }
            }
        }
    }

    /**
     * Función que genera una lista de objetos de una lista para ser usadas en la tienda.
     * Cambia cada vez que se accede a una tienda nueva.
     *
     * @return La lista de objetos disponisbles actualmente en la tienda.
     */
    private fun generateShopItems(): List<Pair<Item,Int>> {
        val shopItems = mutableListOf<Pair<Item,Int>>()
        var count = 1
        while (count != AMOUNT_SHOP_ITEMS) {
            shopItems.add(itemList.random())
            count++
        }
        return shopItems
    }

    /**
     * Función que genera una lista de equipables de una lista para ser usadas en la tienda.
     * Cambia cada vez que se accede a una tienda nueva.
     *
     * @return La lista de equipables disponisbles actualmente en la tienda.
     */
    private fun generateShopEquips(): List<Pair<Equipment,Int>> {
        val shopEquips = mutableListOf<Pair<Equipment,Int>>()
        var count = 1
        while (count != AMOUNT_SHOP_EQUIPMENT) {
            shopEquips.add(equipList.random())
            count++
        }
        return shopEquips
    }
}