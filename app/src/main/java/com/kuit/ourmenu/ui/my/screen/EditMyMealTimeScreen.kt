package com.kuit.ourmenu.ui.my.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.common.topappbar.OnboardingTopAppBar
import com.kuit.ourmenu.ui.my.viewmodel.EditMyMealTimeUiState
import com.kuit.ourmenu.ui.my.viewmodel.EditMyMealTimeViewModel
import com.kuit.ourmenu.ui.common.MealTimeGrid
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun EditMyMealTimeRoute(
    padding: PaddingValues,
    navigateToBack: () -> Unit = {},
    viewModel: EditMyMealTimeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EditMyMealTimeScreen(
        padding = padding,
        uiState = uiState,
        updateSelectedTime = viewModel::updateSelectedTime,
        navigateToBack = navigateToBack,
    )

}

@Composable
fun EditMyMealTimeScreen(
    padding: PaddingValues,
    uiState: EditMyMealTimeUiState,
    updateSelectedTime: (Int) -> Unit = {},
    navigateToBack: () -> Unit = {},
) {
    val mealTimes = uiState.mealTimes
    val selectedTimes = uiState.selectedTimes

    val enable = selectedTimes.isNotEmpty()

    Scaffold(
        topBar = {
            OnboardingTopAppBar(
                onBackClick = navigateToBack,
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .padding(padding)
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
                    mealTimes = mealTimes,
                    updateSelectedTime = updateSelectedTime
                )
            }

            DisableBottomFullWidthButton(
                enable = enable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.confirm),
                onClick = navigateToBack
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditMyMealTimeScreenPreview() {
    EditMyMealTimeScreen(
        padding = PaddingValues(),
        uiState = EditMyMealTimeUiState(),
    )
}