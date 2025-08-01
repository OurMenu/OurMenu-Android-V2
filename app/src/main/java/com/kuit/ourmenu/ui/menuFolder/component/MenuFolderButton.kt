package com.kuit.ourmenu.ui.menuFolder.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderList
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun MenuFolderButton(
    menuFolder: MenuFolderList,
    isSwiped: Boolean, // 현재 버튼이 스와이프된 상태인지 확인
    onSwipe: () -> Unit, // 새로운 버튼이 스와이프될 때 호출
    onReset: () -> Unit, // 버튼이 닫히면 호출
    onButtonClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val density = LocalDensity.current
    val offset = remember {
        Animatable(initialValue = 0f)
    }
    val scope = rememberCoroutineScope()
    val maxSwipe = with(density) {
        128.dp.toPx() // 최대 스와이프 범위를 dp에서 px로 변환
    }

    LaunchedEffect(isSwiped) {
        if (!isSwiped) {
            offset.snapTo(0f)
        }
    }

    Box(
        modifier = Modifier
            .height(132.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
//                detectHorizontalDragGestures(
//                    onDragEnd = {
//                        scope.launch {
//                            if (offsetX < -80f) {
//                                onSwipe() // 다른 버튼을 닫고 이 버튼만 스와이프
//                                offsetX = -maxSwipe
//                            } else {
//                                offsetX = 0f
//                                onReset() // 스와이프가 닫히면 상태 초기화
//                            }
//                        }
//                    }
//                ) { change, dragAmount ->
//                    change.consume()
//                    offsetX = (offsetX + dragAmount).coerceIn(-maxSwipe, 0f)
//                }
                detectHorizontalDragGestures(
                    onHorizontalDrag = { _, dragAmount ->
                        scope.launch {
                            val newOffset = (offset.value + dragAmount)
                                .coerceIn(-maxSwipe, 0f)
                            offset.snapTo(newOffset)
                        }
                    },
                    onDragEnd = {
                        if (offset.value < -maxSwipe / 2) {
                            scope.launch {
                                offset.animateTo(-maxSwipe)
                            }
                            onSwipe()
                        } else {
                            scope.launch {
                                offset.animateTo(0f)
                            }
                            onReset()
                        }
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            MenuFolderDeleteButton(onDeleteClick)
            MenuFolderEditButton(onEditClick)
        }

        // 스와이프 상태일 때만 offset 적용
        Box(
            modifier = Modifier
//                .offset(x = if (isSwiped) offset.value.dp else 0.dp)
                .offset { IntOffset(offset.value.roundToInt(), 0) }

        ) {
            MenuFolderContent(
                onClick = onButtonClick,
                menuFolder = menuFolder
            )
        }
    }
}

@Composable
fun MenuFolderEditButton(onEditClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(end = 64.dp)
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
            .background(color = Neutral300)
            .clickable(onClick = onEditClick),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(modifier = Modifier.padding(end = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.edit),
                style = ourMenuTypography().pretendard_500_14,
                color = Neutral700,
            )
        }
    }
}

@Composable
fun MenuFolderDeleteButton(onDeleteClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
            .background(color = Primary500Main)
            .clickable(onClick = onDeleteClick),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Column(modifier = Modifier.padding(end = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_trashcan),
                contentDescription = "Delete",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.delete),
                style = ourMenuTypography().pretendard_500_14,
                color = NeutralWhite,
            )
        }
    }
}

@Composable
fun MenuFolderContent(
    onClick: () -> Unit = {},
    menuFolder: MenuFolderList,
) {
    val menuCount = menuFolder.menuIds.size

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = menuFolder.menuFolderImgUrl,
            contentDescription = "Folder Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onClick)
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(12.dp))
                .background(brush = gradientBrush())
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp, bottom = 12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = menuFolder.menuFolderIconImgUrl,
                        contentDescription = "Folder Icon",
                        modifier = Modifier.size(32.dp),
                        contentScale = ContentScale.Fit,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = menuFolder.menuFolderTitle,
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_500_24,
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = String.format(stringResource(R.string.menu_count), menuCount),
                    color = NeutralWhite,
                    style = ourMenuTypography().pretendard_500_14,
                )
            }
        }
    }
}

@Composable
fun gradientBrush(): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
    )
}

@Preview(showBackground = true)
@Composable
private fun MenuFolderButtonPreview() {
    val dummyMenuFolder = MenuFolderList(
        menuFolderId = 1,
        menuFolderTitle = "인기 메뉴",
        menuFolderImgUrl = "https://ourmenu-default.s3.ap-northeast-2.amazonaws.com/default_menu_folder_img.svg",
        menuFolderIconImgUrl = "DICE",
        menuIds = listOf(1, 2, 3),
        index = 0
    )

    MenuFolderButton(
        menuFolder = dummyMenuFolder,
        false, {}, {})
}