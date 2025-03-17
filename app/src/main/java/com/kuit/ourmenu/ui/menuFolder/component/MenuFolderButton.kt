package com.kuit.ourmenu.ui.menuFolder.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch


@Composable
fun MenuFolderButton(
    isSwiped: Boolean, // 현재 버튼이 스와이프된 상태인지 확인
    onSwipe: () -> Unit, // 새로운 버튼이 스와이프될 때 호출
    onReset: () -> Unit // 버튼이 닫히면 호출
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()
    val maxSwipe = 128f // 최대 스와이프 범위 (삭제 + 수정 버튼 너비)

    Box(
        modifier = Modifier
            .height(132.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        scope.launch {
                            if (offsetX < -80f) {
                                onSwipe() // 다른 버튼을 닫고 이 버튼만 스와이프
                                offsetX = -maxSwipe
                            } else {
                                offsetX = 0f
                                onReset() // 스와이프가 닫히면 상태 초기화
                            }
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX = (offsetX + dragAmount).coerceIn(-maxSwipe, 0f)
                }
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            MenuFolderDeleteButton()
            MenuFolderEditButton()
        }

        // 스와이프 상태일 때만 offset 적용
        Box(modifier = Modifier.offset(x = if (isSwiped) offsetX.dp else 0.dp)) {
            MenuFolderContent()
        }
    }
}

@Composable
fun MenuFolderEditButton() {
    Box(
        modifier = Modifier
            .padding(end = 64.dp)
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
            .background(color = Neutral300),
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
fun MenuFolderDeleteButton() {
    Box(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
            .background(color = Primary500Main),
        contentAlignment = Alignment.CenterEnd
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
fun MenuFolderContent() {
    val menuCount = 5 // 임의로 정한 값

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_dummy_pizza),
            contentDescription = "Folder Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
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
                    Image(
                        painter = painterResource(id = R.drawable.img_popup_dice),
                        contentDescription = "Folder Icon",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.menu_folder_name),
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
    MenuFolderButton(false, {}, {})
}