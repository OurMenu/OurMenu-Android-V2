package com.kuit.ourmenu.ui.menuinfo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.ui.common.MenuFolderChip
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuInfoAdditionalContent(
    menuInfoData: MenuInfoDummyData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Menu Folder",
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Neutral500
            ),
            modifier = Modifier.padding(bottom = 6.dp)
        )
        // TODO : Grid or Staggered Grid
        MenuFolderChip()


    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoAdditionalContentPreview() {
    MenuInfoAdditionalContent(
        menuInfoData = MenuInfoDummyData.dummyData
    )
}