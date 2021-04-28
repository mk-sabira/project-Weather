package ae.tutorialapp.weather

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ActivityB : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        val buttonB = findViewById<Button>(R.id.buttonB)


        buttonB.setOnClickListener { ActivityC.start(this) }



    }
    companion object {
        fun start (context: Context){
            val intent = Intent(context, ActivityB::class.java)
            context.startActivity(intent)
        }

    }


}