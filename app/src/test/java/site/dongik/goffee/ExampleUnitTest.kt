package site.dongik.goffee

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await
import site.dongik.goffee.network.KoffeeService

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var service:KoffeeService

    @Before
    internal fun setUp(){
        val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(KoffeeService::class.java)
    }

    @Test
    internal fun callServiceWithCoroutine(){
        runBlocking {
            val drinks = service.listDrinks().await()
            drinks.forEach(::println)
//            repos.forEach(::println)
        }

    }

}
