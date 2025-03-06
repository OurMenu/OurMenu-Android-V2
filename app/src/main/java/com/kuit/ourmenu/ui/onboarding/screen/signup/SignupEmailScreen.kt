package com.kuit.ourmenu.ui.onboarding.screen.signup

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
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
import com.kuit.ourmenu.ui.onboarding.component.EmailSpinner
import com.kuit.ourmenu.ui.onboarding.component.LoginTextField
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.state.LoginState
import com.kuit.ourmenu.ui.onboarding.state.SignupState
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch

@Composable
fun SignupEmailScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {

    val email by viewModel.email.collectAsStateWithLifecycle()
    val domain by viewModel.domain.collectAsStateWithLifecycle()
    val enable = email.isNotEmpty() && domain.isNotEmpty()
    val signupState by viewModel.signupState.collectAsStateWithLifecycle()

    LaunchedEffect(signupState) {
        when (signupState) {
            is SignupState.Success ->
                navController.navigate(route = Routes.SignupVerify)

            is SignupState.Error ->
                Log.e("SignupEmailScreen", viewModel.error.value.toString())

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
                    text = stringResource(R.string.input_email),
                    style = ourMenuTypography().pretendard_600_24,
                    color = Neutral900,
                    modifier = Modifier
                )

                Text(
                    text = stringResource(R.string.input_email_description),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    modifier = Modifier.padding(top = 4.dp),
                )

                EmailInputField(
                    modifier = Modifier.padding(top = 12.dp),
                    email = email,
                    onEmailChange = { viewModel.updateEmail(it) },
                    domain = domain,
                    onDomainChange = { viewModel.updateDomain(it) }
                )
            }

            DisableBottomFullWidthButton(
                enable = enable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.send_auth_mail)
            ) { viewModel.sendEmail() }

        }
    }
}


@Composable
fun EmailInputField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    domain: String,
    onDomainChange: (String) -> Unit = {},
) {


    Row(
        modifier = modifier,
    ) {
        LoginTextField(
            modifier = Modifier.weight(1f),
            placeholder = stringResource(R.string.placeholder_default),
            input = email,
            onTextChange = onEmailChange,
        )

        Text(
            text = "@",
            style = ourMenuTypography().pretendard_500_14,
            color = Neutral500,
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .padding(top = 11.5.dp)
                .align(Alignment.Top),
        )

        EmailSpinner(
            domain = domain,
            onDomainChange = onDomainChange
        )
    }
}

@Preview
@Composable
private fun SignupEmailScreenPreview() {
    val navController = rememberNavController()

    SignupEmailScreen(navController)
}