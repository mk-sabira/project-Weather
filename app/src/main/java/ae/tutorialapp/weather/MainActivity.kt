package ae.tutorialapp.weather


import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), FragmentItemListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setUp()
    }

    private fun setUp() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, FragmentItems())
            .addToBackStack(null)
            .commit()
    }

    override fun openFragmentItemDetails(id: Long){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentItemDetails.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    override fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


}