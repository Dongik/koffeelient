package site.dongik.goffee.data

import java.util.ArrayList
import java.util.HashMap

object SimContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<SimItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, SimItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
//        for (i in 1..COUNT) {
        addItem(SimItem("DE VDF","+491023123104231","dongik","dongjin"))
        addItem(SimItem("DE VDF","+491023123151020","dongik","dongik"))
        addItem(SimItem("DE VDF","+491023123324025","dongik","dongik"))
        addItem(SimItem("DE VDF","+491023123134022","dongik","dongik"))
//
    }

    private fun addItem(item: SimItem) {
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
    data class SimItem(val name: String, val number: String, val owner: String,val holder:String) {
        override fun toString(): String = "$name $number owner:$owner holder:$holder"
    }
}
