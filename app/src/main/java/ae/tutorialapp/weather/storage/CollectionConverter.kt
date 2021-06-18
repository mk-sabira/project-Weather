package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.HourlyForeCast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionConverter {


    fun fromHourlyForecastListToJson(list: List<HourlyForeCast>): String =
        Gson().toJson(list)

    fun fromJsonToHourlyForecastList(json: String): List<HourlyForeCast> =
        Gson().fromJson(json, object : TypeToken<List<HourlyForeCast>>() {}.type)



    fun fromDailyForecastListToJson(list: List<HourlyForeCast>): String =
        Gson().toJson(list)

    fun fromJsonToDailyForecastList(json: String): List<HourlyForeCast> =
        Gson().fromJson(json, object : TypeToken<List<HourlyForeCast>>() {}.type)

}