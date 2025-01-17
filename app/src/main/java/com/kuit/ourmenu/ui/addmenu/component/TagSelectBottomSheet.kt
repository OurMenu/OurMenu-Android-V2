package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun TagSelectBottomSheet(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(R.string.food_tag),
                style = ourMenuTypography().pretendard_700_16
            )
            Text(
                text = stringResource(R.string.multiple_choice_available),
                style = ourMenuTypography().pretendard_600_14,
                color = Neutral500
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        //종류
        Text(
            text = "종류",
            style = ourMenuTypography().pretendard_500_14,
        )

        //나라 별 음식
        Text(
            text = "나라 별 음식",
            style = ourMenuTypography().pretendard_500_14,
        )

        //맛
        Text(
            text = "맛",
            style = ourMenuTypography().pretendard_500_14,
        )

        //상황
        Text(
            text = "상황",
            style = ourMenuTypography().pretendard_500_14,
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun TagSelectBottomSheetPreview() {
    TagSelectBottomSheet()
}