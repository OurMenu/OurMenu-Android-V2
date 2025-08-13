package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun FoodTagItem(
    label: String,
    iconId: Int,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    AssistChip(
        onClick = {
            // 클릭 이벤트 처리
            onSelected()
        },
        label = {
            Text(
                text = label,
                style = ourMenuTypography().pretendard_700_16
            )
        },
        leadingIcon = {
                Icon(
                    painterResource(iconId),
                    contentDescription = null,
                    tint = if(isSelected) NeutralWhite else Neutral900
                )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isSelected) Primary500Main else NeutralWhite,
            labelColor = if (isSelected) NeutralWhite else Neutral900
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun FoodTagPreview() {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    FoodTagItem(
        label = "밥",
        iconId = R.drawable.ic_tag_rice,
        isSelected = isSelected,
        onSelected = {
            isSelected = !isSelected
        }
    )
}