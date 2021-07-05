package ae.tutorialapp.weather.repository

import ae.tutorialapp.weather.network.WeatherApi
import ae.tutorialapp.weather.storage.ForeCastDatabase

class WeatherRepo(
    private val db: ForeCastDatabase,
    private val weatherApi: WeatherApi
) {

}