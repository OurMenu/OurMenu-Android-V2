package com.kuit.ourmenu.ui.menuinfo.component.map

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.chip.TagChip
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MenuInfoMapTagContent(
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
private fun MenuInfoMapTagContentPreview() {
    MenuInfoMapTagContent(
        defaultTagList = MenuInfoDummyData.dummyData.defaultTagList,
        customTagList = MenuInfoDummyData.dummyData.customTagList
    )
}