package ae.tutorialapp.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textView = findViewById<TextView>(R.id.textView)

        val textFromIntent = intent.getStringArrayExtra("String")

        textView.text = textFromIntent.toString()
    }
}