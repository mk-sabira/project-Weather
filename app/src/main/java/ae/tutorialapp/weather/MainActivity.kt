package ae.tutorialapp.weather

import ae.tutorialapp.weather.databinding.ActivityMainBinding
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add


class MainActivity : AppCompatActivity(), FragmentAListener {

    lateinit var button: Button
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btnMain)
        editText = findViewById(R.id.edText)

        setUp()
    }

    private fun setUp() {
        button.setOnClickListener {
            val text = editText.text.toString()

            val fragment = FragmentA()

            val args = Bundle()
            args.putString("String", text)

            fragment.arguments = args

            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, FragmentA.TAG)
                .commit()
        }
    }

    override fun showToast() {
        Toast.makeText(this, "Fragment A showing Toast", Toast.LENGTH_LONG ).show()
    }


}