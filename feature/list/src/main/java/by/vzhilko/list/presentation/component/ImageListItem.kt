package by.vzhilko.list.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.R as coreR
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageListItem(data: ImageData, onClick: (ImageData) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(data) }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.previewUrl)
                .placeholder(coreR.drawable.ic_placeholder)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically),
            contentDescription = "Image list item image"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = data.user)
            Text(text = data.tags)
        }
    }
}