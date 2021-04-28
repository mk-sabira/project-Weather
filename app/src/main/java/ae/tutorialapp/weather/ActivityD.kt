package ae.tutorialapp.weather

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__d)

        val buttonD = findViewById<Button>(R.id.buttonD)
        buttonD.setOnClickListener { ActivityD.start(this) }

        val openC = findViewById<Button>(R.id.openC)
        openC.setOnClickListener { ActivityC.start(this) }

        val openA = findViewById<Button>(R.id.openA)
        openA.setOnClickListener { ActivityA.start(this) }

    }


    companion object {
        fun start (context: Context){
            val intent = Intent(context, ActivityD::class.java)
            context.startActivity(intent)
        }
    }
}

