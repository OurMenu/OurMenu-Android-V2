package com.kuit.ourmenu.ui.onboarding.screen.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.component.MealTimeGrid
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.model.MealTimeState
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SignupNicknameScreen(
    navController: NavController
) {

    val mealTimeList = remember {
        mutableStateListOf(
            *List(18) {
                MealTimeState(mealTime = "${it + 6}:00")
            }.toTypedArray()
        )
    }
    val selectedTimes = remember { mutableStateListOf<String>() }
    val enable = selectedTimes.isNotEmpty()

    Scaffold(
        topBar = {
            OnboardingTopAppBar(
                onBackClick = {
                    navController.navigateUp()
                }
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
                    selectedTimes = selectedTimes
                )
            }

            DisableBottomFullWidthButton(
                enable = enable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.confirm)
            ) {
                navController.navigate(route = Routes.Login)
            }

        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
private fun SignupNicknameScreenPreview() {
    val navController = rememberNavController()

    SignupNicknameScreen(navController)

}