package com.kuit.ourmenu.ui.addmenu.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomHalfWidthButton
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.common.chip.TagChipGroup
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch

@Composable
fun TagSelectBottomSheet(
    modifier: Modifier = Modifier,
    categoryTagList: List<Pair<Int, String>>,
    nationalityTagList: List<Pair<Int, String>>,
    tasteTagList: List<Pair<Int, String>>,
    occasionTagList: List<Pair<Int, String>>,
    selectedTagList: List<String>,
    onSelectedTagsChange: (List<String>) -> Unit,
    onApplyButtonClick: () -> Unit,
    onTagClick: (String) -> Unit
) {
    // toast를 위한 context
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.food_tag),
                        style = ourMenuTypography().pretendard_700_16
                    )
                    Text(
                        text = stringResource(R.string.multiple_choice_available),
                        style = ourMenuTypography().pretendard_600_14,
                        color = Neutral500
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                //종류
                TagChipGroup(
                    groupLabel = "종류",
                    tags = categoryTagList,
                    selectedTags = selectedTagList,
                ) { tag ->
                    if (selectedTagList.size >= 12 && tag !in selectedTagList) {
                        scope.launch {
                            snackbarHostState.showSnackbar(context.getString(R.string.tag_number_warning))
                        }
                    } else {
                        onTagClick(tag)
                    }
                }
                //나라 별 음식
                TagChipGroup(
                    groupLabel = "나라 별 음식",
                    tags = nationalityTagList,
                    selectedTags = selectedTagList,
                ) { tag ->
                    if (selectedTagList.size >= 12 && tag !in selectedTagList) {
                        scope.launch {
                            snackbarHostState.showSnackbar(context.getString(R.string.tag_number_warning))
                        }
                    } else {
                        onTagClick(tag)
                    }
                }
                //맛
                TagChipGroup(
                    groupLabel = "맛",
                    tags = tasteTagList,
                    selectedTags = selectedTagList,
                ) { tag ->
                    if (selectedTagList.size >= 12 && tag !in selectedTagList) {
                        scope.launch {
                            snackbarHostState.showSnackbar(context.getString(R.string.tag_number_warning))
                        }
                    } else {
                        onTagClick(tag)
                    }
                }
                //상황
                TagChipGroup(
                    groupLabel = "상황",
                    tags = occasionTagList,
                    selectedTags = selectedTagList,
                ) { tag ->
                    if (selectedTagList.size >= 12 && tag !in selectedTagList) {
                        scope.launch {
                            snackbarHostState.showSnackbar(context.getString(R.string.tag_number_warning))
                        }
                    } else {
                        onTagClick(tag)
                    }
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomHalfWidthButton(
                    containerColor = Neutral400,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.reset)
                ) {
                    // List 비우기
                    onSelectedTagsChange(emptyList())
                }
                Spacer(modifier = modifier.width(12.dp))
                BottomHalfWidthButton(
                    containerColor = Primary500Main,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.apply)
                ) {
                    if (selectedTagList.size >= 12) {
                        scope.launch {
                            snackbarHostState.showSnackbar(context.getString(R.string.tag_number_warning))
                        }
                    } else {
                        //아이콘 선택으로 이동
                        onApplyButtonClick()
                    }
                }
            }
        }

        OurSnackbarHost(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            hostState = snackbarHostState,
            isChecked = false,
            message = stringResource(R.string.tag_number_warning)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TagSelectBottomSheetPreview() {
    val categoryTags = listOf(
        R.drawable.ic_tag_rice to "밥",
        R.drawable.ic_tag_rice to "빵",
        R.drawable.ic_tag_rice to "면",
        R.drawable.ic_tag_rice to "고기",
        R.drawable.ic_tag_rice to "생선",
        R.drawable.ic_tag_rice to "카페",
        R.drawable.ic_tag_rice to "디저트",
        R.drawable.ic_tag_rice to "패스트푸드",
    )
    val nationalityTags = listOf(
        R.drawable.ic_tag_rice to "한식",
        R.drawable.ic_tag_rice to "중식",
        R.drawable.ic_tag_rice to "일식",
        R.drawable.ic_tag_rice to "양식",
        R.drawable.ic_tag_rice to "아시안",
    )
    val tasteTags = listOf(
        R.drawable.ic_tag_rice to "매콤함",
        R.drawable.ic_tag_rice to "달달함",
        R.drawable.ic_tag_rice to "시원함",
        R.drawable.ic_tag_rice to "뜨끈함",
        R.drawable.ic_tag_rice to "얼큰함",
    )
    val occasionTags = listOf(
        R.drawable.ic_tag_rice to "혼밥",
        R.drawable.ic_tag_rice to "비즈니스 미팅",
        R.drawable.ic_tag_rice to "친구 약속",
        R.drawable.ic_tag_rice to "데이트",
        R.drawable.ic_tag_rice to "밥약",
        R.drawable.ic_tag_rice to "단체",
    )
    var selectedTags by rememberSaveable { mutableStateOf(listOf<String>()) }

    TagSelectBottomSheet(
        categoryTagList = categoryTags,
        nationalityTagList = nationalityTags,
        tasteTagList = tasteTags,
        occasionTagList = occasionTags,
        selectedTagList = selectedTags,
        onSelectedTagsChange = { newSelectedTags -> selectedTags = newSelectedTags },
        onApplyButtonClick = {}
    ) { tag ->
        if (selectedTags.contains(tag)) {
            selectedTags -= tag
        } else {
            selectedTags += tag
        }
    }
}