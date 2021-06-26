package ae.tutorialapp.weather.ui


import ae.tutorialapp.weather.network.WeatherClient
import ae.tutorialapp.weather.databinding.ActivityMainBinding
import ae.tutorialapp.weather.format
import ae.tutorialapp.weather.models.Constants
import ae.tutorialapp.weather.models.ForeCast
import ae.tutorialapp.weather.models.HourlyForeCast
import ae.tutorialapp.weather.storage.ForeCastDatabase
import ae.tutorialapp.weather.ui.rv.DailyForeCastAdapter
import ae.tutorialapp.weather.ui.rv.HourlyForeCastAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.roundToInt

@SuppressLint("CheckResult")
class MainActivity: AppCompatActivity(){

    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }
    lateinit var bindingClass:ActivityMainBinding

    private lateinit var dailyForeCastAdapter: DailyForeCastAdapter
    private lateinit var hourlyForeCastAdapter: HourlyForeCastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        setUpViews()
        setUpRecyclerView()
        setUpRecyclerView2()
        getWeatherFromApi()
        subscribeToLiveData()

    }

    private fun setUpViews() {
        bindingClass.tvRefresh.setOnClickListener {
            showLoading()
            getWeatherFromApi()
        }
    }

    private fun setUpRecyclerView() {
        hourlyForeCastAdapter = HourlyForeCastAdapter()
        bindingClass.rvHourlyForecast.adapter = hourlyForeCastAdapter

    }

    private fun setUpRecyclerView2() {
        dailyForeCastAdapter = DailyForeCastAdapter()
        bindingClass.rvDailyForecast.adapter = dailyForeCastAdapter
    }

    private fun showLoading() {
        bindingClass.progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        bindingClass.progress.visibility = View.GONE
    }

    private fun getWeatherFromApi() {
        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .map{
                db.foreCastDao().insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                hideLoading()
            },
                {
                    hideLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
    }

    private fun subscribeToLiveData() {
        db.foreCastDao().getAll().observe(this, androidx.lifecycle.Observer {
            it?.let {
                setValuesToViews(it)
                loadWeatherIcon(it)

                it.daily?.let { dailyList ->
                    dailyForeCastAdapter.setItems(dailyList)
                }
                it.hourly?.let { hourlyList ->
                    hourlyForeCastAdapter.setItems(hourlyList)
                }

            }

        })
    }

    private fun setValuesToViews(it: ForeCast) {
        bindingClass.tvTemperature.text = it.current?.temp?.roundToInt().toString()
        bindingClass.tvDate.text = it.current?.date.format()
        bindingClass.tvTempMax.text = it.daily?.get(0)?.temp?.max?.roundToInt()?.toString()
        bindingClass.tvTempMin.text = it.daily?.get(0)?.temp?.min?.roundToInt()?.toString()
        bindingClass.tvFeelsLike.text = it.current?.feels_like?.roundToInt()?.toString()
        bindingClass.tvWeather.text = it.current?.weather?.get(0)?.description
        bindingClass.tvSunrise.text = it.current?.sunrise.format("hh:mm")
        bindingClass.tvSunset.text = it.current?.sunset.format("hh:mm")
        bindingClass.tvHumidity.text = "${it.current?.humidity?.toString()} %"
    }

    private fun loadWeatherIcon(it: ForeCast) {
        val ivIcon = bindingClass.ivWeatherIcon
        it.current?.weather?.get(0)?.icon?.let { icon ->
            Glide.with(this)
                .load("${Constants.iconUri}${icon}${Constants.iconFormat}")
                .into(ivIcon)
        }
    }

}


