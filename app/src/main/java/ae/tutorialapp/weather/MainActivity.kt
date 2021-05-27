package ae.tutorialapp.weather


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {


    lateinit var button: Button
    lateinit var button2: Button
    lateinit var button3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(INFO_TAG, "onCreate")


        button = findViewById<Button>(R.id.button)
        button2 = findViewById<Button>(R.id.button2)
        button3 = findViewById<Button>(R.id.button3)

        setClick()

    }

    private fun setClick() {
        button.setOnClickListener {
            addFragment(FragmentA())
        }
        button2.setOnClickListener {
            addFragment(FragmentB())
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStart() {
        Log.i(INFO_TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(INFO_TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(INFO_TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(INFO_TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i(INFO_TAG, "onDestroy")
        super.onDestroy()
    }


    companion object{
        const val INFO_TAG = "MainActivity"
    }


}