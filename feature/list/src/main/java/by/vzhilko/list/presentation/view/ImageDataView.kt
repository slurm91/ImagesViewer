package by.vzhilko.list.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import by.vzhilko.list.R
import by.vzhilko.core.R as coreR
import by.vzhilko.core.dto.ImageData
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.textview.MaterialTextView

class ImageDataView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private lateinit var iconImageView: ImageView
    private lateinit var userNameTextView: TextView
    private lateinit var tagsTextView: TextView

    init {
        initView()
        populateInEditMode()
    }

    private fun initView() {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        val padding: Int = resources.getDimensionPixelSize(coreR.dimen.default_margin)
        setPadding(padding, padding, padding, padding)

        val iconSize: Int = resources.getDimensionPixelSize(R.dimen.image_list_item_icon_size)
        iconImageView = AppCompatImageView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(iconSize, iconSize)
        }
        addView(iconImageView)

        val textViewLayout = LinearLayout(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                leftMargin = padding
            }
            orientation = VERTICAL
        }

        userNameTextView = MaterialTextView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setTextAppearance(coreR.style.ImageViewer_TextAppearance_Regular)
        }
        textViewLayout.addView(userNameTextView)

        tagsTextView = MaterialTextView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setTextAppearance(coreR.style.ImageViewer_TextAppearance_Medium_Gray)
        }
        textViewLayout.addView(tagsTextView)

        addView(textViewLayout)
    }

    private fun populateInEditMode() {
        if (isInEditMode) {
            iconImageView.setImageResource(coreR.drawable.ic_placeholder)
            userNameTextView.text = "Fox Malder"
            tagsTextView.text = "train, failway, forest, snow"
        }
    }

    fun populate(data: ImageData) {
        userNameTextView.text = data.user
        tagsTextView.text = data.tags
        iconImageView.load(data.previewUrl) {
            crossfade(true)
            placeholder(coreR.drawable.ic_placeholder)
            transformations(CircleCropTransformation())
        }
    }

}