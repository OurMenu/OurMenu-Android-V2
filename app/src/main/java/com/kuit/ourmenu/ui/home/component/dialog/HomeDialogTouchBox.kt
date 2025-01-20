package com.kuit.ourmenu.ui.home.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun HomeDialogTouchBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_home_dialog_touch),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Row(
            modifier
                .padding(horizontal = 11.dp)
                .padding(top = 3.dp, bottom = 6.dp)
        ) {
            Text(
                text = "터치!",
                style = ourMenuTypography().pretendard_500_14.copy(
                    color = Neutral700
                ),
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = "\uD83D\uDC49",
                style = ourMenuTypography().pretendard_500_14.copy(
                    color = Neutral700
                )
            )
        }
    }
}