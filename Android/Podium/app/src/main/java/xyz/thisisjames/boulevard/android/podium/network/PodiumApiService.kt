package xyz.thisisjames.boulevard.android.podium.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import xyz.thisisjames.boulevard.android.podium.models.OneSubject
import xyz.thisisjames.boulevard.android.podium.models.ResponseAS
import xyz.thisisjames.boulevard.android.podium.models.Session
import java.util.concurrent.TimeUnit


//base url pointing to render service with the podium base endpoint added
private const val BASE_URL = "https://podiumbyblvrd.onrender.com/podium/"

//Build the Moshi object with kotlin adapter factory that retrofit will be using
private val moshi =
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


private val okHttpClient = OkHttpClient
    .Builder()
    .readTimeout(25, TimeUnit.SECONDS)
    .connectTimeout(25, TimeUnit.SECONDS)
    .build()

// Create retrofit object with the Moshi converter
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).
    baseUrl(BASE_URL).client(okHttpClient).build()


/**
 * A public interface that exposes the endpoint methods
 */
interface PodiumApiService {
    /**
     * Returns a all subjects for which there are test available and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "allsubjects" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("allsubjects")
    suspend fun getAllSubjects(): ResponseAS


    @GET("onesubject")
    suspend fun getOneSubject() : OneSubject

   // @GET("getsession")
    @HTTP(method = "POST", path = "getsession", hasBody = true)
    suspend fun getSession( @Body requestBody : ExamRequest) : Session


}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object PodiumApi {
    val retrofitService: PodiumApiService by lazy {
        retrofit.create(PodiumApiService::class.java)
    }
}
