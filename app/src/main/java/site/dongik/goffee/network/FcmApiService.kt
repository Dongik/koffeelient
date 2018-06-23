package site.dongik.goffee.network

import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import site.dongik.goffee.app.Config
import site.dongik.goffee.model.Model
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


interface FcmApiService {
    @Headers(
            "Content-Type:application/json","Authorization",
            "key="+ Config.FCM_API_KEY)
    @POST("send")
    fun postFcmRequest(@Body fcmRequest: Model.FcmRequest)
            : Observable<Model.FcmResponse>

    companion object {
        fun create(): FcmApiService {
            val OkHttpClient = OkHttpClient.Builder()


            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Config.FCM_BASE_URL)
//                    .client()
                    .build()
            return retrofit.create(FcmApiService::class.java)
        }
    }
}