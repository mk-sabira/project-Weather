package ae.tutorialapp.weather

import ae.tutorialapp.weather.models.CurrentForeCast
import ae.tutorialapp.weather.models.ForeCast
import ae.tutorialapp.weather.models.Weather
import ae.tutorialapp.weather.storage.ForeCastDatabase
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity: AppCompatActivity(){
    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }

    private lateinit var et_id: EditText
    private lateinit var et_lat: EditText
    private lateinit var et_long: EditText
    private lateinit var et_description: EditText

    private lateinit var btn_insert: Button
    private lateinit var btn_uptade: Button
    private lateinit var btn_delete: Button
    private lateinit var btn_query: Button
    private lateinit var btn_query_get_all: Button

    private lateinit var tv_forecast_list: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       setUp()

    }

    private fun setUp() {
        btn_insert = findViewById(R.id.btnInsert)
        btn_uptade = findViewById(R.id.btnUptade)
        btn_delete = findViewById(R.id.btnDelete)
        btn_query_get_all = findViewById(R.id.btnQueryGetAll)
        btn_query = findViewById(R.id.btnQuery)

        tv_forecast_list = findViewById(R.id.tv_forcast_list)

        btn_insert.setOnClickListener {
            db.foreCastDao()
                .insert(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
        }
        btn_uptade.setOnClickListener {
            db.foreCastDao()
                .update(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
        }

        btn_delete.setOnClickListener {
            db.foreCastDao()
                .delete(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
        }

        btn_query_get_all.setOnClickListener {
            db.foreCastDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        var text = ""

                        it.forEach{
                            text = it.toString()
                        }
                        tv_forecast_list.text = text
                    },
                    {

                    }
                )
        }
        btn_query.setOnClickListener {
            db.foreCastDao()
                .deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {

                    },
                    {

                    }
                )
        }

    }

    private fun getForecastFromInput(): ForeCast{
        et_id = findViewById(R.id.etId)
        et_lat = findViewById(R.id.etLat)
        et_long = findViewById(R.id.etLong)
        et_description = findViewById(R.id.etDescription)

        val id = et_id.text?.toString().takeIf { !it.isNullOrEmpty() }?.toLong()
        val lat = et_lat.text?.toString().takeIf { !it.isNullOrEmpty() }?.toDouble()
        val long = et_long.text?.toString().takeIf { !it.isNullOrEmpty() }?.toDouble()
        val description = et_description?.text.toString()
        val current = CurrentForeCast(weather = listOf(Weather(description = description)))

        return ForeCast(id = id, lat = lat, lon = long, current = current)
    }





    @SuppressLint("CheckResult")
    private fun makeRxCall() {

        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({

            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            })
    }



}


