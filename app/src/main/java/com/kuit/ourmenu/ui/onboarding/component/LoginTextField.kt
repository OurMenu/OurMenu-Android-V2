package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary100
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun LoginTextField(
    error: Boolean = false,
    modifier: Modifier = Modifier,
    placeholder: String,
    input: String,
    onTextChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var isFocused by remember { mutableStateOf(false) }

    CustomTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .border(
                width = 1.dp,
                color =
                if (isFocused) Neutral500
                else if (error) Primary500Main
                else Neutral300,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        text = input,
        onTextChange = onTextChange,
        shape = RoundedCornerShape(8.dp),
        paddingValues = PaddingValues(28.dp, 12.dp),
        containerColor = if (error) Primary100 else Neutral100,
        placeHolder = {
            Text(
                text = placeholder,
                style = ourMenuTypography().pretendard_700_12,
                color = Neutral500,
                lineHeight = 18.sp
            )
        },
        textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
        visualTransformation = visualTransformation,
        cursorColor = Primary500Main,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (error) Primary100 else Neutral100,
            unfocusedContainerColor = Neutral100,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Neutral100,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}

@Preview
@Composable
private fun LoginTextFieldPreview() {
    LoginTextField(
        placeholder = "Email",
        input = "",
        onTextChange = {}
    )
}
