package com.kuit.ourmenu.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.LoginTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MyCurrentPasswordModal(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .background(NeutralWhite, shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
                .width(288.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Text(
                text = stringResource(R.string.enter_current_password),
                style = ourMenuTypography().pretendard_700_18,
                color = Neutral900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            LoginTextField(
                placeholder = stringResource(R.string.current_password),
                input = password,
                onTextChange = { password = it },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isPasswordVisible,
                    onCheckedChange = { isPasswordVisible = it },
                    modifier =
                        Modifier
                            .border(1.dp, Neutral300, RoundedCornerShape(4.dp))
                            .size(24.dp),
                    colors =
                        CheckboxDefaults.colors(
                            checkmarkColor = NeutralWhite,
                            checkedColor = Primary500Main,
                            uncheckedColor = Neutral100,
                        ),
                )

                Text(
                    text = stringResource(R.string.see_password),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
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

@Preview(showBackground = true)
@Composable
private fun MyCurrentPasswordModalPreview() {
    MyCurrentPasswordModal(
        onDismiss = {},
        onConfirm = {}
    )
}