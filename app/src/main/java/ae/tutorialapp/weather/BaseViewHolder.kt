package ae.tutorialapp.weather

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View):RecyclerView.ViewHolder(itemView) {
   abstract fun bind(item: T)
}