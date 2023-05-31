@file:OptIn(ExperimentalMaterial3Api::class)

package by.vzhilko.list.presentation.component

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImageListSearchField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit
) {
    Log.d("myTag", "ImageListScreenSearchField text: ${text}")
    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Text search icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { onTextChanged("") }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Text clear icon"
                )
            }
        }
    )
}