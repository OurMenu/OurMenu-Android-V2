package com.kuit.ourmenu.ui.common.chip

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun TagChip(
    modifier: Modifier = Modifier,
    tagIcon: Int,
    tagName: String,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    FilterChip(
        enabled = enabled,
        selected = selected,
        modifier = modifier
            .height(32.dp),
        onClick = onClick,
        label = {
            Text(
                text = tagName,
                style = ourMenuTypography().pretendard_700_16.copy(
                    lineHeight = 24.sp
                )
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                painter = painterResource(tagIcon),
                contentDescription = null,
            )
        },
        // disabled, selected -> primary500Main, enabled, default -> neutralWhite
        colors = FilterChipDefaults.filterChipColors().copy(
            containerColor = NeutralWhite,
            labelColor = Neutral900,
            leadingIconColor = Neutral900,
            disabledContainerColor = Primary500Main,
            disabledLabelColor = NeutralWhite,
            disabledLeadingIconColor = NeutralWhite,
            selectedContainerColor = Primary500Main,
            disabledSelectedContainerColor = Primary500Main,
            selectedLabelColor = NeutralWhite,
            selectedLeadingIconColor = NeutralWhite,
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled,
            selected = selected,
            borderColor = Neutral300,
            selectedBorderColor = Primary500Main,
            disabledBorderColor = Primary500Main

        ),
        shape = RoundedCornerShape(12.dp)
    )

}

@Preview
@Composable
private fun DefaultTagChipPrev() {
    TagChip(
        tagIcon = R.drawable.ic_tag_rice,
        tagName = "밥",
        onClick = { },
    )
}

@Preview
@Composable
private fun SelectedTagChipPrev() {
    TagChip(
        tagIcon = R.drawable.ic_tag_rice,
        tagName = "밥",
        onClick = { },
        selected = true,
        enabled = true
    )
}

@Preview
@Composable
private fun DisableTagChipPrev() {
    TagChip(
        tagIcon = R.drawable.ic_tag_rice,
        tagName = "밥",
        onClick = { },
        selected = true,
        enabled = false
    )
}