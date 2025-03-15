package com.kuit.ourmenu.ui.onboarding.screen.signup

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.component.MealTimeGrid
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.state.SignupState
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SignupMealTimeScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {

    val mealTimeList by viewModel.mealTimes.collectAsStateWithLifecycle()
    val selectedTimes by viewModel.selectedTimes.collectAsStateWithLifecycle()
    val enable = selectedTimes.isNotEmpty()
    val signupState by viewModel.signupState.collectAsStateWithLifecycle()

    LaunchedEffect(signupState) {
        when (signupState) {
            is SignupState.Success ->
                navController.navigate(route = Routes.Login) {
                    popUpTo(Routes.Landing) {
                        inclusive = true
                    }
                }

            is SignupState.Error ->
                Log.e("SignupVerifyScreen", viewModel.error.value.toString())

            else -> {}
        }
    }

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
                    selectedTimes = selectedTimes,
                    addTime = { index, mealTime ->
                        viewModel.addSelectedTime(index, mealTime)
                    },
                    removeTime = { index, mealTime ->
                        viewModel.removeSelectedTime(index, mealTime)
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
                Log.d("okhttp", "SignupMealTimeScreen")
                viewModel.signup()
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

    SignupMealTimeScreen(navController)

}