package com.kuit.ourmenu.ui.menuFolder.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomHalfWidthButton
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.common.chip.TagChipGroup
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    modifier: Modifier = Modifier,
    categoryTagList: List<Pair<Int, String>>,
    nationalityTagList: List<Pair<Int, String>>,
    tasteTagList: List<Pair<Int, String>>,
    occasionTagList: List<Pair<Int, String>>,
    onSelectedTagsChange: (List<String>) -> Unit,
    onApplyButtonClick: () -> Unit,
) {
    // toast를 위한 context
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // 가격 범위 설정
    val minPrice = 0f
    val maxPrice = 50_000f
    var priceRange by remember { mutableStateOf(minPrice..maxPrice) }
    val stepSize = 5000f // 5000원 단위 조정

    // 각 항목별로 선택된 태그 상태를 개별적으로 관리
    var selectedCategoryTag by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedNationalityTag by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedTasteTag by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedOccasionTag by rememberSaveable { mutableStateOf<String?>(null) }

    fun updateSelectedTags() {
        val selectedTags = listOfNotNull(
            selectedCategoryTag,
            selectedNationalityTag,
            selectedTasteTag,
            selectedOccasionTag
        )
        onSelectedTagsChange(selectedTags)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.filtering),
                        style = ourMenuTypography().pretendard_700_16
                    )
                    Text(
                        text = stringResource(R.string.select_one),
                        style = ourMenuTypography().pretendard_600_14,
                        color = Neutral500
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                ) {
                    // 종류
                    item {
                        TagChipGroup(
                            groupLabel = stringResource(R.string.type),
                            tags = categoryTagList,
                            selectedTags = listOfNotNull(selectedCategoryTag),
                        ) { tag ->
                            if (selectedCategoryTag != null && selectedCategoryTag != tag) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(context.getString(R.string.tag_max_one))
                                }
                            } else {
                                selectedCategoryTag = if (selectedCategoryTag == tag) null else tag
                                updateSelectedTags()
                            }
                        }
                    }

                    // 나라 별 음식
                    item {
                        TagChipGroup(
                            groupLabel = stringResource(R.string.nationality),
                            tags = nationalityTagList,
                            selectedTags = listOfNotNull(selectedNationalityTag),
                        ) { tag ->
                            if (selectedNationalityTag != null && selectedNationalityTag != tag) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(context.getString(R.string.tag_max_one))
                                }
                            } else {
                                selectedNationalityTag =
                                    if (selectedNationalityTag == tag) null else tag
                                updateSelectedTags()
                            }
                        }
                    }

                    // 맛
                    item {
                        TagChipGroup(
                            groupLabel = stringResource(R.string.taste),
                            tags = tasteTagList,
                            selectedTags = listOfNotNull(selectedTasteTag),
                        ) { tag ->
                            if (selectedTasteTag != null && selectedTasteTag != tag) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(context.getString(R.string.tag_max_one))
                                }
                            } else {
                                selectedTasteTag = if (selectedTasteTag == tag) null else tag
                                updateSelectedTags()
                            }
                        }
                    }

                    // 상황
                    item {
                        TagChipGroup(
                            groupLabel = stringResource(R.string.occasion),
                            tags = occasionTagList,
                            selectedTags = listOfNotNull(selectedOccasionTag),
                        ) { tag ->
                            if (selectedOccasionTag != null && selectedOccasionTag != tag) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(context.getString(R.string.tag_max_one))
                                }
                            } else {
                                selectedOccasionTag = if (selectedOccasionTag == tag) null else tag
                                updateSelectedTags()
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(12.dp)) }

                    // 가격 슬라이더
                    item {
                        Text(
                            text = stringResource(R.string.price_range_title),
                            style = ourMenuTypography().pretendard_400_14,
                            color = Neutral900
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(
                                R.string.price_range,
                                priceRange.start.toInt(),
                                priceRange.endInclusive.toInt()
                            ),
                            style = ourMenuTypography().pretendard_700_18
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        RangeSlider(
                            value = priceRange,
                            onValueChange = { newValue ->
                                val adjustedStart =
                                    (Math.round(newValue.start / stepSize) * stepSize).coerceAtLeast(
                                        minPrice
                                    )
                                val adjustedEnd =
                                    (Math.round(newValue.endInclusive / stepSize) * stepSize).coerceAtMost(
                                        maxPrice
                                    )

                                priceRange = adjustedStart..adjustedEnd // 5000 단위 반올림 적용
                            },
                            valueRange = minPrice..maxPrice,
                            steps = ((maxPrice - minPrice) / stepSize - 1).toInt(), // 5000원 단위로 이동
                            colors = SliderDefaults.colors(
                                thumbColor = Primary500Main,
                                activeTrackColor = Primary500Main,
                                inactiveTrackColor = Neutral300,
                                activeTickColor = Primary500Main,
                                inactiveTickColor = Neutral300
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp),
                            startThumb = {
                                SliderDefaults.Thumb(
                                    interactionSource = remember { MutableInteractionSource() },
                                    colors = SliderDefaults.colors(
                                        thumbColor = Primary500Main
                                    ),
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                )
                            },
                            endThumb = {
                                SliderDefaults.Thumb(
                                    interactionSource = remember { MutableInteractionSource() },
                                    colors = SliderDefaults.colors(
                                        thumbColor = Primary500Main
                                    ),
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                )
                            },
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomHalfWidthButton(
                    modifier = modifier.weight(1f),
                    containerColor = Neutral400,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.reset)
                ) {
                    // 모든 선택된 필터 초기화
                    selectedCategoryTag = null
                    selectedNationalityTag = null
                    selectedTasteTag = null
                    selectedOccasionTag = null
                    priceRange = minPrice..maxPrice

                    onSelectedTagsChange(emptyList())
                }
                Spacer(modifier = modifier.width(12.dp))
                BottomHalfWidthButton(
                    modifier = modifier.weight(1f),
                    containerColor = Primary500Main,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.apply)
                ) {
                    onApplyButtonClick()
                }
            }
        }

        OurSnackbarHost(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            hostState = snackbarHostState,
            isChecked = false,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterBottomSheetPreview() {
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

    FilterBottomSheet(
        categoryTagList = categoryTags,
        nationalityTagList = nationalityTags,
        tasteTagList = tasteTags,
        occasionTagList = occasionTags,
        onSelectedTagsChange = { newSelectedTags -> selectedTags = newSelectedTags },
        onApplyButtonClick = {}
    )
}