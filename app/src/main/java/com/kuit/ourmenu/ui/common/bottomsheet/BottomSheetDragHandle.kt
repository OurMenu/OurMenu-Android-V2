package com.kuit.ourmenu.ui.common.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.theme.Neutral300


/**
 * BottomSheet 의 Drag Handle 용으로 사용되는 Composable
 *
 * BottomSheetScaffold(sheetDragHandle = BottomSheetDragHandle()) 의 형태로 사용합니다.
 *
 * @param modifier Modifier
 * @param verticalPadding Dp : Drag Handle 의 상단과 하단의 Padding 값
 * @param width Dp : Drag Handle 의 가로 길이 default 120.dp
 * @param height Dp : Drag Handle 의 세로 길이 default 4.dp
 * @param color Color : Drag Handle 의 색상 default Neutral300
 * @param shape Shape : Drag Handle 의 모양 default RoundedCornerShape(6.dp)
 *
 * */
@Composable
fun BottomSheetDragHandle(
    modifier: Modifier = Modifier,
    verticalPadding: Dp = 12.dp,
    width: Dp = 120.dp,
    height: Dp = 4.dp,
    color: Color = Neutral300,
    shape: Shape = RoundedCornerShape(6.dp),
) {
    Surface(
        modifier = modifier.padding(vertical = verticalPadding),
        color = color,
        shape = shape
    ) {
        Box(Modifier.size(width = width, height = height))
    }
}