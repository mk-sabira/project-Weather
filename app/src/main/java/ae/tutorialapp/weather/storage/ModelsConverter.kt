package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.CurrentForeCast
import ae.tutorialapp.weather.models.ForeCast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ModelsConverter {
    fun fromCurrentForeCastToJson(foreCast: ForeCast):String =
        Gson().toJson(foreCast)

    fun fromJsonToForeCast(json: String): ForeCast =
        Gson().fromJson(json, object: TypeToken<CurrentForeCast>() {}.type)
}