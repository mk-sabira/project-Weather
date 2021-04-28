package ae.tutorialapp.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityC: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)


        val buttonC = findViewById<Button>(R.id.buttonC)


        buttonC.setOnClickListener { ActivityD.start(this) }


    }

    companion object {
        fun start (context: Context){
            val intent = Intent(context, ActivityC::class.java)
            context.startActivity(intent)
        }

    }
}