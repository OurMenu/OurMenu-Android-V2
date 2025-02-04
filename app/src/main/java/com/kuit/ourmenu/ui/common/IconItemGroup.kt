package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.item.IconItem
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IconItemGroup(
    modifier: Modifier = Modifier,
    groupLabel: String,
    icons: List<Int>,
    onIconSelect: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = groupLabel,
            style = ourMenuTypography().pretendard_500_14,
        )
        Spacer(modifier = modifier.height(8.dp))
        FlowRow(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            maxItemsInEachRow = 6
        ) {
            icons.forEach { img ->
                IconItem(
                    iconId = img,
                ) {
                    // 아이콘 클릭시 수행할 작업

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconItemGroupPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        IconItemGroup(
            groupLabel = "아이콘",
            icons = listOf(
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
                R.drawable.ic_tag_rice,
            )
        ) {
            // 아이콘 클릭시 수행할 작업
        }
    }
}