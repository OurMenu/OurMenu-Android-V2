package com.kuit.ourmenu.ui.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.signup.uistate.MealTime
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Primary100
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ViewUtil.noRippleClickable

@Composable
fun MealTimeGrid(
    modifier: Modifier = Modifier,
    mealTimes: List<MealTime>,
    selectedTimes: List<String>,
    addTime: (Int, String) -> Unit,
    removeTime: (Int, String) -> Unit
) {
    val state = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        items(18) { index ->
            MealTimeItem(
                modifier = Modifier
                    .width(66.dp)
                    .height(42.dp),
                mealTime = mealTimes[index].mealTime,
                selected = mealTimes[index].selected,
                updateSelected = { mealTime, selected ->
                    if (selected) {
                        if (selectedTimes.size < 4) addTime(index, mealTime)
                    } else
                        removeTime(index, mealTime)
                }
            )
        }
    }
}

@Composable
fun MealTimeItem(
    modifier: Modifier = Modifier,
    mealTime: String = "10:00",
    selected: Boolean = false,
    updateSelected: (String, Boolean) -> Unit = { _, _ -> },
) {

    val containerColor = if (selected) Primary100 else Neutral100
    val borderColor = if (selected) Primary500Main else Neutral300
    val textColor = if (selected) Primary500Main else Neutral500

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .background(color = containerColor, shape = RoundedCornerShape(size = 8.dp))
            .noRippleClickable {
                updateSelected(mealTime, !selected)
            }
    ) {
        Text(
            text = mealTime,
            style = ourMenuTypography().pretendard_500_16,
            color = textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}