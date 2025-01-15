package com.kuit.ourmenu.ui.home.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kuit.ourmenu.ui.theme.NeutralWhite

@Composable
fun HomePopUpDialog(modifier: Modifier = Modifier) {

    Dialog(
        onDismissRequest = {
            // TODO : Dismiss Dialog Event
        },
    ) {
        Surface(
            modifier = Modifier
                .shadow(elevation = 8.dp)
                .fillMaxWidth()
                .height(431.dp)
                .background(
                    color = NeutralWhite,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 20.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 24.dp
                    )
            ) {
                HomeDialogAssets(
                    onDiceClick = {
                        // TODO : Dice Click Event
                    },
                    onCloseClick = {
                        // TODO : Close Click Event
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0x111111
)
@Composable
private fun HomePopUpDialogPreview() {
    HomePopUpDialog()
}