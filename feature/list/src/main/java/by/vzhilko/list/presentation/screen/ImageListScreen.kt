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
import by.vzhilko.list.presentation.component.ImageListSearchField

const val IMAGE_LIST_SCREEN_ROUTE: String = "image_list_screen"

@Composable
fun ImageListScreen() {
    val viewModel: ImageListViewModel = hiltViewModel()
    val query: String by viewModel.queryStateFlow.collectAsState()
    val imagePagingData: LazyPagingItems<ImageData> = viewModel.imageDataPagingDataFlow.collectAsLazyPagingItems()
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
                    onRetry = { imagePagingData.retry() }
                )
            }
        )
    }
}

@Composable
private fun ImageListScreenTopBar() {
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
    onRetry: () -> Unit
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
            onRetry = onRetry
        )
    }
}

@Preview
@Composable
fun ImageListScreenPreview() {
    ImageListScreen()
}

