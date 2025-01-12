package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun AddMenuInfoMenuBoardFieldItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(
                text = stringResource(R.string.menuboard),
                style = ourMenuTypography().pretendard_600_14
            )
            Text(
                text = stringResource(R.string.asterisk),
                style = ourMenuTypography().pretendard_600_14,
                color = Primary500Main
            )
        }
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            paddingValues = PaddingValues(start = 28.dp, top = 12.dp, bottom = 12.dp),
            containerColor = Neutral100,
            placeHolder = {
                Text(
                    text = stringResource(R.string.save_menuboard),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500
                )
            },
            textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_searchbar_search),
                    contentDescription = "search icon",
                    tint = Color.Unspecified
                )
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuInfoMenuBoardFieldItemPreview() {
    AddMenuInfoMenuBoardFieldItem()
}