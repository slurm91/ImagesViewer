package by.vzhilko.list.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.R

@Composable
fun ImageList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<ImageData>,
    onRetry: () -> Unit
) {
    Log.d("myTag", "ImageList")
    Box(modifier) {
        LazyColumn {
            itemsIndexed(
                items = lazyPagingItems,
                key = { index, item -> item.id }
            ) { index, item ->
                //Show error message when scroll up
                when (lazyPagingItems.loadState.prepend) {
                    is LoadState.Error -> {
                        if (index == 0) {
                            val errorModifier: Modifier = Modifier.fillMaxWidth()
                            val message: String =
                                (lazyPagingItems.loadState.prepend as? LoadState.Error)?.error?.message
                                    ?: stringResource(R.string.image_list_default_error_message)
                            ImageListError(
                                modifier = errorModifier,
                                message = message,
                                onRetryClick = onRetry
                            )
                        }
                    }
                    else -> {}
                }

                //Show item if it's possible
                item?.let {
                    ImageListItem(data = it)
                    if (index < lazyPagingItems.itemCount - 1) {
                        Divider()
                    }
                }

                //Show error message when scroll down
                when (lazyPagingItems.loadState.append) {
                    is LoadState.Error -> {
                        if (index >= lazyPagingItems.itemCount - 1) {
                            val errorModifier: Modifier = Modifier.fillMaxWidth()
                            val message: String =
                                (lazyPagingItems.loadState.append as? LoadState.Error)?.error?.message
                                    ?: stringResource(R.string.image_list_default_error_message)
                            ImageListError(
                                modifier = errorModifier,
                                message = message,
                                onRetryClick = onRetry
                            )
                        }
                    }
                    else -> {}
                }
            }
        }

        //Show error message list is loaded for the first time
        when (lazyPagingItems.loadState.refresh) {
            is LoadState.Error -> {
                val errorModifier: Modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                val message: String =
                    (lazyPagingItems.loadState.refresh as? LoadState.Error)?.error?.message
                        ?: stringResource(R.string.image_list_default_error_message)
                ImageListError(
                    modifier = errorModifier,
                    message = message,
                    onRetryClick = onRetry
                )
            }

            is LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is LoadState.NotLoading -> {}
        }
    }
}