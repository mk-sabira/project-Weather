package ae.tutorialapp.weather


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), FragmentAListener, FragmentBListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragments()


    }

    private fun setFragments() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, FragmentA.newInstance("Finally!"), FragmentA.TAG )
            .add(R.id.container2, FragmentB(), FragmentB.TAG)
            .commit()
    }

    override fun setTextToFragmentB(text: String) {
        (supportFragmentManager.findFragmentByTag(FragmentB.TAG) as FragmentB)
            .setNewTextB(text)
    }

    override fun setTextToFragmentA(text: String) {
        (supportFragmentManager.findFragmentByTag(FragmentA.TAG) as FragmentA)
            .setNewTextA(text)
    }


}