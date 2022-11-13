package by.vzhilko.core.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<V : View, D>(view: View) : RecyclerView.ViewHolder(view) {

    private var _data: D? = null
    val data: D
        get() = _data!!

    open fun populate(data: D) {
        _data = data
    }

    @Suppress("UNCHECKED_CAST")
    protected fun getView(): V {
        return itemView as V
    }

}