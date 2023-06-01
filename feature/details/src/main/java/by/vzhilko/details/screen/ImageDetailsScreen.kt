@file:OptIn(ExperimentalMaterial3Api::class)

package by.vzhilko.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import by.vzhilko.core.dto.ImageData
import by.vzhilko.details.viewmodel.ImageDetailsViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import by.vzhilko.core.R as coreR
import by.vzhilko.core.ui.theme.AppTheme
import by.vzhilko.core.ui.theme.topAppBarTextStyle
import by.vzhilko.core.util.DefaultState
import by.vzhilko.details.R
import by.vzhilko.details.component.ImageDetailsKeyValue
import coil.compose.AsyncImage
import coil.request.ImageRequest

const val IMAGE_DETAILS_SCREEN_ROUTE: String = "image_details_screen"

@Composable
fun ImageDetailsScreen(data: ImageData, onBack: () -> Unit) {
    val viewModel: ImageDetailsViewModel = hiltViewModel()
    viewModel.updateImageData(data)
    val imageDataState: DefaultState<ImageData> by viewModel.imageDataStateFlow.collectAsState()
    AppTheme {
        Scaffold(
            topBar = {
                ImageDetailsScreenTopBar(
                    onNavigationIconClick = onBack
                )
            },
            content = { paddingValues ->
                when (imageDataState) {
                    is DefaultState.Success -> {
                        ImageDetailsScreenContent(
                            modifier = Modifier.padding(paddingValues),
                            data = (imageDataState as DefaultState.Success<ImageData>).value
                        )
                    }

                    else -> { /*ignore*/
                    }
                }
            }
        )
    }
}

@Composable
private fun ImageDetailsScreenTopBar(onNavigationIconClick: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigation icon")
            }
        },
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
private fun ImageDetailsScreenContent(
    modifier: Modifier = Modifier,
    data: ImageData
) {
    ConstraintLayout(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    ) {
        val (imageRef, userNameRef, tagsRef, likesRef, downloadsRef, commentsRef) = createRefs()
        ImageDetailsKeyValue(
            modifier = Modifier.constrainAs(commentsRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            data = Pair(
                stringResource(R.string.image_details_view_comments_caption),
                data.comments.toString()
            )
        )
        ImageDetailsKeyValue(
            modifier = Modifier.constrainAs(downloadsRef) {
                bottom.linkTo(commentsRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            data = Pair(
                stringResource(R.string.image_details_view_downloads_caption),
                data.downloads.toString()
            )
        )
        ImageDetailsKeyValue(
            modifier = Modifier.constrainAs(likesRef) {
                bottom.linkTo(downloadsRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            data = Pair(
                stringResource(R.string.image_details_view_likes_caption),
                data.likes.toString()
            )
        )
        ImageDetailsKeyValue(
            modifier = Modifier.constrainAs(tagsRef) {
                bottom.linkTo(likesRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            data = Pair(
                stringResource(R.string.image_details_view_tags_caption),
                data.tags
            )
        )
        ImageDetailsKeyValue(
            modifier = Modifier.constrainAs(userNameRef) {
                bottom.linkTo(tagsRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            data = Pair(
                stringResource(R.string.image_details_view_name_caption),
                data.user
            )
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.largeImageUrl)
                .placeholder(coreR.drawable.ic_placeholder)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(userNameRef.top)
                    start.linkTo(parent.start)
                },
            contentDescription = "Image details image"
        )
    }
}