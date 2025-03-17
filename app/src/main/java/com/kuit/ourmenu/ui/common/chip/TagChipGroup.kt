package com.kuit.ourmenu.ui.common.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagChipGroup(
    modifier: Modifier = Modifier,
    groupLabel: String,
    tags: List<Pair<Int, String>>,
    selectedTags: List<String>,
    onTagClick: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()){
        Text(
            text = groupLabel,
            style = ourMenuTypography().pretendard_500_14,
        )
        Spacer(modifier = modifier.height(8.dp))
        FlowRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = Int.MAX_VALUE
        ) {
            tags.forEach { (img, name) ->
                TagChip(
                    tagIcon = img,
                    tagName = name,
                    selected = selectedTags.contains(name),
                ) {
                    //태그 클릭시 수행할 작업
                    onTagClick(name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TagChipGroupPreview() {
    val tags = listOf(
        R.drawable.ic_tag_rice to "밥",
        R.drawable.ic_tag_rice to "빵",
        R.drawable.ic_tag_rice to "면",
        R.drawable.ic_tag_rice to "고기",
        R.drawable.ic_tag_rice to "생선",
        R.drawable.ic_tag_rice to "카페",
        R.drawable.ic_tag_rice to "디저트",
        R.drawable.ic_tag_rice to "패스트푸드",
    )
    var selectedTags by rememberSaveable { mutableStateOf(listOf<String>()) }

    TagChipGroup(
        groupLabel = "종류",
        tags = tags,
        selectedTags = selectedTags,
    ){ tag ->
        if(selectedTags.contains(tag)){
            selectedTags -= tag
        }else{
            selectedTags += tag
        }
    }
}