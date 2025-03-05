package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun VerifyCodeTextField(
    input: String,
    onTextChange: (String) -> Unit,
    onNext: () -> Unit, // 다음 칸으로 이동하는 콜백
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
) {
    CustomTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .width(36.dp)
            .height(44.dp)
            .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
        text = input,
        onTextChange = { newText ->
            if (newText.length <= 1) {
                onTextChange(newText)
                if (newText.isNotEmpty()) {
                    onNext() // 다음 칸으로 이동
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        paddingValues = PaddingValues(12.dp, 12.dp),
        containerColor = Neutral100,
        textStyle = ourMenuTypography().pretendard_700_16.copy(color = Neutral900),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        cursorColor = Primary500Main // TODO: 적용 안됨 왜지? 서치 더 해보기
    )
}

@Preview(showBackground = true)
@Composable
private fun VerifyCodeTextFieldPreview() {
    val focusRequesters = List(6) { FocusRequester() }
    val codes: SnapshotStateList<String> = remember { mutableStateListOf("", "", "", "", "", "") }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until 6) {
            VerifyCodeTextField(
                input = codes[i],
                onTextChange = { newText ->
                    if (newText.length <= 1) {
                        codes[i] = newText // Compose에서 상태 변경 감지
                    }
                },
                onNext = {
                    if (i < 5) {
                        focusRequesters[i + 1].requestFocus()
                    }
                },
                modifier = Modifier.then(
                    if (i == 0 && codes[i].isEmpty()) {
                        Modifier.focusRequester(focusRequesters[i])
                    } else Modifier
                ),
                focusRequester = focusRequesters[i],
            )
            if (i < 5) {
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}