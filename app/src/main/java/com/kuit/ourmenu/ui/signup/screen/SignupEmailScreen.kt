package com.kuit.ourmenu.ui.signup.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.common.LoginTextField
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.common.topappbar.OnboardingTopAppBar
import com.kuit.ourmenu.ui.signup.component.EmailSpinner
import com.kuit.ourmenu.ui.signup.model.SignupState
import com.kuit.ourmenu.ui.signup.uistate.SignupUiState
import com.kuit.ourmenu.ui.signup.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch

@Composable
fun SignupEmailRoute(
    navigateToVerify: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val enable by remember {
        derivedStateOf { uiState.email.isNotEmpty() && uiState.domain.isNotEmpty() }
    }
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(uiState.emailState) {
        when (uiState.emailState) {
            is SignupState.Success -> navigateToVerify()
            is SignupState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = viewModel.error.value ?: "",
                        duration = SnackbarDuration.Short
                    )
                }
                /*
                    TODO : Error Response 에 따라서 관리를 해야함.
                    scope.launch {
                        shakeAnimation(
                            offset = shakeOffset,
                            coroutineScope = scope
                        )
                    }
                */
            }

            else -> {}
        }
    }

    SignupEmailScreen(
        navigateBack = navigateBack,
        uiState = uiState,
        enable = enable,
        shakeOffset = shakeOffset,
        sendEmail = viewModel::sendEmail,
        updateEmail = viewModel::updateEmail,
        updateDomain = viewModel::updateDomain
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        OurSnackbarHost(
            hostState = snackbarHostState
        )
    }

}

@Composable
fun SignupEmailScreen(
    navigateBack: () -> Unit,
    uiState: SignupUiState,
    shakeOffset: Animatable<Float, AnimationVector1D>,
    enable: Boolean = true,
    sendEmail: () -> Unit = {},
    updateEmail: (String) -> Unit,
    updateDomain: (String) -> Unit
) {

    Scaffold(
        topBar = {
            OnboardingTopAppBar(
                onBackClick = navigateBack
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
            DisableBottomFullWidthButton(
                enable = enable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.send_auth_mail)
            ) { sendEmail() }

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
                    modifier = Modifier
                        .padding(top = 12.dp)
//                        .offset { IntOffset(shakeOffset.value.roundToInt(), 0) }
                    ,
                    email = uiState.email,
                    onEmailChange = { updateEmail(it) },
                    domain = uiState.domain,
                    onDomainChange = { updateDomain(it) }
                )
            }
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
    SignupEmailScreen(
        navigateBack = { },
        uiState = SignupUiState(),
        shakeOffset = remember { Animatable(0f) },
        updateEmail = { },
        updateDomain = { }
    )
}