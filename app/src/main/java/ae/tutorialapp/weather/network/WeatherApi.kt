package ae.tutorialapp.weather.network

import ae.tutorialapp.weather.models.ForeCast
import retrofit2.Call
import retrofit2.http.GET


interface WeatherApi {
    @GET( "onecall?lat=23.4241&lon=53.8478&exclude=minitely&appid=c041a38e6112d2dc52f3d608ea562618&lang=en&units=metric")
    fun getweather(): Call<ForeCast>
}