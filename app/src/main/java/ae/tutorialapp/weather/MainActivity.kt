package ae.tutorialapp.weather


import ae.tutorialapp.weather.storage.ForeCastDatabase
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Observer


class MainActivity: AppCompatActivity(){


    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }



    private lateinit var tv_forecast_list: TextView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_forecast_list  = findViewById(R.id.tv_forcast_list)
        button = findViewById(R.id.buttonB)
        makeRxCall()


        db.foreCastDao().getAll().observe(this, androidx.lifecycle.Observer {

            tv_forecast_list.text = it?.toString()
        })

    }

    @SuppressLint("CheckResult")
    private fun makeRxCall() {

        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .map{
                db.foreCastDao().deleteAll()
                db.foreCastDao().insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            })
    }

//    @SuppressLint("CheckResult")
//    private fun getFromDb(){
//        db.foreCastDao()
//            .getAll()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    tv_forecast_list.text = it.toString()
//
//            },
//                {
//
//                }
//            )
//    }



}


