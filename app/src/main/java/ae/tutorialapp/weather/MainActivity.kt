package ae.tutorialapp.weather



import ae.tutorialapp.weather.models.ForeCast
import ae.tutorialapp.weather.models.Post
import ae.tutorialapp.weather.models.Weather
import ae.tutorialapp.weather.network.PostApi
import ae.tutorialapp.weather.network.WeatherApi
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       textView = findViewById(R.id.textView)
       textView2 = findViewById(R.id.textView2)

//        fetchWeather()
//        fetchWeatherUsingQuery()
//        fetchPostById()
//        createPost()
//        createPostUsingFields()

        createPostUsingFieldMap()
    }

    private fun createPostUsingFieldMap() {
        val map = HashMap<String, String>().apply {
            put("userId", "55")
            put("title", "SUP!!")
            put("Body", "Al Ain")
        }

        val call = postsApi.createPostUsingFieldMap(map)

        call.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val postResult = response.body()
                postResult?.let {
                    val resultText = "ID: " + it.id+ "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"
                    textView.text = resultText
                }


            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        }
        )
    }

    private fun createPostUsingFields() {

        val  call = postsApi.createPostUsingFields(userId = 99, title = "Salam", body = "Abu Dhabi")
        call.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val postResult = response.body()
                postResult?.let {
                    val resultText = "ID: " + it.id+ "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"
                    textView.text = resultText
                }


            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        }
        )
    }

    private fun createPost() {
        val post = Post(userId = "42", title = "Hello", body = "Abu Dhabi")

        val  call = postsApi.createPost(post)
        call.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val postResult = response.body()
                postResult?.let {
                    val resultText = "ID: " + it.id+ "\n" +
                            "userID: " + it.userId + "\n" +
                            "TITLE: " + it.title + "\n" +
                            "BODY: " + it.body + "\n"
                    textView.text = resultText
                }


            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        }
        )
    }

    private fun fetchPostById() {
        val call = postsApi.fetchPostById(10)

        call.enqueue(object : Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    val post = response.body()
                    post?.let {
                        val resultText = "ID: " + it.id+ "\n" +
                                "userID: " + it.userId + "\n" +
                                "TITLE: " + it.title + "\n" +
                                "BODY: " + it.body + "\n"
                        textView.text = resultText
                    }


                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }

            }
        )
    }

    private fun fetchWeatherUsingQuery() {
        val call = weatherApi.fetWeatherUsingQuery(24.4539, 54.3773, lang = "ar" )

        call.enqueue(object : Callback<ForeCast> {
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful) {
                    val forCast = response.body()
                    forCast?.let {
                        textView.text = it.current?.temp?.toString()
                        textView2.text = it.current?.weather?.get(0)?.description

                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

        }
        )

    }

    private fun fetchWeather() {
        val call = weatherApi.getweather()

        call.enqueue(object : Callback<ForeCast> {
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful) {
                    val forCast = response.body()
                    forCast?.let {
                        textView.text = it.current?.temp?.toString()
                        textView2.text = it.current?.weather?.get(0)?.description

                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

        }
        )
    }

    private val okhttp by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    }

    private val retrofit by lazy {
            Retrofit.Builder()
//                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build()
        }
    private val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    private val postsApi by lazy {
        retrofit.create(PostApi::class.java)
    }


 }


