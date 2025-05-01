package com.kuit.ourmenu.ui.my.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.onboarding.component.MealTimeGrid
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.model.MealTimeState
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun EditMyMealTimeScreen() {
    // TODO: viewmodel로 수정하기
    val initialTimes = listOf(
        "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
        "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"
    )

    val mealTimeList = remember {
        mutableStateListOf<MealTimeState>().apply {
            addAll(initialTimes.map { MealTimeState(mealTime = it) })
        }
    }

    val selectedTimes = remember {
        mutableStateListOf<String>().apply {
            addAll(listOf()) // 초기 선택값
        }
    }

    val enable = selectedTimes.isNotEmpty()

    Scaffold(
        topBar = {
            OnboardingTopAppBar(
                onBackClick = { /* TODO: Handle back click */ },
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 92.dp)
            ) {
                Text(
                    text = stringResource(R.string.input_meal_time),
                    style = ourMenuTypography().pretendard_600_24,
                    color = Neutral900,
                    modifier = Modifier
                )

                Text(
                    text = stringResource(R.string.input_meal_time_description),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    modifier = Modifier.padding(top = 4.dp),
                )

                MealTimeGrid(
                    modifier = Modifier.padding(top = 29.dp),
                    mealTimes = mealTimeList,
                    selectedTimes = selectedTimes,
                    addTime = { index, mealTime ->
                        if (mealTime !in selectedTimes && selectedTimes.size < 4) {
                            selectedTimes.add(mealTime)
                            mealTimeList[index] = mealTimeList[index].copy(selected = true)
                        }
                    },
                    removeTime = { index, mealTime ->
                        selectedTimes.remove(mealTime)
                        mealTimeList[index] = mealTimeList[index].copy(selected = false)
                    }
                )
            }

            DisableBottomFullWidthButton(
                enable = enable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.confirm)
            ) {

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditMyMealTimeScreenPreview() {
    EditMyMealTimeScreen()
}