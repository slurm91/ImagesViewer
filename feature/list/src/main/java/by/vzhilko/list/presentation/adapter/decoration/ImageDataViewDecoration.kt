package by.vzhilko.list.presentation.adapter.decoration

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import by.vzhilko.list.R
import by.vzhilko.core.R as coreR

class ImageDataViewDecoration(context: Context) : RecyclerView.ItemDecoration() {

    @SuppressLint("UseCompatLoadingForDrawables")
    private val dividerDrawable: Drawable? = context.getDrawable(R.drawable.bg_image_data_view_divider)
    private val dividerDrawablePadding: Int = context.resources.getDimensionPixelSize(coreR.dimen.default_margin)
    private val drawableBounds: Rect = Rect()
    private var childPosition: Int = RecyclerView.NO_POSITION

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        dividerDrawable?.let {
            parent.children.forEach { child: View ->
                childPosition = parent.getChildAdapterPosition(child)
                if (childPosition == RecyclerView.NO_POSITION) {
                    return
                }

                parent.adapter?.itemCount?.let { size: Int ->
                    if (childPosition != size - 1)
                        drawableBounds.apply {
                            left = child.left + dividerDrawablePadding
                            right = child.right - dividerDrawablePadding
                            top = child.bottom
                            bottom = child.bottom + it.intrinsicHeight
                        }
                    it.bounds = drawableBounds
                    it.draw(canvas)
                }
            }
        }
    }

}