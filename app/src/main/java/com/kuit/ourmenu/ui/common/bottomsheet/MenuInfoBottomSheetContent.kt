package com.kuit.ourmenu.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.chip.MenuFolderChip
import com.kuit.ourmenu.ui.common.chip.TagChip
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoTag
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ExtensionUtil.toWon

@Composable
fun MenuInfoBottomSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        MenuInfoContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            menuInfoData = MenuInfoDummyData.dummyData
        )

        MenuInfoImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            menuInfoData = MenuInfoDummyData.dummyData
        )

        MenuInfoTagContent(
            modifier = Modifier
                .fillMaxWidth(),
            defaultTagList = MenuInfoDummyData.dummyData.defaultTagList,
            customTagList = MenuInfoDummyData.dummyData.customTagList
        )
    }
}

@Composable
fun MenuInfoContent(
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

@Composable
fun MenuInfoImage(
    modifier: Modifier = Modifier,
    menuInfoData: MenuInfoDummyData
) {
    val imgRes = menuInfoData.imgRes

    Row(modifier = modifier) {
        for (i in 0 until 3) {
            Image(
                painter = // 이 부분은 추후에 int == 0 대신 url.isNullOrEmpty()로 변경해야 함
                if (imgRes[i] == 0) painterResource(R.drawable.img_dummy_menu)
                else painterResource(imgRes[i]),
                contentDescription = null,
                modifier = Modifier
                    .weight(104f)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
            if (i != 2) Spacer(modifier = Modifier.padding(end = 4.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MenuInfoTagContent(
    modifier: Modifier = Modifier,
    defaultTagList: List<MenuInfoTag> = listOf(),
    customTagList: List<MenuInfoTag> = listOf(),
) {
    FlowRow(modifier = modifier) {
        defaultTagList.forEach { tag ->
            TagChip(
                modifier = Modifier.padding(
                    top = 4.dp,
                ),
                tagIcon = tag.tagIcon,
                tagName = tag.tagName,
                enabled = false,
                selected = true,
                onClick = { }
            )
            Spacer(modifier = Modifier.padding(end = 4.dp))
        }
        customTagList.forEachIndexed { index, tag ->
            TagChip(
                modifier = Modifier.padding(
                    top = 4.dp,
                ),
                tagIcon = tag.tagIcon,
                tagName = tag.tagName,
                onClick = { }
            )
            if (index != customTagList.lastIndex) {
                Spacer(modifier = Modifier.padding(end = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoBottomSheetContentPreview() {
    MenuInfoBottomSheetContent()
}