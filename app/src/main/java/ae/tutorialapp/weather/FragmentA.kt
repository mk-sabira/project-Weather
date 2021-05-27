package ae.tutorialapp.weather

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FragmentA : Fragment() {

    override fun onAttach(context: Context) {
        Log.i(INFO_TAG, "OnAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(INFO_TAG, "onCreate")
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(INFO_TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(INFO_TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(INFO_TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
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

    override fun onDestroyView() {
        Log.i(INFO_TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i(INFO_TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i(INFO_TAG, "onDetach")
        super.onDetach()
    }


    companion object{
        const val INFO_TAG = "FragmentA"
    }

}