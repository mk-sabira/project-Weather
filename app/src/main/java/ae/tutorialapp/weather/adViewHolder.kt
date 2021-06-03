package ae.tutorialapp.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class AdViewHolder(itemView: View):  BaseViewHolder<Any>(itemView) {

    override fun bind(item: Any){
        item as String
            itemView.run {
                var tv_url = findViewById<TextView>(R.id.tv_url)
                tv_url.text = item
            }
    }


    companion object{
        fun create(parent: ViewGroup): AdViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ad_viewholder, parent, false)
            return AdViewHolder(view)
        }
    }
}