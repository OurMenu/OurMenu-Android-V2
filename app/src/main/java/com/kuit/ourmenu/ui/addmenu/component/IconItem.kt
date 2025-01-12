package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary300
import com.kuit.ourmenu.ui.theme.Primary500Main

@Composable
fun IconItem(
    iconId: Int,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .clickable { onSelected() },
        color = if (isSelected) Primary500Main else Primary300 ,
        contentColor = if (isSelected) Color.White else Color.Black
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = "icon",
                tint = NeutralWhite,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview
@Composable
private fun IconItemPreview() {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    IconItem(
        iconId = R.drawable.ic_tag_rice,
        isSelected = isSelected,
        onSelected = {
            isSelected = !isSelected
        }
    )
}