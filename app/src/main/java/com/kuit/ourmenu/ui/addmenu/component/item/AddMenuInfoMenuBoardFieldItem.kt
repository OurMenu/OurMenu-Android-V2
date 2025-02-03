package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun AddMenuInfoMenuBoardFieldItem(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onSelectedOptionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = modifier
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

        Box(modifier = modifier.fillMaxWidth()) {
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .border(1.dp, Neutral300, RoundedCornerShape(8.dp))
                    .padding(end = 6.dp)
                    .clickable {
                        expanded = true
                    },
                text = selectedOption,
                onTextChange = {},
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
                enabled = false,
                textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
                trailingIcon = {
                    Button(
                        modifier = modifier.size(44.dp, 32.dp),
                        onClick = {
                            if(expanded) expanded = false
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(if (selectedOption.isBlank()) Neutral400 else Primary500Main),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "확인",
                            style = ourMenuTypography().pretendard_700_12.copy(
                                color = NeutralWhite
                            )
                        )
                    }
                }
            )
            DropdownMenu(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = NeutralWhite, shape = RoundedCornerShape(8.dp)),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    val isSelected = option == selectedOption
                    DropdownMenuItem(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                color = if (isSelected) Neutral300 else NeutralWhite,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        text = {
                            Row(modifier = modifier.fillMaxWidth()) {
                                Icon(
                                    painter = painterResource(if (isSelected) R.drawable.ic_dropdown_checked else R.drawable.ic_dropdown_unchecked),
                                    contentDescription = "check icon",
                                    tint = Color.Unspecified
                                )

                                Text(
                                    text = option,
                                    style = ourMenuTypography().pretendard_700_14.copy(
                                        color = if (isSelected) Neutral700 else Neutral500
                                    )
                                )
                            }
                        },
                        onClick = {
                            onSelectedOptionChange(option)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuInfoMenuBoardFieldItemPreview() {
    var options = listOf("옵션1", "옵션2", "옵션3")
    var selectedOption by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        AddMenuInfoMenuBoardFieldItem(
            options = options,
            selectedOption = selectedOption
        ){
            selectedOption = it
        }
    }
}