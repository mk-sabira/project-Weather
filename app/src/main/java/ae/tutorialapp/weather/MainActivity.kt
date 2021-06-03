package ae.tutorialapp.weather


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var myAdapter: MyAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        setUp()
    }

    private fun setUp() {

        myAdapter = MyAdapter()
        recyclerView.adapter = myAdapter

        myAdapter.setItems(Data.getLongListOfItems())
    }


}