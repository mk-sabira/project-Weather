package ae.tutorialapp.weather


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentB: Fragment(R.layout.fragment_b) {

    private lateinit var listener: FragmentBListener

    lateinit var textViewB: TextView
    lateinit var buttonB: Button
    lateinit var editTextB: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textViewB = view.findViewById(R.id.tvFragmentB)
        buttonB = view.findViewById(R.id.btFragmentB)
        editTextB = view.findViewById(R.id.edFragmentB)

        buttonB.setOnClickListener{
            val text = editTextB.text.toString()
            listener.setTextToFragmentA(text)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentBListener)listener = context
    }


    fun setNewTextB(text: String){
        textViewB.text = text
    }


    companion object{
        const val TAG = "FragmentB"
    }


}