package com.kuit.ourmenu.ui.menuFolder.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SortDropdown(
    options: List<String>,
    selectedOption: String,
    onSelectedOptionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier.clickable {
                expanded = true
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedOption,
                style = ourMenuTypography().pretendard_500_14,
                color = Neutral700
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = if (expanded) painterResource(id = R.drawable.ic_dropdown_btn_up) else painterResource(
                    id = R.drawable.ic_dropdown_btn
                ),
                modifier = Modifier.size(16.dp),
                contentDescription = "Expand Down"
            )
        }

        DropdownMenu(
            modifier = Modifier
                .width(72.dp)
                .background(color = NeutralWhite, shape = RoundedCornerShape(8.dp)),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption

                DropdownMenuItem(
                    modifier = Modifier
                        .width(72.dp),
                    text = {
                        Text(
                            text = option,
                            style = ourMenuTypography().pretendard_500_14.copy(
                                color = if (isSelected) Primary500Main else Neutral500
                            )
                        )
                    },
                    onClick = {
                        onSelectedOptionChange(option)
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SortDropdownPreview() {
    val options = listOf("이름순", "등록순", "가격순")
    var selectedOption by rememberSaveable { mutableStateOf("이름순") }

    Column(modifier = Modifier.fillMaxSize()) {
        SortDropdown(
            options = options,
            selectedOption = selectedOption
        ) {
            selectedOption = it
        }
    }
}