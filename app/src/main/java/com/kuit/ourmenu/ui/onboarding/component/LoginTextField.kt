package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun LoginTextField(
    placeholder: String,
    input: String,
    onTextChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    CustomTextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
        text = input,
        onTextChange = onTextChange,
        shape = RoundedCornerShape(8.dp),
        paddingValues = PaddingValues(28.dp, 12.dp),
        containerColor = Neutral100,
        placeHolder = {
            Text(
                text = placeholder,
                style = ourMenuTypography().pretendard_700_12,
                color = Neutral500,
            )
        },
        textStyle = ourMenuTypography().pretendard_700_14.copy(color = Neutral700),
        visualTransformation = visualTransformation,
    )
}
