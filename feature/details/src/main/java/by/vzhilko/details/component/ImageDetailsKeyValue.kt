package by.vzhilko.details.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ImageDetailsKeyValue(modifier: Modifier = Modifier, data: Pair<String, String>) {
    Row(modifier = modifier) {
        Text(text = "${data.first}:")
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = data.second,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}