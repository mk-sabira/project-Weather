package ae.tutorialapp.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
    val iv_icon = itemView.findViewById<ImageView>(R.id.iv_icon)
    val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
    fun bind(item: Item){
        itemView.run {
            iv_icon.setImageResource(item.icon)
            tv_name.text = item.name
        }
    }

    companion object{
        fun create(parent: ViewGroup): MyViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_my_viewholder, parent, false)
            return MyViewHolder(view)
        }
    }
}