package com.kuit.ourmenu.ui.common.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuFolderChip(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    menuFolderTitle: String = ""
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Neutral300,
                shape = RoundedCornerShape(12.dp)
            )
            .background(NeutralWhite)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            painter = painterResource(id = R.drawable.img_menu_folder_dummy),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = menuFolderTitle,
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Neutral700
            )
        )
    }


}

@Preview
@Composable
private fun MenuFolderChipPreview() {
    MenuFolderChip(
        menuFolderTitle = "홍대 맛집"
    )
}