package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite

@Composable
fun IconItem(
    iconId: Int,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier.size(36.dp).
        clickable { onSelected() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_icon_ellipse),
            contentDescription = "icon ellipse",
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(iconId),
            contentDescription = "icon",
            tint = NeutralWhite,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
private fun IconItemPreview() {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    IconItem(
        iconId = R.drawable.ic_tag_rice,
        onSelected = {
            isSelected = !isSelected
        }
    )
}