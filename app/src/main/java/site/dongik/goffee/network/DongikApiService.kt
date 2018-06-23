package site.dongik.goffee.network

import android.location.Location
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import site.dongik.goffee.app.Config
import site.dongik.goffee.model.Model

interface DongikApiService {

    @GET("drinks")
    fun getDrinks(): Single<List<Model.Drink>>

    @GET("users")
    fun getUsers():Single<List<Model.User>>

    @GET("users/{user_id}/followers")
    fun getFollowersOf(@Path("user_id")id:String):Single<List<String>>

    @GET("user/{user_id}/followings")
    fun getFollowingsOf(@Path("user_id")id:String):Single<List<String>>


    @GET("orders")
    fun getOrders():Single<List<Model.Order>>

    @POST("orders")
    fun postOrder(@Body order: Model.Order)
            : Single<Model.OrderResponse>

    @POST("user/{user_id}/location")
    fun postLocation(@Path("user_id")id:String,@Body location: Location):Observable<Response>

    companion object {
        fun create(): DongikApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Config.BASE_URL)
                    .build()

            return retrofit.create(DongikApiService::class.java)
        }
    }

}