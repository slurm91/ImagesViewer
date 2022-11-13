package by.vzhilko.details.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setPadding
import by.vzhilko.core.dto.ImageData
import by.vzhilko.details.R
import coil.load
import by.vzhilko.core.R as coreR

class ImageDataDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private lateinit var imageView: ImageView

    private lateinit var userNameKeyValueView: ImageDataDetailsKeyValueView
    private val userNameKey: String = resources.getString(R.string.image_details_view_name_caption)

    private lateinit var tagsKeyValueView: ImageDataDetailsKeyValueView
    private val tagsKey: String = resources.getString(R.string.image_details_view_tags_caption)

    private lateinit var likesKeyValueView: ImageDataDetailsKeyValueView
    private val likesKey: String = resources.getString(R.string.image_details_view_likes_caption)

    private lateinit var downloadsKeyValueView: ImageDataDetailsKeyValueView
    private val downloadsKey: String = resources.getString(R.string.image_details_view_downloads_caption)

    private lateinit var commentsKeyValueView: ImageDataDetailsKeyValueView
    private val commentsKey: String = resources.getString(R.string.image_details_view_comments_caption)

    init {
        initView()
        populateInEditMode()
    }

    private fun initView() {
        setPadding(resources.getDimensionPixelSize(coreR.dimen.default_margin))

        imageView = AppCompatImageView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 0)
        }
        addView(imageView)

        userNameKeyValueView = createImageDataDetailsKeyValueView()
        addView(userNameKeyValueView)

        tagsKeyValueView = createImageDataDetailsKeyValueView()
        addView(tagsKeyValueView)

        likesKeyValueView = createImageDataDetailsKeyValueView()
        addView(likesKeyValueView)

        downloadsKeyValueView = createImageDataDetailsKeyValueView()
        addView(downloadsKeyValueView)

        commentsKeyValueView = createImageDataDetailsKeyValueView()
        addView(commentsKeyValueView)

        setupView()
    }

    private fun createImageDataDetailsKeyValueView(): ImageDataDetailsKeyValueView {
        return ImageDataDetailsKeyValueView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
    }

    private fun setupView() {
        ConstraintSet().apply {
            clone(this@ImageDataDetailsView)

            connect(commentsKeyValueView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
            connect(commentsKeyValueView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(commentsKeyValueView.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)

            connect(downloadsKeyValueView.id, ConstraintSet.BOTTOM, commentsKeyValueView.id, ConstraintSet.TOP)
            connect(downloadsKeyValueView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(downloadsKeyValueView.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)

            connect(likesKeyValueView.id, ConstraintSet.BOTTOM, downloadsKeyValueView.id, ConstraintSet.TOP)
            connect(likesKeyValueView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(likesKeyValueView.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)

            connect(tagsKeyValueView.id, ConstraintSet.BOTTOM, likesKeyValueView.id, ConstraintSet.TOP)
            connect(tagsKeyValueView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(tagsKeyValueView.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)

            connect(userNameKeyValueView.id, ConstraintSet.BOTTOM, tagsKeyValueView.id, ConstraintSet.TOP)
            connect(userNameKeyValueView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            connect(userNameKeyValueView.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)

            connect(imageView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            connect(imageView.id, ConstraintSet.BOTTOM, userNameKeyValueView.id, ConstraintSet.TOP)
            connect(imageView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)

            applyTo(this@ImageDataDetailsView)
        }
    }

    fun populate(data: ImageData) {
        imageView.load(data.largeImageUrl) {
            crossfade(true)
            placeholder(coreR.drawable.ic_placeholder)
        }
        userNameKeyValueView.populate(Pair(userNameKey, data.user))
        tagsKeyValueView.populate(Pair(tagsKey, data.tags))
        likesKeyValueView.populate(Pair(likesKey, data.likes.toString()))
        downloadsKeyValueView.populate(Pair(downloadsKey, data.downloads.toString()))
        commentsKeyValueView.populate(Pair(commentsKey, data.comments.toString()))
    }

    private fun populateInEditMode() {
        if (isInEditMode) {
            imageView.setImageResource(coreR.drawable.ic_placeholder)
            userNameKeyValueView.populate(Pair("Name", "Tom"))
            tagsKeyValueView.populate(Pair("Tags", "Train, Winter, Mountains"))
            likesKeyValueView.populate(Pair("Likes", "500"))
            downloadsKeyValueView.populate(Pair("Downloads", "700"))
            commentsKeyValueView.populate(Pair("Comments", "222"))
        }
    }

}