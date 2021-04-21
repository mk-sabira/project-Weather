package ae.tutorialapp.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var button  = findViewById<Button>(R.id.button1)
    var editText = findViewById<EditText>(R.id.editText)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()



    }
    private fun setUpView(){
        button.setOnClickListener {
            val text = editText.text.toString()


            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("String", text)
            startActivity(intent)
        }
    }
}
