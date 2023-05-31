package by.vzhilko.list.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import by.vzhilko.list.R

@Composable
fun ImageListError(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.image_list_default_error_message),
    onRetryClick: () -> Unit = { }
) {
    Column(modifier = modifier) {
        Text(text = message, modifier = Modifier.align(Alignment.CenterHorizontally))
        Button(onClick = onRetryClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = stringResource(R.string.image_list_retry_caption))
        }
    }
}