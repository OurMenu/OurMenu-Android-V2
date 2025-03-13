package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SelectedTagItem(
    modifier: Modifier = Modifier,
    label: String,
    onTagClick: (String) -> Unit
) {
    AssistChip(
        onClick = {
            onTagClick(label)
        },
        label = {
            Text(
                text = label,
                style = ourMenuTypography().pretendard_700_16
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_addmenu_x),
                contentDescription = "x",
                tint = NeutralWhite
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Primary500Main,
            labelColor = NeutralWhite,
        ),
        border = BorderStroke(0.dp, Primary500Main),
    )
}

@Preview(showBackground = true)
@Composable
private fun SelectedTagItemPreview() {
    SelectedTagItem(
        label = "밥밥밥"
    ){
        // 클릭시 동작
    }
}