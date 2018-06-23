package site.dongik.goffee.data

import java.util.ArrayList
import java.util.HashMap

object DrinkContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DrinkItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DrinkItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
//        for (i in 1..COUNT) {
//            addItem(createDummyItem(i))
//        }
        addItem(DrinkItem("아메리카노",2000))
        addItem(DrinkItem("카페라떼",3000))
        addItem(DrinkItem("아메리카노",4000))
        addItem(DrinkItem("녹차라떼",5000))

    }

    fun addItem(item: DrinkItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.name, item)
    }
//
//    private fun createDummyItem(position: Int): OrderItem {
//        return OrderItem(position.toString(), "Order " + position, makeDetails(position))
//    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DrinkItem(val name: String, val price: Int) {
        override fun toString(): String = "$name $price"
    }
}
