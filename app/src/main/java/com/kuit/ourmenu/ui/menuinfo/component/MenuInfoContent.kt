package com.kuit.ourmenu.ui.menuinfo.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuInfoContent(
    menuInfoData: MenuInfoDummyData
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = menuInfoData.menuTitle,
                style = ourMenuTypography().pretendard_700_48.copy(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    color = Neutral900
                ),
                modifier = Modifier
                    .padding(start = 20.dp, top = 1.dp)
            )
            Text(
                text = menuInfoData.menuPrice.toString(),
                style = ourMenuTypography().pretendard_600_32.copy(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = Neutral700
                ),
                modifier = Modifier
                    .padding(end = 20.dp, top = 1.dp)
                    .align(Alignment.TopEnd)
            )
        }
        Text(
            text = menuInfoData.store,
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = Neutral500
            ),
            modifier = Modifier
                .padding(start = 20.dp, top = 7.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoContentPreview() {
    MenuInfoContent(
        MenuInfoDummyData.dummyData
    )
}