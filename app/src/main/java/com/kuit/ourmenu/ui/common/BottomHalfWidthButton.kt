package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomHalfWidthButton(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(154.dp, 52.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomHalfWidthButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BottomHalfWidthButton({}, Color(0xFFC2C2C4), Color.White, "취소")
            BottomHalfWidthButton({}, Color(0xFFFF5420), Color.White, "적용하기")
        }
    }
}
