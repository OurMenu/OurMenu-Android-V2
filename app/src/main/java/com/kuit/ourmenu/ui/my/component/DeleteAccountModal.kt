package com.kuit.ourmenu.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun DeleteAccountModal(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NeutralWhite, shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 닫기 아이콘
            Icon(
                painter = painterResource(R.drawable.ic_close_24_n400),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onDismiss() }
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 제목
            Text(
                text = stringResource(R.string.want_to_delete_account),
                style = ourMenuTypography().pretendard_700_18,
                color = Neutral900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 경고 메시지
            Text(
                text = stringResource(R.string.delete_account_warning),
                style = ourMenuTypography().pretendard_500_14,
                color = Neutral500,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 버튼 Row
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Neutral400,
                        contentColor = NeutralWhite
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = ourMenuTypography().pretendard_700_18,
                        color = NeutralWhite
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500Main,
                        contentColor = NeutralWhite
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.confirm),
                        style = ourMenuTypography().pretendard_700_18,
                        color = NeutralWhite
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeleteAccountModalPreview() {
    DeleteAccountModal(
        onDismiss = {},
        onConfirm = {}
    )
}