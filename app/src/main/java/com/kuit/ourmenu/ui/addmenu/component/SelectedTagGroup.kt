package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.addmenu.component.item.SelectedTagItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedTagGroup(
    modifier: Modifier = Modifier,
    tags: List<String>,
    onTagClick: (String) -> Unit
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = Int.MAX_VALUE
    ) {
        tags.forEach { it ->
            SelectedTagItem(
                label = it
            ){
                onTagClick(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectedTagGroupPreview() {
    SelectedTagGroup(
        tags = listOf(
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
            "밥밥밥",
        )
    ) {

    }
}