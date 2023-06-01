package by.vzhilko.imagesviewer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.viewmodel.ArgumentsViewModel
import by.vzhilko.details.screen.IMAGE_DETAILS_SCREEN_ROUTE
import by.vzhilko.details.screen.ImageDetailsScreen
import by.vzhilko.list.presentation.screen.IMAGE_LIST_SCREEN_ROUTE
import by.vzhilko.list.presentation.screen.ImageListScreen

@Composable
fun Navigation(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = IMAGE_LIST_SCREEN_ROUTE,
    argsViewModel: ArgumentsViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(IMAGE_LIST_SCREEN_ROUTE) {
            ImageListScreen(onNavigateToDetails = { data ->
                argsViewModel.imageDetailsScreenArgs = data
                navHostController.navigate(IMAGE_DETAILS_SCREEN_ROUTE)
            })
        }
        composable(IMAGE_DETAILS_SCREEN_ROUTE) { entry ->
            val data: ImageData? = argsViewModel.imageDetailsScreenArgs
            data?.let {
                ImageDetailsScreen(
                    data = it,
                    onBack = { navHostController.popBackStack() }
                )
            }
        }
    }
}