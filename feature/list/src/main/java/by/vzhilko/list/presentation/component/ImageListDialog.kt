package by.vzhilko.list.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import by.vzhilko.core.ui.theme.white
import by.vzhilko.list.R
import by.vzhilko.core.R as coreR

@Composable
fun ImageListDialog(
    onDismissDialog: () -> Unit = {},
    onPositiveButtonClick: () -> Unit = {},
    onNegativeButtonClick: () -> Unit = {}
) {
    Dialog(onDismissRequest = { onDismissDialog() }) {
        ConstraintLayout(
            modifier = Modifier
                .background(color = white)
                .padding(16.dp)
        ) {
            val (titleRef, messageRef, spacerRef, positiveButtonRef, negativeButtonRef) = createRefs()

            Text(
                text = stringResource(coreR.string.app_name),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )
            Text(
                text = stringResource(R.string.image_list_details_view_offer_message),
                modifier = Modifier.constrainAs(messageRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(parent.start)
                }
            )

            Button(
                modifier = Modifier.constrainAs(positiveButtonRef) {
                    top.linkTo(messageRef.bottom)
                    end.linkTo(parent.end)
                },
                onClick = { onPositiveButtonClick() }
            ) {
                Text(text = stringResource(coreR.string.ok_caption))
            }
            Spacer(modifier = Modifier
                .width(16.dp)
                .constrainAs(spacerRef) {
                    top.linkTo(messageRef.bottom)
                    end.linkTo(positiveButtonRef.start)
                })
            Button(
                modifier = Modifier.constrainAs(negativeButtonRef) {
                    top.linkTo(messageRef.bottom)
                    end.linkTo(spacerRef.start)
                },
                onClick = { onNegativeButtonClick() }
            ) {
                Text(text = stringResource(coreR.string.no_caption))
            }
        }
    }
}

@Preview
@Composable
fun ImageListDialogPreview() {
    ImageListDialog()
}