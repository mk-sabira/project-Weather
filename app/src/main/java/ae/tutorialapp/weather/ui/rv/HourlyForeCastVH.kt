package ae.tutorialapp.weather.ui.rv

import ae.tutorialapp.weather.R
import ae.tutorialapp.weather.format
import ae.tutorialapp.weather.models.Constants
import ae.tutorialapp.weather.models.HourlyForeCast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

class HourlyForeCastVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindH(item: HourlyForeCast){
        itemView.run {
            val tvTime = findViewById<TextView>(R.id.tv_time)
            val tvPrecipitationHourly = findViewById<TextView>(R.id.tv_precipitation_hourly)
            val tvTempHourly = findViewById<TextView>(R.id.tv_temp_hourly)
            val ivWeatherIconItemHourly = findViewById<ImageView>(R.id.iv_weather_icon_item_hourly)

            tvTime.text = item.date?.format("hh:mm")

            item.probability?.let {
                tvPrecipitationHourly.text ="${(it * 100.0).roundToInt()}%"
            }

            tvTempHourly.text = item.temp?.roundToInt().toString()

            Glide.with(itemView.context)
                .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                .into(ivWeatherIconItemHourly)

        }
    }

    companion object{
        fun createVH(parent: ViewGroup): HourlyForeCastVH{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hourly_forecast, parent, false)

            return HourlyForeCastVH(view)
        }
    }
}