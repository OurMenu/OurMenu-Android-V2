package com.kuit.ourmenu.ui.common.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

/**
 * Dialog 에 사용되는 Big Button
 *
 * Width : fillMaxWidth
 *
 * Height : 48dp
 *
 * Padding 값 없음
 *
 * Round Corner : 12dp
 *
 * Text Style : Pretendard 700 20sp
 *
 * Text Color : NeutralWhite
 *
 * @param buttonText: 버튼 텍스트
 * @param buttonIcon: 버튼 아이콘
 * @param hasIcon: 아이콘 여부
 * @param containerColor: 버튼 색상
 * */
@Composable
fun DialogBigButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    hasIcon: Boolean = true,
    buttonIcon: String = "",
    containerColor: Color,
    onClick: () -> Unit = { /* TODO */ }
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = NeutralWhite
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hasIcon) {
                Icon(
                    painter = painterResource(R.drawable.img_home_popup_thumbsup),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(28.dp)
                )
            }
            Text(
                text = buttonText,
                style = ourMenuTypography().pretendard_700_20.copy(
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun IBBPreview() {
    Column {
        DialogBigButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            buttonText = "좋아!",
            hasIcon = true,
            containerColor = Primary500Main
        )
        Spacer(modifier = Modifier.height(16.dp))
        DialogBigButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            buttonText = "확인",
            hasIcon = false,
            containerColor = Neutral400
        )
    }
}