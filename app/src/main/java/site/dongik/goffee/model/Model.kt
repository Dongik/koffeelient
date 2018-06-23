package site.dongik.goffee.model

import com.google.gson.annotations.SerializedName

object Model{
    data class Drink(val name: String,val price: Int)
    data class Order(var name :String,var type: String,var size: String)
    data class User(val id:String,
                    val name:String,
                    val status:String)



    data class Response(val code:Int, val text: String)

    data class FcmRequest(val to:String, val notification:FcmNotification)

    data class FcmNotification(val title:String,val body:String)
    data class FcmData(val message:String)

    data class FcmResponse(
            @SerializedName("message_id")
            val messageId:String,
            @SerializedName("error")
            val error:String)

    data class OrderResponse(val result:String)
}