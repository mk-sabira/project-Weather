package ae.tutorialapp.weather


import ae.tutorialapp.weather.models.ForeCast
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       textView = findViewById(R.id.textView)
       textView2 = findViewById(R.id.textView2)

        fetchWeatherUsingQuarry()

    }

    private fun fetchWeatherUsingQuarry() {
        val call = WeatherClient.weatherApi.fetWeatherUsingQuerry( 24.4539,  54.3773 )

        call.enqueue(object : Callback<ForeCast> {
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful) {
                    val foreCast = response.body()

                    foreCast?.let {
                        textView.text = it.current?.temp?.toString()
                        textView2.text = it.current?.weather?.get(0)?.description

                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

 }


