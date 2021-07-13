package ae.tutorialapp.weather.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ForeCast(
    var id: Long? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    @PrimaryKey
    var timezone: String,
    var timezone_offset : Long? = null,
    var current: CurrentForeCast? = null,
    var hourly: List<HourlyForeCast>? = null,
    var daily: List<DailyForeCast>? = null
)

data class CurrentForeCast(
    @SerializedName("dt")
    var date: Long? = null,
    var sunrise: Long? = null,
    var sunset: Long? = null,
    var temp: Double? = null,
    var feels_like: Double? = null,
    var humidity: Int? = null,
    var weather: List<Weather>
)

data class Weather(
    var id: Long? = null,
    var description: String? = null,
    var icon: String? = null
)

data class HourlyForeCast(
    @SerializedName("dt")
    var date: Long? = null,
    var temp: Double? = null,
    var weather: List<Weather>,
    @SerializedName("pop")
    var probability: Double? = null
)

data class DailyForeCast(
    @SerializedName("dt")
    var date: Long? = null,
    var temp: Temperature? = null,
    var weather: List<Weather>? = null,
    @SerializedName("pop")
    var probability: Int? = null
)

data class Temperature(
    var min: Double? = null,
    var max: Double? =null
)
