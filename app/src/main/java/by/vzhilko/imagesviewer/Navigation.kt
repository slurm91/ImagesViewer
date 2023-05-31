package by.vzhilko.imagesviewer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.vzhilko.list.presentation.screen.IMAGE_LIST_SCREEN_ROUTE
import by.vzhilko.list.presentation.screen.ImageListScreen

@Composable
fun Navigation(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = IMAGE_LIST_SCREEN_ROUTE
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(IMAGE_LIST_SCREEN_ROUTE) {
            ImageListScreen()
        }
    }
}