package site.dongik.goffee.data

import java.util.ArrayList
import java.util.HashMap

object OrderContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<OrderItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, OrderItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
//        for (i in 1..COUNT) {
//            addItem(createDummyItem(i))
//        }
        addItem(OrderItem("아이스 아메리카노",2,"주문자: 이동익"))
        addItem(OrderItem("아이스 카페라떼",2,"주문자: 이동익, 이동진"))
        addItem(OrderItem("아메리카노",2,"주문자: 이동익, 이돈진"))
        addItem(OrderItem("아이스 녹차 라떼",2,"주문자: 이동익, 김대하"))

    }

    private fun addItem(item: OrderItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.title, item)
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
    data class OrderItem(val title: String, val count: Int, val details: String) {
        override fun toString(): String = "$title $count $details"
    }
}
