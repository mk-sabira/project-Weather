package ae.tutorialapp.weather.repository

import ae.tutorialapp.weather.models.ForeCast
import ae.tutorialapp.weather.network.WeatherApi
import ae.tutorialapp.weather.storage.ForeCastDatabase
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRepo(
    private val db: ForeCastDatabase,
    private val weatherApi: WeatherApi
) {

    fun getWeatherFromApi(): Single<ForeCast> {
        return weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .map{
                db.foreCastDao().insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getForeCastFromDbAsLive() = db.foreCastDao().getAll()
}