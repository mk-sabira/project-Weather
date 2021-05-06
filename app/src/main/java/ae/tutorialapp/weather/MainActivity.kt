package ae.tutorialapp.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpFragment()
    }

    private fun setUpFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentA())

        transaction.commit()

    }
}