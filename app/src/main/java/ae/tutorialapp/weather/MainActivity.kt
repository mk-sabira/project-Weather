package ae.tutorialapp.weather


import ae.tutorialapp.weather.databinding.ActivityMainBinding
import ae.tutorialapp.weather.storage.ForeCastDatabase
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Observer
import kotlin.math.roundToInt


class MainActivity: AppCompatActivity(){

    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }
    lateinit var bindingClass:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        getWeatherFromApi()

        subscribeToLiveData()

    }

    private fun subscribeToLiveData() {
        db.foreCastDao().getAll().observe(this, androidx.lifecycle.Observer {
            it?.let {
                bindingClass.tvTemperature.text = it.current?.temp?.roundToInt().toString()
                bindingClass.tvDate.text = it.current?.date.format()
                bindingClass.tvTempMax.text = it.daily?.get(0)?.temp?.max?.roundToInt()?.toString()
                bindingClass.tvTempMin.text = it.daily?.get(0)?.temp?.min?.roundToInt()?.toString()
                bindingClass.tvFeelsLike.text = it.current?.feels_like?.roundToInt()?.toString()
                bindingClass.tvWeather.text = it.current?.weather?.get(0)?.description
                bindingClass.tvSunrise.text = it.current?.sunrise.format("hh:mm")
                bindingClass.tvSunset.text = it.current?.sunset.format("hh:mm")
                bindingClass.tvHumidity.text = "${it.current?.humidity?.toString()} %"

                val ivIcon = bindingClass.ivWeatherIcon
                it.current?.weather?.get(0)?.icon?.let { icon ->
                    Glide.with(this)
                        .load("https://openweathermap.org/img/wn/${icon}@2x.png")
                        .into(ivIcon)
                }

            }

        })
    }

    private fun getWeatherFromApi() {
        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .map{
                db.foreCastDao().insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({},
                {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            })
    }


}


