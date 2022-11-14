package by.vzhilko.list.presentation.dialog

import android.content.Context
import androidx.navigation.fragment.findNavController
import by.vzhilko.core.di.IDIContainerProvider
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.dialog.DefaultDialogFragment
import by.vzhilko.core.ui.dialog.DefaultDialogModel
import by.vzhilko.core.ui.navigation.INavigator
import by.vzhilko.core.R as coreR
import by.vzhilko.list.R
import by.vzhilko.list.di.component.ImageListComponent
import javax.inject.Inject

class ImageListDialogFragment : DefaultDialogFragment() {

    @Inject
    lateinit var navigator: INavigator

    private val data: ImageData? by lazy { arguments?.getParcelable("IMAGE_DATA") as? ImageData }

    override val model: DefaultDialogModel
        get() = DefaultDialogModel(
            title = getString(coreR.string.app_name),
            message = getString(R.string.image_list_details_view_offer_message),
            positiveButtonText = getString(coreR.string.ok_caption),
            negativeButtonText = getString(coreR.string.no_caption)
        )

    override val onPositiveButtonClickAction: (() -> Unit)
        get() = {
            data?.let { navigator.openImageDetailsFragment(findNavController(), it) }
        }

    override fun onAttach(context: Context) {
        val component: ImageListComponent = (requireActivity().application as IDIContainerProvider)
            .getComponentProvider<ImageListComponent.Provider>()
            .getImageListComponent()
        component.inject(this)
        super.onAttach(context)
    }

}