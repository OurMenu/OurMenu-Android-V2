package com.kuit.ourmenu.ui.menuinfo.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.menuinfo.response.MenuInfoResponse
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuInfoChipContent(
    menuInfoData: MenuInfoResponse
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.menu_folder),
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Neutral500
            ),
        )

        MenuInfoFolderChipGrid(
            menuFolderList = menuInfoData.menuFolders
        )

        Text(
            text = stringResource(R.string.tag_eng),
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Neutral500
            ),
            modifier = Modifier.padding(top = 12.dp)
        )
            // TODO: chip grid 구현
//        MenuInfoTagChipGrid(
//            defaultTagList = menuInfoData.defaultTagList,
//            customTagList = menuInfoData.customTagList
//        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoChipContentPreview() {
//    MenuInfoChipContent(
//        menuInfoData = MenuInfoDummyData.dummyData
//    )
}