package com.kuit.ourmenu.ui.home.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R

@Composable
fun HomeDialogAssets(
    onDiceClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row {
            HomeDialogTouchBox(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .height(32.dp)
                    .weight(72f)
            )
            Spacer(
                modifier = Modifier.weight(156f)
            )
            Icon(
                painter = painterResource(R.drawable.ic_close_24_n400),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(24f)
                    .clickable { onCloseClick() }
            )
        }
        Image(
            painter = painterResource(R.drawable.img_popup_dice),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 27.dp)
                .padding(horizontal = 82.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable { onDiceClick() }
        )
        // TODO : Add Dice shadow asset
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev() {
    HomeDialogAssets()
}