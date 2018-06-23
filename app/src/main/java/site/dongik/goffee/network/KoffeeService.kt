package site.dongik.goffee.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import site.dongik.goffee.app.Config

interface KoffeeService {
    @GET("/users/{user}/devices")
    fun listDevices(@Path("user") user: String): Call<List<Device>>

    @GET("/users/{user}/sims")
    fun listSims(@Path("user") user: String): Call<List<Sim>>

    @GET("/drinks")
    fun listDrinks(): Call<List<Drink>>

    @GET("/users/{user}/followers")
    fun listFollwers(@Path("user") user: String): Call<User>

    @GET("/groups/{group}/orders")
    fun listOrders(@Path("group") group: String): Call<List<Order>>



}

data class Order(val title:String,val number:Int)
data class User(val id:String,val name:String)
data class Drink(val name:String,val price:Int)
data class Sim(val pn:String,val operator:String)
data class Device(val sn:String,val model:String)