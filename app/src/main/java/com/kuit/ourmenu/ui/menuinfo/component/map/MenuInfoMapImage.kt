package com.kuit.ourmenu.ui.menuinfo.component.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData

@Composable
fun MenuInfoMapImage(
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

@Preview
@Composable
private fun MenuIInfoMapImagePreview() {
    MenuInfoMapImage(
        menuInfoData = MenuInfoDummyData.dummyData
    )
}