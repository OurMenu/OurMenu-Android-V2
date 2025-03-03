package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ViewUtil.noRippleClickable

@Composable
fun EmailDropdown(
    modifier: Modifier = Modifier,
    domain: String,
    onDomainChange : (String) -> Unit,
) {
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(-1) } // -1은 아무 항목도 선택되지 않은 상태
    val domains = listOf(
        stringResource(R.string.email_custom),
        stringResource(R.string.email_daum),
        stringResource(R.string.email_gmail),
        stringResource(R.string.email_kakao),
        stringResource(R.string.email_nate),
        stringResource(R.string.email_naver),
    )
//    val customEmail = remember { mutableStateOf("") } // 직접 입력한 이메일을 저장하는 상태
    val isEditable = remember { mutableStateOf(false) } // 타이핑 가능 여부
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isEditable.value) {
        if (isEditable.value) {
            focusRequester.requestFocus()
        }
    }

    Box(
        contentAlignment = Alignment.CenterEnd,
    ) {
        CustomTextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .border(
                    width = 1.dp,
                    color = if (isFocused) Neutral500 else Neutral300,
                    shape = RoundedCornerShape(8.dp)
                )
                .noRippleClickable {
                    isDropDownExpanded.value = true
                },
            text = domain,
            onTextChange = {
                onDomainChange(it)
            },
            shape = RoundedCornerShape(8.dp),
            paddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            containerColor = Neutral100,
            placeHolder = {
                Text(
                    text = stringResource(R.string.placeholder_email_domain),
                    style = ourMenuTypography().pretendard_700_12,
                    color = Neutral500,
                )
            },
            enabled = isEditable.value,
            textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
            interactionSource = interactionSource,
            cursorColor = Primary500Main,
        )

        if (!isEditable.value or domain.isEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dropdown_btn),
                contentDescription = null,
                tint = Neutral500,
                modifier =
                Modifier
                    .padding(end = 16.dp)
                    .noRippleClickable {
                        isDropDownExpanded.value = true
                    },
            )
        }


        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = { isDropDownExpanded.value = false },
            modifier = Modifier.width(122.dp),
            offset = DpOffset(0.dp, 8.dp),
        ) {
            domains.forEachIndexed { index, domain ->
                DropdownMenuItem(
                    text = { Text(text = domain) },
                    onClick = {
                        isDropDownExpanded.value = false
                        itemPosition.intValue = index

                        if (index == 0) {
                            // "직접 입력하기" 선택
                            isEditable.value = true
                            onDomainChange("") // 초기화
                            focusRequester.requestFocus()
                        } else {
                            // 다른 이메일 선택
                            isEditable.value = false
                            onDomainChange(domain)
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
    val domain = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmailDropdown(
            domain = domain.value,
            onDomainChange = {
                domain.value = it
            }
        )
    }
}
