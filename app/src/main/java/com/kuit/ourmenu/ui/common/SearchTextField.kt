package com.kuit.ourmenu.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    @StringRes placeHolder: Int = R.string.placeholder,
    borderColor: Color = Neutral500,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Card(
        modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.8.dp, borderColor, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .height(44.dp),
            text = text,
            onTextChange = onTextChange,
            shape = RoundedCornerShape(8.dp),
            placeHolder = {
                Text(
                    text = stringResource(placeHolder),
                    style = ourMenuTypography().pretendard_500_14.copy(
                        lineHeight = 20.sp,
                        color = Neutral500
                    )
                )
            },
            textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_searchbar_search),
                    contentDescription = "Search Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.clickable { onSearch() }
                )
            },
            paddingValues = PaddingValues(start = 28.dp, top = 0.dp, bottom = 0.dp),
            cursorColor = Primary500Main,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() })
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Center
    ) {
        SearchTextField(
            text = text,
            onTextChange = { text = it },
            onSearch = { }
        )
    }
}