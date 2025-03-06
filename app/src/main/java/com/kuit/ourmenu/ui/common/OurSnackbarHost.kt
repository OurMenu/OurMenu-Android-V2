package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral50
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun OurSnackbarHost(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
    isChecked: Boolean = false,
    message: String
) {
//    Snackbar()
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
    ) {
        OurSnackbar(
            modifier = modifier,
            isChecked = isChecked,
            message = message
        )
    }
}


@Composable
private fun OurSnackbar(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    message: String
) {
    Card(
        modifier = modifier
            .background(color = Neutral50, shape = RoundedCornerShape(size = 8.dp)),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(size = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeutralWhite
        )
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .padding(horizontal = 40.dp)
                .height(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = if (isChecked) painterResource(R.drawable.ic_snackbar_check)
                else painterResource(R.drawable.ic_snackbar_error),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                text = message,
                style = ourMenuTypography().pretendard_500_12.copy(color = Neutral700)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OurSnackbarPreview() {
    Column(
        modifier = Modifier
            .width(360.dp)
            .height(400.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        OurSnackbar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            message = "최대 10자까지 가능해요!",
            isChecked = false
        )

        OurSnackbar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            message = "계정 생성 완료",
            isChecked = true
        )
    }


}