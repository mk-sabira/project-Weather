package ae.tutorialapp.weather


import ae.tutorialapp.weather.models.ForeCast
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    lateinit var btn_start: Button
    lateinit var btn_showToast: Button
    lateinit var textView: TextView
    lateinit var textView2: TextView
    lateinit var tv_counter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       textView = findViewById(R.id.textView)
       textView2 = findViewById(R.id.textView2)

        setUp()

    }

    private fun setUp() {
       btn_start = findViewById(R.id.btn_start)
       btn_showToast = findViewById(R.id.btn_showToast)

        btn_start.setOnClickListener {
//            hardWork()
            makeRxCall()
        }
        btn_showToast.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("CheckResult")
    private fun makeRxCall() {

        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                textView.text = it.current?.temp?.toString()
                textView2.text = it.current?.weather?.get(0)?.description
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            })
    }

    private fun hardWork() {
        tv_counter = findViewById(R.id.tv_counter)

        val observable = Observable.create<String>{ emitor ->
            Log.d(TAG,"${Thread.currentThread().name} starting emitting")
            Thread.sleep(3000)
            emitor.onNext("Hi!")
            Thread.sleep(1000)
            emitor.onNext("Abu Dhabi")
            emitor.onComplete()
        }

        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: String) {
                Log.d(TAG,"${Thread.currentThread().name}onNext() $t")
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        }
        observable
            .subscribeOn(Schedulers.computation())
            .map {
                Log.d(TAG,"${Thread.currentThread().name} starting mapping")
                it.toUpperCase()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    companion object{
        const val TAG = "RX"
    }

 }


