package ae.tutorialapp.weather

import ae.tutorialapp.weather.ActivityA.Companion.start
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ActivityA : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_a)


            val buttonA = findViewById<Button>(R.id.buttonA)


            buttonA.setOnClickListener { ActivityB.start(this) }
    }

    companion object {
        fun start (context: Context){
            val intent = Intent(context, ActivityA::class.java)
            context.startActivity(intent)
        }

    }





}
