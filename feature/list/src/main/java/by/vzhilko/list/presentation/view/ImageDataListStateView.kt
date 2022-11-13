package by.vzhilko.list.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import by.vzhilko.list.R
import by.vzhilko.core.R as coreR
import by.vzhilko.list.presentation.model.ImageDataListState
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView

class ImageDataListStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private lateinit var progressIndicator: ProgressBar

    private lateinit var errorMessageTextView: TextView
    private val defaultErrorMessage: String = resources.getString(R.string.image_list_default_error_message)

    private lateinit var retryButton: Button
    var onRetryAction: (() -> Unit)? = null
        set(value) {
            field = value
            if (value != null) {
                retryButton.setOnClickListener { value.invoke() }
            }
        }

    var state: ImageDataListState = ImageDataListState.NO_STATE
        set(value) {
            field = value
            populateState(value)
        }

    init {
        initView()
    }

    private fun initView() {
        val padding: Int = resources.getDimensionPixelSize(coreR.dimen.default_margin)
        setPadding(padding, padding, padding, padding)

        progressIndicator = CircularProgressIndicator(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            isIndeterminate = true
        }
        addView(progressIndicator)

        errorMessageTextView = MaterialTextView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            setTextAppearance(coreR.style.ImageViewer_TextAppearance_Regular_Gray)
            gravity = Gravity.CENTER
        }
        addView(errorMessageTextView)

        retryButton = MaterialButton(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = padding
            }
            text = resources.getString(R.string.image_list_retry_caption)
        }
        addView(retryButton)

        setupView()
        state = ImageDataListState.NO_STATE
    }

    private fun setupView() {
        ConstraintSet().apply {
            clone(this@ImageDataListStateView)

            connect(
                progressIndicator.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP
            )
            connect(
                progressIndicator.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
            )
            connect(
                progressIndicator.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT
            )
            connect(
                progressIndicator.id,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
            )

            connect(
                errorMessageTextView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP
            )
            connect(
                errorMessageTextView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
            )
            connect(
                errorMessageTextView.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT
            )
            connect(
                errorMessageTextView.id,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
            )

            connect(
                retryButton.id,
                ConstraintSet.TOP,
                errorMessageTextView.id,
                ConstraintSet.BOTTOM
            )
            connect(
                retryButton.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
            )
            connect(
                retryButton.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT
            )
            connect(
                retryButton.id,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
            )

            applyTo(this@ImageDataListStateView)
        }
    }

    private fun populateState(state: ImageDataListState) {
        when (state) {
            ImageDataListState.LOADING -> {
                showProgress(true)
                showError(false)
            }
            ImageDataListState.ERROR -> {
                showProgress(false)
                showError(true, state.message)
            }
            ImageDataListState.NO_STATE -> {
                showProgress(false)
                showError(false)
            }
        }
    }

    private fun showProgress(show: Boolean) {
        progressIndicator.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(show: Boolean, message: String? = null) {
        val visibility: Int = if (show) View.VISIBLE else View.GONE
        errorMessageTextView.apply {
            this.visibility = visibility
            errorMessageTextView.text = message ?: defaultErrorMessage
        }
        retryButton.visibility = visibility
    }

}