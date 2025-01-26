package com.kuit.ourmenu.ui.menuinfo.component.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.ui.common.chip.MenuFolderChip
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ExtensionUtils.toWon

@Composable
fun MenuInfoMapContent(
    modifier: Modifier = Modifier,
    menuInfoData: MenuInfoDummyData
) {
    val menuFolderTitle =
        when (menuInfoData.menuFolderList.size) {
            0 -> "기본 메뉴판"
            1 -> menuInfoData.menuFolderList[0]
            else -> "${menuInfoData.menuFolderList[0]} +${menuInfoData.menuFolderList.size - 1}"
        }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = menuInfoData.menuTitle,
                    style = ourMenuTypography().pretendard_700_20.copy(
                        lineHeight = 32.sp,
                        color = Neutral900,
                    ),
                )
                Text(
                    text = menuInfoData.menuPrice.toWon(),
                    style = ourMenuTypography().pretendard_600_32.copy(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        color = Neutral700
                    ),
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            MenuFolderChip(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                menuFolderTitle = menuFolderTitle
            )
        }
        Text(
            text = menuInfoData.store,
            style = ourMenuTypography().pretendard_600_14.copy(
                lineHeight = 12.sp,
                color = Neutral500
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapContentPreview() {
    MenuInfoMapContent(
        menuInfoData = MenuInfoDummyData.dummyData
    )
}