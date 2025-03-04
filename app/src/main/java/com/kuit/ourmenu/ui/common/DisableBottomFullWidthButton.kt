package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun DisableBottomFullWidthButton(
    enable: Boolean,
    modifier: Modifier = Modifier,
    enableContainerColor: Color = Primary500Main,
    disabledContainerColor: Color = Neutral400,
    contentColor: Color = NeutralWhite,
    text: String,
    onClick: () -> Unit
) {
    Button(
        enabled = enable,
        onClick = onClick,
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x29000000),
                ambientColor = Color(0x29000000)
            )
            .fillMaxWidth()
            .size(320.dp, 48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = enableContainerColor,
            disabledContainerColor = disabledContainerColor,
            contentColor = contentColor
        ),
    ) {
        Text(
            text = text,
            style = ourMenuTypography().pretendard_700_16,
            color = contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DisableBottomFullWidthButtonPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        DisableBottomFullWidthButton(
            enable = true,
            text = stringResource(R.string.add_store_and_menu_by_myself)
        ) {
            //onClick 작성
        }
        Spacer(modifier = Modifier.height(16.dp))
        DisableBottomFullWidthButton(
            enable = false,
            text = stringResource(R.string.add_menu)
        ) {
            //onClick 작성
        }
    }
}