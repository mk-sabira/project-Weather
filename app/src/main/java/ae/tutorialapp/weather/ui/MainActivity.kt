package ae.tutorialapp.weather.ui


import ae.tutorialapp.weather.databinding.ActivityMainBinding
import ae.tutorialapp.weather.format
import ae.tutorialapp.weather.models.Constants
import ae.tutorialapp.weather.models.ForeCast
import ae.tutorialapp.weather.ui.rv.DailyForeCastAdapter
import ae.tutorialapp.weather.ui.rv.HourlyForeCastAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.math.roundToInt


class MainActivity: AppCompatActivity(){

    lateinit var bindingClass:ActivityMainBinding

    private lateinit var vm: MainViewModel

    private lateinit var dailyForeCastAdapter: DailyForeCastAdapter
    private lateinit var hourlyForeCastAdapter: HourlyForeCastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        vm = getViewModel ( MainViewModel::class)
        setUpViews()
        setUpRecyclerViews()
        subscribeToLiveData()
    }

    private fun setUpViews() {
        bindingClass.tvRefresh.setOnClickListener {
            vm.getWeatherFromApi()
        }
    }

    private fun setUpRecyclerViews() {
        hourlyForeCastAdapter = HourlyForeCastAdapter()
        bindingClass.rvHourlyForecast.adapter = hourlyForeCastAdapter

        dailyForeCastAdapter = DailyForeCastAdapter()
        bindingClass.rvDailyForecast.adapter = dailyForeCastAdapter

    }

    private fun subscribeToLiveData() {
        vm.getForeCastAsLive().observe(this, androidx.lifecycle.Observer {
            it?.let {
                setValuesToViews(it)
                loadWeatherIcon(it)
                setDataToRecyclerViews(it)
            }
        })

        vm._isLoading.observe(this, Observer {
            when(it){
                true -> showLoading()
                false -> hideLoading()
            }
        })
    }

    private fun setDataToRecyclerViews(it: ForeCast) {
        it.daily?.let { dailyList ->
            dailyForeCastAdapter.setItems(dailyList)
        }
        it.hourly?.let { hourlyList ->
            hourlyForeCastAdapter.setItems(hourlyList)
        }
    }

    private fun showLoading() {
        bindingClass.progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        bindingClass.progress.visibility = View.INVISIBLE
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


