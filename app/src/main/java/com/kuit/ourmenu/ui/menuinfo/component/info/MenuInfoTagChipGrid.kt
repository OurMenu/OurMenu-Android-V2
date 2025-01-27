package com.kuit.ourmenu.ui.menuinfo.component.info

import androidx.compose.foundation.layout.Column
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
fun MenuInfoTagChipGrid(
    modifier: Modifier = Modifier,
    defaultTagList: List<MenuInfoTag> = listOf(),
    customTagList: List<MenuInfoTag> = listOf(),
) {

    Column {

        FlowRow(
            modifier = Modifier
                .padding(top = 2.dp)
        ) {
            defaultTagList.forEachIndexed { index, tag ->
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
                if (index != defaultTagList.lastIndex) {
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                }
            }
        }
        FlowRow {
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
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun MenuInfoFolderChipGridPreview() {
    MenuInfoTagChipGrid(
        defaultTagList = MenuInfoDummyData.dummyData.defaultTagList,
        customTagList = MenuInfoDummyData.dummyData.customTagList
    )
}


