package by.vzhilko.imagesviewer

import android.os.Bundle
import androidx.navigation.NavController
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.navigation.INavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() : INavigator {

    override fun openImageDetailsFragment(navController: NavController, data: ImageData) {
        val bundle = Bundle().apply {
            putParcelable("IMAGE_DATA", data)
        }
        navController.navigate(R.id.action_imageListDialogFragment_to_imageDetailsFragment, bundle)
    }

    override fun openImageListDialogFragment(navController: NavController, data: ImageData) {
        val bundle = Bundle().apply {
            putParcelable("IMAGE_DATA", data)
        }
        navController.navigate(R.id.action_imageListFragment_to_imageListDialogFragment, bundle)
    }

}