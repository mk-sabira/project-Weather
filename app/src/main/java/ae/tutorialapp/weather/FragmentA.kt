package ae.tutorialapp.weather


import android.content.Context
import android.os.Binder
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentA: Fragment(R.layout.fragment_a) {

    lateinit var buttonA : Button
    lateinit var editTextA : EditText
    lateinit var textViewA: TextView
    private lateinit var listener: FragmentAListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonA = view.findViewById(R.id.btFragmentA)
        editTextA = view.findViewById(R.id.edFragmentA)
        textViewA = view.findViewById(R.id.tvFragmentA)


        buttonA.setOnClickListener{
            val text = editTextA.text.toString()
            listener.setTextToFragmentB(text)


        }
        val textFromArguments = arguments?.getString("String")

        textViewA.text = textFromArguments
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAListener)listener = context
    }

    fun setNewTextA(text: String){
        textViewA.text = text
    }
    companion object{
        const val TAG = "FragmentA"

        fun newInstance(argument: String): FragmentA{
            val fragmentA = FragmentA()
            val args = Bundle()
            args.putString("String", "Finally!")
            fragmentA.arguments = args

            return fragmentA
        }

    }


}