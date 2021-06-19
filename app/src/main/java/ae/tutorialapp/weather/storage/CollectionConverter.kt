package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.DailyForeCast
import ae.tutorialapp.weather.models.HourlyForeCast
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionConverter {

    @TypeConverter
    fun fromHourlyForecastListToJson(list: List<HourlyForeCast>?): String? =
        Gson().toJson(list)

    @TypeConverter
    fun fromJsonToHourlyForecastList(json: String?): List<HourlyForeCast>? =
        Gson().fromJson(json, object : TypeToken<List<HourlyForeCast>>() {}.type)



    @TypeConverter
    fun fromDailyForecastListToJson(list: List<DailyForeCast>?): String? =
        Gson().toJson(list)


    @TypeConverter
    fun fromJsonToDailyForecastList(json: String?): List<DailyForeCast>? =
        Gson().fromJson(json, object : TypeToken<List<DailyForeCast>>() {}.type)

}