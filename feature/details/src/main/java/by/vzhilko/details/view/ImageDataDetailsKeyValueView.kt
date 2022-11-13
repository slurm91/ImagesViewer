package by.vzhilko.details.view

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import by.vzhilko.details.R
import by.vzhilko.core.R as coreR
import com.google.android.material.textview.MaterialTextView

class ImageDataDetailsKeyValueView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private lateinit var keyTextView: TextView
    private lateinit var valueTextView: TextView

    init {
        initView()
        populateInEditMode()
    }

    private fun initView() {
        orientation = HORIZONTAL
        setPadding(resources.getDimensionPixelSize(R.dimen.image_details_key_value_view_padding))

        keyTextView = MaterialTextView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                rightMargin = resources.getDimensionPixelSize(R.dimen.image_details_key_value_view_key_right_padding)
            }
            setTextAppearance(coreR.style.ImageViewer_TextAppearance_Regular)
        }
        addView(keyTextView)

        valueTextView = MaterialTextView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            setTextAppearance(coreR.style.ImageViewer_TextAppearance_Regular_Gray)
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
        }
        addView(valueTextView)
    }

    @SuppressLint("SetTextI18n")
    fun populate(data: Pair<String, String>) {
        keyTextView.text = "${data.first}:"
        valueTextView.text = data.second
    }

    private fun populateInEditMode() {
        if (isInEditMode) {
            populate(Pair("Key", "Value"))
        }
    }

}