package com.kuit.ourmenu.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.MapDetailResponse
import com.kuit.ourmenu.data.model.map.response.MenuFolderInfo
import com.kuit.ourmenu.ui.common.chip.MenuFolderChip
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ExtensionUtil.toWon

@Composable
fun MenuInfoBottomSheetContent(
    modifier: Modifier = Modifier,
    menuInfoData: MapDetailResponse
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        MenuInfoContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            menuInfoData = menuInfoData
        )

        MenuInfoImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            menuInfoData = menuInfoData
        )

        MenuInfoTagContent(
            modifier = Modifier
                .fillMaxWidth(),
            menuTags = menuInfoData.menuTagImgUrls
        )
    }
}

@Composable
fun MenuInfoContent(
    modifier: Modifier = Modifier,
    menuInfoData: MapDetailResponse
) {
    val menuFolderTitle = menuInfoData.menuFolderInfo.menuFolderTitle

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
                    text = with(menuInfoData.menuTitle) {
                        if (length > 10) take(10) + "..." else this
                    },
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
                menuFolderIconImgUrl = menuInfoData.menuFolderInfo.menuFolderIconImgUrl,
                menuFolderTitle = menuFolderTitle
            )
        }
        Text(
            text = "응답에 가게명 누락", // TODO: 가게명 처리
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
    menuInfoData: MapDetailResponse
) {
    val imgUrls = menuInfoData.menuImgUrls

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        for (i in 0 until 3) {
            Image(
                painter = if (i < imgUrls.size && imgUrls[i].isNotEmpty()) {
                    rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalPlatformContext.current)
                            .data(imgUrls[i])
                            .size(104, 80)
                            .build()
                    )
                } else {
                    painterResource(R.drawable.img_dummy_menu)
                },
                contentDescription = null,
                modifier = Modifier
                    .size(104.dp, 80.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
//            if (i != 2) Spacer(modifier = Modifier.padding(end = 4.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MenuInfoTagContent(
    modifier: Modifier = Modifier,
    menuTags: List<String>
) {
    FlowRow(modifier = modifier) {
        menuTags.forEach { tag ->
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(tag)
                    .size(96, 32)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(32.dp)
                    .padding(top = 4.dp, end = 4.dp),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoBottomSheetContentPreview() {
    MenuInfoBottomSheetContent(
        menuInfoData = MapDetailResponse(
            menuId = 1,
            menuTitle = "Test Menu",
            menuPrice = 10000,
            menuPinImgUrl = "pin",
            menuTagImgUrls = listOf("한식", "밥"),
            menuImgUrls = listOf(),
            menuFolderInfo = MenuFolderInfo(
                menuFolderTitle = "Test Store",
                menuFolderIconImgUrl = "icon",
                menuFolderCount = 1
            ),
            mapId = 1,
            mapX = 127.0,
            mapY = 37.0
        )
    )
}