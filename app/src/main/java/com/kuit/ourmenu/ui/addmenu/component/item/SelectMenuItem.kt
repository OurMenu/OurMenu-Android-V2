package com.kuit.ourmenu.ui.addmenu.component.item

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.CrawlingMenuDetail
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SelectMenuItem(
    menu: CrawlingMenuDetail,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    HorizontalDivider(
        thickness = 1.dp,
        color = Neutral300,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = menu.menuTitle,
                style = ourMenuTypography().pretendard_700_16
            )
            Text(
                text = menu.menuPrice,
                style = ourMenuTypography().pretendard_500_14,
                color = Neutral700,
            )
        }
        if (isSelected) {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary500Main
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
                onClick = onClick,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Primary500Main),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeutralWhite,
                    contentColor = Primary500Main
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
        SelectMenuItem(
            menu = CrawlingMenuDetail(
                menuTitle = "떡볶이",
                menuPrice = "14,000원"
            ),
            isSelected = false,
            onClick = {}
        )
        SelectMenuItem(
            menu = CrawlingMenuDetail(
                menuTitle = "치킨",
                menuPrice = "18,000원"
            ),
            isSelected = true,
            onClick = {}
        )
    }
}