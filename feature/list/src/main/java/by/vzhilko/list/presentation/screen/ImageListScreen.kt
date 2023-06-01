@file:OptIn(ExperimentalMaterial3Api::class)

package by.vzhilko.list.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.theme.AppTheme
import by.vzhilko.core.ui.theme.topAppBarTextStyle
import by.vzhilko.list.presentation.viewmodel.ImageListViewModel
import by.vzhilko.core.R as coreR
import androidx.compose.runtime.getValue
import by.vzhilko.list.presentation.component.ImageList
import by.vzhilko.list.presentation.component.ImageListDialog
import by.vzhilko.list.presentation.component.ImageListSearchField

const val IMAGE_LIST_SCREEN_ROUTE: String = "image_list_screen"

@Composable
fun ImageListScreen(onNavigateToDetails: (ImageData) -> Unit = {}) {
    val viewModel: ImageListViewModel = hiltViewModel()
    val query: String by viewModel.queryStateFlow.collectAsState()
    val imagePagingData: LazyPagingItems<ImageData> = viewModel.imageDataPagingDataFlow.collectAsLazyPagingItems()
    val isDialogShown: Boolean by viewModel.imageListDialogAppearanceFlow.collectAsState()
    val imageData: ImageData? by viewModel.imageDataFlow.collectAsState()
    AppTheme {
        Scaffold(
            topBar = { ImageListScreenTopBar() },
            content = { paddingValues ->
                Log.d("myTag", "ImageListScreen paddingValues: ${paddingValues}")
                ImageListScreenContent(
                    modifier = Modifier.padding(paddingValues),
                    lazyPagingItems = imagePagingData,
                    query = query,
                    onTextChanged = { text -> viewModel.updateQuery(text) },
                    onRetry = { imagePagingData.retry() },
                    onItemClick = { data ->
                        viewModel.updateImageData(data)
                        viewModel.updateDialogAppearance(true)
                    }
                )
            }
        )
        if (isDialogShown) {
            ImageListDialog(
                onDismissDialog = { viewModel.updateDialogAppearance(false) },
                onPositiveButtonClick = {
                    Log.d("myTag", "ImageListScreen onPositiveButtonClick data: ${imageData}")
                    viewModel.updateDialogAppearance(false)
                    imageData?.let { onNavigateToDetails(it) }
                },
                onNegativeButtonClick = { viewModel.updateDialogAppearance(false) }
            )
        }
    }
}

@Composable
private fun ImageListScreenTopBar() {
    Log.d("myTag", "ImageListScreenTopBar")
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        title = {
            Text(
                text = stringResource(id = coreR.string.app_name),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.topAppBarTextStyle
            )
        }
    )
}

@Composable
private fun ImageListScreenContent(
    modifier: Modifier = Modifier,
    query: String,
    lazyPagingItems: LazyPagingItems<ImageData>,
    onTextChanged: (String) -> Unit,
    onRetry: () -> Unit,
    onItemClick: (ImageData) -> Unit
) {
    Column(modifier = modifier) {
        ImageListSearchField(
            modifier = Modifier.fillMaxWidth(),
            text = query,
            onTextChanged = onTextChanged
        )
        ImageList(
            modifier = Modifier.fillMaxSize(),
            lazyPagingItems = lazyPagingItems,
            onRetry = onRetry,
            onItemClick = onItemClick
        )
    }
}

@Preview
@Composable
fun ImageListScreenPreview() {
    ImageListScreen()
}

