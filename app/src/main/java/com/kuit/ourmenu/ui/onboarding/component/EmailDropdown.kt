package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun EmailDropdown(modifier: Modifier = Modifier) {
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(-1) } // -1은 아무 항목도 선택되지 않은 상태
    val emails =
        listOf(
            stringResource(R.string.email_custom),
            stringResource(R.string.email_daum),
            stringResource(R.string.email_gmail),
            stringResource(R.string.email_kakao),
            stringResource(R.string.email_nate),
            stringResource(R.string.email_naver),
        )
    val customEmail = remember { mutableStateOf("") } // 직접 입력한 이메일을 저장하는 상태
    val isEditable = remember { mutableStateOf(false) } // 타이핑 가능 여부

    Box(
        contentAlignment = Alignment.CenterEnd,
    ) {
        CustomTextField(
            modifier =
                Modifier
                    .width(122.dp)
                    .height(44.dp)
                    .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
            text =
                if (itemPosition.value == -1) {
                    "" // 아무 항목도 선택되지 않았을 때 빈 텍스트
                } else if (isEditable.value || customEmail.value.isNotEmpty()) {
                    customEmail.value
                } else {
                    emails[itemPosition.value]
                },
            onTextChange = {
                if (isEditable.value) {
                    customEmail.value = it
                }
            },
            shape = RoundedCornerShape(8.dp),
            paddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            containerColor = Neutral100,
            placeHolder = {
                Text(
                    text = stringResource(R.string.placeholder_text),
                    style = ourMenuTypography().pretendard_700_14,
                    color = Neutral500,
                )
            },
            textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_dropdown_btn),
            contentDescription = null,
            tint = Neutral500,
            modifier =
                Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        isDropDownExpanded.value = true
                    },
        )

        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = { isDropDownExpanded.value = false },
            modifier = Modifier.width(122.dp),
        ) {
            emails.forEachIndexed { index, email ->
                DropdownMenuItem(
                    text = { Text(text = email) },
                    onClick = {
                        isDropDownExpanded.value = false
                        itemPosition.value = index

                        if (index == 0) {
                            // "직접 입력하기" 선택
                            isEditable.value = true
                            customEmail.value = "" // 초기화
                        } else {
                            // 다른 이메일 선택
                            isEditable.value = false
                            customEmail.value = email
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmailDropdownPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmailDropdown()
    }
}
