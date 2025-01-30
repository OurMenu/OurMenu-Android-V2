package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
fun AddMenuInfoTextFieldItem(
    fieldName: String,
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    isPriceInfo: Boolean = false,
) {
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
                text = fieldName,
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
            text = text,
            onTextChange = onTextChange,
            shape = RoundedCornerShape(8.dp),
            paddingValues = PaddingValues(start = 28.dp, top = 12.dp, bottom = 12.dp),
            containerColor = Neutral100,
            placeHolder = {
                Text(
                    text = placeholder,
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500
                )
            },
            textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
            keyboardOptions = if(isPriceInfo) {
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    )
            }else{
                KeyboardOptions.Default
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuInfoTextFieldItemPreview() {
    var priceText by rememberSaveable { mutableStateOf("") }
    var restaurantNameText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddMenuInfoTextFieldItem(
            fieldName = stringResource(R.string.menu_price),
            text = priceText,
            onTextChange = { priceText = it },
            placeholder = stringResource(R.string.type_menu_price),
            isPriceInfo = true
        )
        AddMenuInfoTextFieldItem(
            fieldName = stringResource(R.string.restaurant_name),
            text = restaurantNameText,
            onTextChange = { restaurantNameText = it },
            placeholder = stringResource(R.string.type_restaurant_name)
        )
    }

}