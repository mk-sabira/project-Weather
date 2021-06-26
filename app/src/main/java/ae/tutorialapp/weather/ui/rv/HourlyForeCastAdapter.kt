package ae.tutorialapp.weather.ui.rv

import ae.tutorialapp.weather.models.HourlyForeCast
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HourlyForeCastAdapter: RecyclerView.Adapter<HourlyForeCastVH>() {
    private val items = arrayListOf<HourlyForeCast>()

    fun setItems(newItems: List<HourlyForeCast>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForeCastVH {
        return HourlyForeCastVH.createVH(parent)
    }

    override fun onBindViewHolder(holder: HourlyForeCastVH, position: Int) {
        holder.bindH(items[position])
    }

    override fun getItemCount() = items.count()
}