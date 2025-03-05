package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun EmailSpinner() {
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableStateOf(0) }
    val emails = listOf("직접 입력하기", "Isabella", "Benjamin", "Sophia", "Christopher")
    val customEmail = remember { mutableStateOf("") } // 직접 입력한 이메일을 저장하는 상태

    Column(
//        modifier = Modifier.fillMaxSize(),
        modifier =
            Modifier
                .padding(18.dp, 12.dp)
                .height(44.dp)
                .width(122.dp)
                .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.clickable {
                    isDropDownExpanded.value = true
                },
        ) {
            Text(text = emails[itemPosition.value],
                style = ourMenuTypography().pretendard_500_12,
                color = Neutral500
                )
            Image(
                painter = painterResource(id = R.drawable.ic_dropdown_btn),
                contentDescription = "DropDown Icon",
            )
        }
        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = {
                isDropDownExpanded.value = false
            },
        ) {
            emails.forEachIndexed { index, email ->
                DropdownMenuItem(
                    text = {
                        Text(text = email)
                    },
                    onClick = {
                        isDropDownExpanded.value = false
                        itemPosition.value = index
                        if (index != 0) customEmail.value = "" // 직접 입력 초기화
                    },
                )
            }

            // "직접 입력하기"를 선택한 경우 TextField 표시
            if (itemPosition.value == 0) {
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = customEmail.value,
                    onValueChange = { customEmail.value = it },
                    label = { Text("이메일 입력") },
                    modifier = Modifier.fillMaxWidth(0.8f),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailSpinnerPreview() {
    EmailSpinner()
}
