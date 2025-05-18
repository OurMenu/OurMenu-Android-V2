package com.kuit.ourmenu.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.OurMenuTypography
import com.kuit.ourmenu.ui.theme.Primary500Main

@Composable
fun MyBottomModal(
    onDismissRequest: () -> Unit,
    onChangePassword: () -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f)) // 흐린 배경
            .clickable(onClick = onDismissRequest), // 바깥 클릭 시 dismiss
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // 모달 본문
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        NeutralWhite,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                SheetItem(
                    text = stringResource(R.string.change_password),
                    textColor = Neutral700,
                    onClick = {
                        onDismissRequest()
                        onChangePassword()
                    }
                )

                HorizontalDivider(color = Neutral300)

                SheetItem(
                    text = stringResource(R.string.logout),
                    onClick = {
                        onDismissRequest()
                        onLogout()
                    }
                )

                HorizontalDivider(color = Neutral300)

                SheetItem(
                    text = stringResource(R.string.delete_account),
                    onClick = {
                        onDismissRequest()
                        onDeleteAccount()
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 취소 버튼
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        NeutralWhite,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(onClick = onDismissRequest)
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = OurMenuTypography().pretendard_500_16,
                    color = Neutral500
                )
            }
        }
    }
}

@Composable
private fun SheetItem(
    text: String,
    onClick: () -> Unit,
    textColor: Color = Primary500Main
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = OurMenuTypography().pretendard_500_16,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyBottomModalPreview() {
    MyBottomModal(
        onDismissRequest = {},
        onChangePassword = {},
        onLogout = {},
        onDeleteAccount = {}
    )
}