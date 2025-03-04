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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun VerifyCodeTextField(
    input: String,
    onTextChange: (String) -> Unit,
    onNext: () -> Unit, // 다음 칸으로 이동하는 콜백
    onPrevious: () -> Unit = {}, // 이전 칸으로 이동하는 콜백
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
) {

    var isFocused by remember { mutableStateOf(false) }

    CustomTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .width(36.dp)
            .height(44.dp)
            .border(
                width = 1.dp,
                color =
                if (isFocused) Neutral500
                else Neutral300,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Backspace) {
                    onPrevious() // 이전 칸으로 이동
                    true // 이벤트 처리 완료
                } else {
                    false // 다른 키는 처리하지 않음
                }
            },
        text = input,
        onTextChange = { newText ->
            if (newText.isDigitsOnly() && newText.length <= 1) {
                onTextChange(newText)
                if (newText.isNotEmpty()) {
                    onNext() // 다음 칸으로 이동
                } else {
                    onPrevious() // 이전 칸으로 이동
                }
            } else {
                onTextChange(newText.take(1))
                onNext() // 다음 칸으로 이동
            }
        },
        shape = RoundedCornerShape(8.dp),
        paddingValues = PaddingValues(12.dp, 12.dp),
        containerColor = Neutral100,
        textStyle = ourMenuTypography().pretendard_700_16.copy(
            color = Neutral900,
            textAlign = TextAlign.Center
        ),
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
                input = "1",
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