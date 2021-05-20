package ae.tutorialapp.weather


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    private lateinit var button : Button
    private lateinit var button2 : Button
    private lateinit var button3 : Button
    private lateinit var textView: TextView
    private lateinit var popButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        textView = findViewById(R.id.textView2)
        popButton = findViewById(R.id.backStack)

        setFragment()
    }

    private fun setFragment() {
        textView.text = supportFragmentManager.backStackEntryCount.toString()

        supportFragmentManager.addOnBackStackChangedListener {
            textView.text = supportFragmentManager.backStackEntryCount.toString()
        }
        button.setOnClickListener{
            addFragment(FirstFragment())
        }

        button2.setOnClickListener{
            addFragment(SecondFragment())
        }
        button3.setOnClickListener{
            addFragment(ThirdFragment())
        }
        popButton.setOnClickListener{
            supportFragmentManager.popBackStack(0, 0)
        }


    }

    private fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()


            transaction.run {
                replace(R.id.container, fragment)
                addToBackStack(null)
                commit()
            }

    }


}