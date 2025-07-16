package com.kuit.ourmenu.ui.my.screen

import android.content.Intent
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.common.model.PasswordState
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.my.component.DeleteAccountModal
import com.kuit.ourmenu.ui.my.component.LogoutModal
import com.kuit.ourmenu.ui.my.component.MyBottomModal
import com.kuit.ourmenu.ui.my.component.MyCurrentPasswordModal
import com.kuit.ourmenu.ui.my.component.MyMealTime
import com.kuit.ourmenu.ui.my.component.MyNewPasswordModal
import com.kuit.ourmenu.ui.my.viewmodel.MyPageUiState
import com.kuit.ourmenu.ui.my.viewmodel.MyPageViewModel
import com.kuit.ourmenu.ui.my.viewmodel.UserMealTime
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralBlack
import com.kuit.ourmenu.ui.theme.OurMenuTypography
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputField
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputFieldWithFocus
import com.kuit.ourmenu.utils.ViewUtil.noRippleClickable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyRoute(
    padding: PaddingValues,
    navigateToEdit: (List<Int>) -> Unit = {},
    navigateToLanding: () -> Unit = {},
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val shakeOffset = remember { Animatable(0f) }
    val focusRequester = remember { FocusRequester() }
    val passwordConfirmEnabled by remember {
        derivedStateOf {
            uiState.newPassword.isNotEmpty() && uiState.confirmNewPassword.isNotEmpty()
        }
    }

    LaunchedEffect(
        uiState.isLogoutSuccess,
        uiState.isDeleteAccountSuccess
    ) {
        if (uiState.isLogoutSuccess || uiState.isDeleteAccountSuccess)
            navigateToLanding()
    }

    LaunchedEffect(
        uiState.showCompleteSnackbar
    ) {
        if (uiState.showCompleteSnackbar) {
            snackbarHostState.showSnackbar(
                message = "비밀번호가 변경되었어요.",
                duration = SnackbarDuration.Short,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
    }

    LaunchedEffect(uiState.passwordState) {
        when (uiState.passwordState) {
            PasswordState.NotMeetCondition -> {
                shakeErrorInputFieldWithFocus(
                    shakeOffset = shakeOffset,
                    focusRequester = focusRequester,
                    message = "비밀번호 조건을 다시 확인해주세요.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
                scope.launch {
                    delay(1600)
                    viewModel.updatePasswordState(PasswordState.Default)
                }
            }

            PasswordState.DifferentPassword -> {
                shakeErrorInputField(
                    shakeOffset = shakeOffset,
                    message = "비밀번호가 일치하지 않아요.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
                scope.launch {
                    delay(800)
                    viewModel.updatePasswordState(PasswordState.Default)
                }
            }

            PasswordState.Valid -> {
                viewModel.changePassword()
            }

            PasswordState.IncorrectPassword -> {
                viewModel.reInputCurrentPassword()
                shakeErrorInputFieldWithFocus(
                    shakeOffset = shakeOffset,
                    focusRequester = focusRequester,
                    message = "현재 비밀번호가 일치하지 않아요.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }

            else -> {}
        }
    }

    MyScreen(
        padding = padding,
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        focusRequester = focusRequester,
        shakeOffset = shakeOffset,
        navigateToEdit = { navigateToEdit(uiState.mealTimes.map { it.mealTime }) },
        updatePasswordState = { viewModel.updatePasswordState(null) },
        logout = { viewModel.logout(context) },
        deleteAccount = { viewModel.deleteAccount(context) },
        updateBottomSheetVisible = viewModel::updateBottomSheetVisible,
        updateCurrentPasswordModalVisible = viewModel::updateCurrentPasswordModalVisible,
        updateNewPasswordModalVisible = viewModel::updateNewPasswordModalVisible,
        updateLogoutModalVisible = viewModel::updateLogoutModalVisible,
        updateDeleteAccountModalVisible = viewModel::updateDeleteAccountModalVisible,
        navigateToAnnouncement = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                uiState.announcementUrl.toUri()
            )
            context.startActivity(intent)
        },
        navigateToCustomerService = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                uiState.customerServiceUrl.toUri()
            )
            context.startActivity(intent)
        },
        navigateToReview = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                uiState.appReviewUrl.toUri()
            ).apply { setPackage("com.android.vending") }
            context.startActivity(intent)
        },
        passwordConfirmEnabled = passwordConfirmEnabled,
        updateCurrentPassword = viewModel::updateCurrentPassword,
        updateNewPassWordChange = viewModel::updateNewPassword,
        updateConfirmPasswordChange = viewModel::updateConfirmNewPassword,
        updatePasswordVisibilityChange = viewModel::updatePasswordVisibility
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        OurSnackbarHost(
            hostState = snackbarHostState,
            isChecked = true,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(
    padding: PaddingValues,
    uiState: MyPageUiState,
    snackbarHostState: SnackbarHostState,
    focusRequester: FocusRequester,
    shakeOffset: Animatable<Float, AnimationVector1D>,
    navigateToEdit: () -> Unit = {},
    updatePasswordState: () -> Unit = {},
    logout: () -> Unit = {},
    deleteAccount: () -> Unit = {},
    updateBottomSheetVisible: (Boolean) -> Unit = {},
    updateCurrentPasswordModalVisible: (Boolean) -> Unit = {},
    updateNewPasswordModalVisible: (Boolean) -> Unit = {},
    updateLogoutModalVisible: (Boolean) -> Unit = {},
    updateDeleteAccountModalVisible: (Boolean) -> Unit = {},
    navigateToAnnouncement: () -> Unit = {},
    navigateToCustomerService: () -> Unit = {},
    navigateToReview: () -> Unit = {},
    passwordConfirmEnabled: Boolean,
    updateCurrentPassword: (String) -> Unit = {},
    updateNewPassWordChange: (String) -> Unit = {},
    updateConfirmPasswordChange: (String) -> Unit = {},
    updatePasswordVisibilityChange: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
    ) {
        Scaffold(
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = null,
                            modifier = Modifier.size(44.dp),
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = uiState.email,
                            style = OurMenuTypography().pretendard_700_14,
                            color = Neutral900
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_kebab),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .noRippleClickable(
                                onClick = { updateBottomSheetVisible(true) }
                            ),
                        tint = Neutral700
                    )
                }

                MyMealTime(
                    navigateToEdit = navigateToEdit,
                    mealTimes = uiState.mealTimes
                )

                Column(
                    modifier = Modifier
                        .padding(top = 22.dp)
                ) {
                    InfoRow(infoTitle = stringResource(R.string.notice)) {
                        navigateToAnnouncement()
                    }
                    InfoRow(infoTitle = stringResource(R.string.customer_service)) {
                        navigateToCustomerService()
                    }
                    InfoRow(infoTitle = stringResource(R.string.app_review)) {
                        navigateToReview()
                    }
                    Text(
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        text = stringResource(R.string.app_version, "2.0"),
                        style = OurMenuTypography().pretendard_600_16,
                        color = Neutral900
                    )
                }
            }
        }

        // 모달 처리
        if (uiState.bottomSheetVisible) {
            MyBottomModal(
                onDismissRequest = { updateBottomSheetVisible(false) },
                onChangePassword = {
                    updateCurrentPasswordModalVisible(true)
                    updateBottomSheetVisible(false)
                },
                onLogout = {
                    updateLogoutModalVisible(true)
                    updateBottomSheetVisible(false)
                },
                onDeleteAccount = {
                    updateDeleteAccountModalVisible(true)
                    updateBottomSheetVisible(false)
                }
            )
        }

        // 비밀번호 변경 모달
        if (uiState.showCurrentPasswordModal) {
            MyCurrentPasswordModal(
                onDismiss = { updateCurrentPasswordModalVisible(false) },
                onConfirm = {
                    updateNewPasswordModalVisible(true)
                },
                snackbarHostState = snackbarHostState,
                currentPassword = uiState.currentPassword,
                isPasswordVisible = uiState.isPasswordViewVisible,
                updatePassword = { updateCurrentPassword(it) },
                updatePasswordVisible = { updatePasswordVisibilityChange() },
                focusRequester = focusRequester,
                passwordState = uiState.passwordState,
                shakeOffset = shakeOffset,
            )
        }

        // 새 비밀번호 입력 모달
        if (uiState.showNewPasswordModal) {
            MyNewPasswordModal(
                onDismiss = { updateNewPasswordModalVisible(false) },
                onConfirm = { updatePasswordState() },
                focusRequester = focusRequester,
                shakeOffset = shakeOffset,
                snackbarHostState = snackbarHostState,
                newPassword = uiState.newPassword,
                passwordState = uiState.passwordState,
                confirmNewPassword = uiState.confirmNewPassword,
                isPasswordVisible = uiState.isPasswordViewVisible,
                passwordConfirmEnabled = passwordConfirmEnabled,
                updateNewPassWordChange = { updateNewPassWordChange(it) },
                updateConfirmPasswordChange = { updateConfirmPasswordChange(it) },
                updatePasswordVisibilityChange = { updatePasswordVisibilityChange() },

                )
        }

        // 로그아웃 모달
        if (uiState.showLogoutModal) {
            LogoutModal(
                onDismiss = { updateLogoutModalVisible(false) },
                onConfirm = {
                    logout()
                }
            )
        }

        // 계정 삭제 모달
        if (uiState.showDeleteAccountModal) {
            DeleteAccountModal(
                onDismiss = { updateDeleteAccountModalVisible(false) },
                onConfirm = { deleteAccount() }
            )
        }
    }
}

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    infoTitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(start = 20.dp, end = 30.dp)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = infoTitle,
            style = OurMenuTypography().pretendard_600_16.copy(
                lineHeight = 24.sp
            ),
            color = Neutral900
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    MyScreen(
        uiState = MyPageUiState(
            email = "ourmenu@gmai.com",
            mealTimes = listOf(
                UserMealTime(
                    10, true
                ),
                UserMealTime(
                    13, true
                ),
                UserMealTime(
                    16, true
                )
            )
        ),
        padding = PaddingValues(),
        snackbarHostState = SnackbarHostState(),
        focusRequester = FocusRequester(),
        shakeOffset = remember { Animatable(0f) },
        passwordConfirmEnabled = true,
    )
}

