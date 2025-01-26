package com.kuit.ourmenu.ui.menuinfo.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.chip.MenuFolderChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MenuInfoFolderChipGrid(
    modifier: Modifier = Modifier,
    menuFolderList: List<String> = listOf()
) {

    FlowRow(
        modifier = Modifier
            .padding(top = 2.dp)
    ) {
        menuFolderList.forEach { title ->
            MenuFolderChip(
                modifier = Modifier.padding(
                    top = 4.dp,
                ),
                menuFolderTitle = title
            )
            if (title != menuFolderList.last()) {
                Spacer(modifier = Modifier.padding(end = 4.dp))
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
    MenuInfoFolderChipGrid(
        menuFolderList = listOf(
            "메뉴판1",
            "메뉴판22",
            "메뉴판333",
            "메뉴판4444",
            "메뉴판55555",
            "메뉴판666666",
            "메뉴판7777777",
            "메뉴판88888888",
            "메뉴판999999999"
        )
    )
}