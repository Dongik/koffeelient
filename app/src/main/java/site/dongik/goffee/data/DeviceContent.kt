package site.dongik.goffee.data

import java.util.ArrayList
import java.util.HashMap

object DeviceContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Device> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Device> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
//        for (i in 1..COUNT) {
//            addItem(createDummyItem(i))
//        }

        addItem(Device("STAR","123120310","dongik","dongjin"))
    }

    fun addItem(item: Device) {
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
    data class Device(val name: String, val serialNumber: String, val owner:String, val holder: String) {
        override fun toString(): String = "$name $serialNumber owner:$owner holder:$holder"
    }
}
