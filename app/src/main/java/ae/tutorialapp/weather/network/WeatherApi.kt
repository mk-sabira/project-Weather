package ae.tutorialapp.weather.network

import ae.tutorialapp.weather.models.ForeCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET( "onecall?lat=23.4241&lon=53.8478&exclude=minitely&appid=c041a38e6112d2dc52f3d608ea562618&lang=en&units=metric")
    fun getweather(): Call<ForeCast>


    @GET("onecall")
    fun fetWeatherUsingQuerry(
        @Query("lat") lat: Double = 23.4241,
        @Query("lon") lon: Double = 53.8478,
        @Query("exclude") exclude: String = "minutely",
        @Query("appid") appid: String = "c041a38e6112d2dc52f3d608ea562618",
        @Query("lang") lang: String = "en",
        @Query("units") units: String = "metric"
    ): Call<ForeCast>
}