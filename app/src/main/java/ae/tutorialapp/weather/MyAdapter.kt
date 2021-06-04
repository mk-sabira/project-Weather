package ae.tutorialapp.weather

import ae.tutorialapp.weather.MyAdapter.Type.ADVERTISEMENT
import ae.tutorialapp.weather.MyAdapter.Type.ITEM
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val listener: OnClickListener): RecyclerView.Adapter<BaseViewHolder<Any>>() {

    private val items = arrayListOf<Any>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BaseViewHolder<Any> {

        return when (viewType){
            ITEM -> MyViewHolder.create(parent, listener)
            else -> AdViewHolder.create(parent, listener)

       }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is String -> ADVERTISEMENT
            is Item -> ITEM
            else -> -1
        }
    }

    fun setItems(newItems:List<Any>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    object Type{
        const val ITEM = 0
        const val ADVERTISEMENT = 1
    }

    companion object{
        const val TAG = "Adapter"
    }

    interface OnClickListener{
        fun onItemClick(position: Int)
        fun onAdClick(position: Int)

        fun onButtonClick(position: Int)
    }
}