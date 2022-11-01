package by.vzhilko.core.ui.navigation

import androidx.navigation.NavController

interface INavigator {

    fun openImageDetailsFragment(navController: NavController)

    interface Provider {
        fun getNavigator(): INavigator
    }

}