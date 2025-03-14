package com.kuit.ourmenu.ui.addmenu.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.SelectedTagGroup
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.IconSelectBottomSheet
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.TagSelectBottomSheet
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuBackButtonTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuTagScreen(modifier: Modifier = Modifier) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showTagBottomSheet by rememberSaveable { mutableStateOf(true) }
    var memoTitle by rememberSaveable { mutableStateOf("") }
    var memoBody by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()
    var enableAddButton by rememberSaveable { mutableStateOf(false) }

    // 선택된 태그 개수를 위한 변수
    var selectedTags by rememberSaveable { mutableStateOf(listOf<String>()) }
    var selectedIcon by rememberSaveable { mutableStateOf(0) }

    // 예시를 위한 dummy list들
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
    // 아이콘 bottom sheet에 사용할 list
    val iconList = listOf(
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
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

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(scaffoldState.bottomSheetState) {
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }
            .collect { state ->
                Log.d("AddMenuTagScreen", "BottomSheetState changed: $state")
            }
    }

    //메모가 비어있지 않고, 태그와 아이콘이 선택된 경우에 메뉴 등록하기 버튼 활성화
    enableAddButton = memoTitle.isNotBlank() && memoBody.isNotBlank() && selectedTags.isNotEmpty()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            OurMenuBackButtonTopAppBar {
                Text(
                    text = stringResource(R.string.add_menu),
                    style = ourMenuTypography().pretendard_600_18,
                    color = Neutral900,
                )
            }
        },
        sheetContainerColor = Color.White,
        sheetContent = {
            if (showTagBottomSheet) {
                //음색 태그 선택하는 bottom sheet
                TagSelectBottomSheet(
                    categoryTagList = categoryTags,
                    nationalityTagList = nationalityTags,
                    tasteTagList = tasteTags,
                    occasionTagList = occasionTags,
                    selectedTagList = selectedTags,
                    onSelectedTagsChange = { newSelectedTags -> selectedTags = newSelectedTags },
                    onApplyButtonClick = {
                        showTagBottomSheet = false
                    }
                ) { tag ->
                    if (selectedTags.contains(tag)) {
                        selectedTags -= tag
                    } else {
                        selectedTags += tag
                    }
                }
            } else {
                //아이콘 선택하는 bottom sheet
                IconSelectBottomSheet(
                    iconList = iconList,
                    onCancel = {
                        showTagBottomSheet = true
                        showBottomSheet = true
                    },
                    onConfirm = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.partialExpand()
                            showBottomSheet = false
                            showTagBottomSheet = true
                        }
                    }
                ){ index ->
                    selectedIcon = index
                    Log.d("AddMenuTagScreen", "Selected icon index: $index")
                }
            }
        },
        //태그 고르기 눌러야 보이도록
        sheetPeekHeight = if (showBottomSheet) {
            if (showTagBottomSheet){
                (LocalConfiguration.current.screenHeightDp - 20).dp
            } else 400.dp
        } else 0.dp,
        sheetDragHandle = {
            BottomSheetDragHandle()
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = modifier) {
                //태그
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.tag),
                        style = ourMenuTypography().pretendard_600_14
                    )
                    Button(
                        onClick = {
                            showTagBottomSheet = true
                            showBottomSheet = true
                        },
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Primary500Main),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary500Main,
                            contentColor = NeutralWhite
                        ),
                        modifier = modifier.size(128.dp, 32.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_filter_white_16),
                                contentDescription = "select tag",
                                tint = Color.Unspecified
                            )
                            Text(
                                text = stringResource(R.string.choose_tag),
                                style = ourMenuTypography().pretendard_700_16,
                                color = NeutralWhite
                            )
                        }
                    }
                }
                SelectedTagGroup(
                    modifier = modifier
                        .padding(
                            vertical = if (selectedTags.isEmpty()) 0.dp else 20.dp,
                        ),
                    tags = selectedTags
                ) {
                    selectedTags -= it
                }

                //메모
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.memo),
                    style = ourMenuTypography().pretendard_600_14
                )
                Spacer(modifier = modifier.height(4.dp))
                CustomTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
                    text = memoTitle,
                    onTextChange = { memoTitle = it },
                    shape = RoundedCornerShape(8.dp),
                    paddingValues = PaddingValues(28.dp, 12.dp),
                    containerColor = Neutral100,
                    placeHolder = {
                        Text(
                            text = stringResource(R.string.type_title),
                            style = ourMenuTypography().pretendard_500_14,
                            color = Neutral500
                        )
                    },
                    textStyle = ourMenuTypography().pretendard_500_14.copy(color = Neutral700)
                )
                Spacer(modifier = modifier.height(8.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(88.dp)
                        .background(Neutral100)
                        .border(1.dp, Neutral300, RoundedCornerShape(8.dp))
                        .verticalScroll(scrollState),
                ) {
                    CustomTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(88.dp),
                        text = memoBody,
                        singleLine = false,
                        onTextChange = { memoBody = it },
                        shape = RoundedCornerShape(8.dp),
                        paddingValues = PaddingValues(28.dp, 12.dp),
                        containerColor = Neutral100,
                        placeHolder = {
                            Text(
                                text = stringResource(R.string.type_body),
                                style = ourMenuTypography().pretendard_500_12,
                                color = Neutral500,
                            )
                        },
                        textStyle = ourMenuTypography().pretendard_500_14.copy(color = Neutral700)
                    )
                }

                //아이콘
                Spacer(modifier = modifier.height(20.dp))
                Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = stringResource(R.string.icon),
                        style = ourMenuTypography().pretendard_600_14
                    )
                    Icon(
                        modifier = modifier
                            .clickable {
                                showTagBottomSheet = false
                                showBottomSheet = true
                            },
                        painter = painterResource(iconList[selectedIcon]),
                        contentDescription = "selected icon",
                        tint = Color.Unspecified,
                    )
                }
            }
            // TODO: 넓이 늘이기
            BottomFullWidthButton(
                modifier = modifier
                    .padding(bottom = 20.dp),
                containerColor = if (enableAddButton) Primary500Main else Neutral100,
                contentColor = if (enableAddButton) NeutralWhite else Neutral500,
                text = "메뉴 등록하기"
            ) {
                // TODO: 등록 api 호출
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuTagScreenPreview() {
    AddMenuTagScreen(

    )
}