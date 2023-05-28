package by.vzhilko.core.ui.navigation

import androidx.navigation.NavController
import by.vzhilko.core.dto.ImageData

interface INavigator {

    fun openImageDetailsFragment(navController: NavController, data: ImageData)

    fun openImageListDialogFragment(navController: NavController, data: ImageData)

}