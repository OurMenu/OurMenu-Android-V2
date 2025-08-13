package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
    onIconSelect: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = groupLabel,
            style = ourMenuTypography().pretendard_700_16
        )
        Spacer(modifier = modifier.height(20.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(6), // 한 행에 6개씩 배치
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(icons.size) { index ->
                IconItem(
                    iconId = icons[index],
                ) {
                    onIconSelect(index)
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