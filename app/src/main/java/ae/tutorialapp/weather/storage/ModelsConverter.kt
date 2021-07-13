package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.CurrentForeCast
import ae.tutorialapp.weather.models.ForeCast
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ModelsConverter {
    @TypeConverter
    fun fromCurrentForeCastToJson(foreCast: CurrentForeCast?):String? =
        Gson().toJson(foreCast)


    @TypeConverter
    fun fromJsonToForeCast(json: String?): CurrentForeCast? =
        Gson().fromJson(json, object: TypeToken<CurrentForeCast>() {}.type)
}