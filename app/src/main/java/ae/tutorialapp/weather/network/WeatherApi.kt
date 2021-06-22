package ae.tutorialapp.weather.network

import ae.tutorialapp.weather.models.ForeCast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("onecall")
    fun fetchWeather(
        @Query("lat") lat: Double = 23.4241,
        @Query("lon") lon: Double = 53.8478,
        @Query("exclude") exclude: String = "minutely",
        @Query("appid") appid: String = "c041a38e6112d2dc52f3d608ea562618",
        @Query("lang") lang: String = "en",
        @Query("units") units: String = "metric"
    ): Single<ForeCast>
}