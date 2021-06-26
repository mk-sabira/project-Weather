package ae.tutorialapp.weather.ui.rv

import ae.tutorialapp.weather.R
import ae.tutorialapp.weather.format
import ae.tutorialapp.weather.models.Constants
import ae.tutorialapp.weather.models.DailyForeCast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

class DailyForeCastVH(itemView: View):RecyclerView.ViewHolder(itemView){

    fun bind(item:DailyForeCast){
        itemView.run {
            val tv_weekday = findViewById<TextView>(R.id.tv_weekday)
            val tv_precipitation = findViewById<TextView>(R.id.tv_precipitation)
            val tv_temp_max = findViewById<TextView>(R.id.tv_temp_max_item)
            val tv_temp_min = findViewById<TextView>(R.id.tv_temp_min_item)
            val iv_weather_icon = findViewById<ImageView>(R.id.iv_weather_icon_item)

            tv_weekday.text = item.date.format("dd/MM")

            item.probability?.let {
                tv_precipitation.text = "${(it * 1000.0).roundToInt()}%"
            }
            tv_temp_max.text = item.temp?.max?.roundToInt().toString()
            tv_temp_min.text = item.temp?.min?.roundToInt().toString()

            Glide.with(itemView.context)
                .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                .into(iv_weather_icon)

        }
    }

    companion object{
        fun create(parent: ViewGroup): DailyForeCastVH{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_daily_forecast, parent, false)

            return DailyForeCastVH(view)
        }
    }
}