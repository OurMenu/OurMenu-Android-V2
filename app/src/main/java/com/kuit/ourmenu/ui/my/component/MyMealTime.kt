package com.kuit.ourmenu.ui.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral200
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.OurMenuTypography
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MyMealTime(
    navigateToEdit: () -> Unit = {},
    mealTimes: List<String> = listOf("8:00", "12:00", "19:00"),
) {
    Column(
        modifier = Modifier
            .background(Neutral200)
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.now_meal_time),
            style = OurMenuTypography().pretendard_600_16,
            color = Neutral900
        )

        MyMealTimeBox(mealTimes = mealTimes)

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { navigateToEdit() },
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary500Main,
                contentColor = NeutralWhite
            ),
        ) {
            Text(
                text = stringResource(R.string.change_meal_time),
                style = ourMenuTypography().pretendard_700_16,
                color = NeutralWhite
            )
        }
    }
}

@Composable
fun MyMealTimeBox(mealTimes: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(NeutralWhite)
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        mealTimes.forEachIndexed { index, time ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_clock),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = time,
                    style = OurMenuTypography().pretendard_600_18,
                    color = Neutral900
                )
            }

            if (index != mealTimes.lastIndex) {
                HorizontalDivider(color = Neutral300, thickness = 1.dp)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MyMealTimePreview() {
    MyMealTime()
}