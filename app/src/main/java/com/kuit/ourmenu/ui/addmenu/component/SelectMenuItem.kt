package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R

@Composable
fun SelectMenuItem(isSelected: Boolean = false) {
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.LightGray,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "메뉴 이름", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "14,000원", fontSize = 14.sp, color = Color.Gray)
        }
        if (isSelected) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5420)
                ),
                modifier = Modifier.size(44.dp, 28.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_btn_check_white),
                    contentDescription = "selected button"
                )
            }
        } else {
            Button(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFFF5420)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFFF5420)
                ),
                modifier = Modifier.size(44.dp, 28.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_btn_plus_orange),
                    contentDescription = "unselected button"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectedMenuItemPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        SelectMenuItem(false)
        SelectMenuItem(true)
    }
}