package com.kuit.ourmenu.ui.menuFolder.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun AddButton(btnName: String, modifier: Modifier, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Neutral100),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = btnName,
                color = Neutral500,
                style = ourMenuTypography().pretendard_600_14
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddButtonPreview() {
    AddButton(stringResource(R.string.add_menu), modifier = Modifier, onClick = {})
}