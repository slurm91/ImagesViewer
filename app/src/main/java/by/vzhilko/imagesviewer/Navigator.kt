package by.vzhilko.imagesviewer

import androidx.navigation.NavController
import by.vzhilko.core.di.annotation.scope.AppScope
import by.vzhilko.core.ui.navigation.INavigator
import javax.inject.Inject

@AppScope
class Navigator @Inject constructor() : INavigator {

    override fun openImageDetailsFragment(navController: NavController) {
        navController.navigate(R.id.action_imageListFragment_to_imageDetailsFragment)
    }

}