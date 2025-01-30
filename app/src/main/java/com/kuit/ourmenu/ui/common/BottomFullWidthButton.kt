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
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun BottomFullWidthButton(
    modifier : Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .size(320.dp, 48.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Text(text = text,
            style = ourMenuTypography().pretendard_700_16,
            color = contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomFullWidthButtonPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        BottomFullWidthButton(containerColor = Neutral100, contentColor = Neutral500, text = stringResource(R.string.add_restaurant_and_menu_by_myself)){
            //onClick 작성
        }
        Spacer(modifier = Modifier.height(16.dp))
        BottomFullWidthButton(containerColor = Primary500Main, contentColor = NeutralWhite, text = stringResource(R.string.add_menu)){
            //onClick 작성
        }
    }
}