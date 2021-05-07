package ae.tutorialapp.weather

import ae.tutorialapp.weather.databinding.FragmentABinding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentA: Fragment(R.layout.fragment_a) {

    private lateinit var listener: FragmentAListener
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_a, container,
            false)
        val textFromArgument = arguments?.getString("String")

        val tvFragment = view.findViewById<TextView>(R.id.tvFragment)
        val button4 = view.findViewById<TextView>(R.id.button4)
        tvFragment.text = textFromArgument
        button4.setOnClickListener {
            listener.showToast()
        }
        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAListener){
            listener = context
        }
    }
    companion object{
        const val TAG = "A"
    }


}