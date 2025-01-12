package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SearchBar(
    onSearch: () -> Unit,
) {
    Card(
        modifier = Modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.8.dp, Neutral500, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .height(44.dp),
            shape = RoundedCornerShape(8.dp),
            placeHolder = {
                Text(
                    text = stringResource(R.string.placeholder),
                    style = ourMenuTypography().pretendard_600_18.copy(color = Neutral500)
                )
            },
            textStyle = ourMenuTypography().pretendard_700_20.copy(color = Neutral700),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_searchbar_search),
                    contentDescription = "Search Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.clickable { onSearch() }
                )
            },
            paddingValues = PaddingValues(start = 28.dp, top = 0.dp, bottom = 0.dp),
            cursorColor = Primary500Main
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Center
    ) {
        SearchBar(
            onSearch = { }
        )
    }
}