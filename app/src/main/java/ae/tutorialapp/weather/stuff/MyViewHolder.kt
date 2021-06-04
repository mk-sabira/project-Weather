package ae.tutorialapp.weather.stuff

import ae.tutorialapp.weather.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MyViewHolder(itemView: View): BaseViewHolder<Any>(itemView){
    val iv_icon = itemView.findViewById<ImageView>(R.id.iv_icon)
    val tv_name = itemView.findViewById<TextView>(R.id.tv_name)


    override fun bind(item: Any){
        item as Item
        itemView.run {
            iv_icon.setImageResource(item.icon)
            tv_name.text = item.name
        }
    }

    companion object{


        fun create(parent: ViewGroup, listener: MyAdapter.OnClickListener): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_my_viewholder, parent, false)
            val holder =  MyViewHolder(view)

            holder.itemView.setOnClickListener{
                listener.onItemClick(holder.adapterPosition)
            }

            holder.itemView.run {
                val button = findViewById<Button>(R.id.button4)
                button.setOnClickListener {
                    listener.onButtonClick(holder.adapterPosition)
                }
            }

            return holder
        }
    }
}