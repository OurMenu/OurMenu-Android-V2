package com.kuit.ourmenu.ui.my.screen

import android.R.attr.bottom
import android.R.attr.text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.my.component.DeleteAccountModal
import com.kuit.ourmenu.ui.my.component.LogoutModal
import com.kuit.ourmenu.ui.my.component.MyBottomModal
import com.kuit.ourmenu.ui.my.component.MyCurrentPasswordModal
import com.kuit.ourmenu.ui.my.component.MyMealTime
import com.kuit.ourmenu.ui.my.component.MyNewPasswordModal
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralBlack
import com.kuit.ourmenu.ui.theme.OurMenuTypography
import com.kuit.ourmenu.utils.ViewUtil.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    var bottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    var showCurrentPasswordModal by rememberSaveable { mutableStateOf(false) }
    var showNewPasswordModal by rememberSaveable { mutableStateOf(false) }
    var showLogoutModal by rememberSaveable { mutableStateOf(false) }
    var showDeleteAccountModal by rememberSaveable { mutableStateOf(false) }

    Box {
        Scaffold(
            topBar = {
                OurMenuAddButtonTopAppBar(
                    modifier = Modifier
                        .drawBehind {
                            drawRect(
                                color = NeutralBlack
                            )
                        }
                        .shadow(elevation = 4.dp)
                )
            }
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
                            text = stringResource(R.string.email),
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
                                onClick = {
                                    bottomSheetVisible = true
                                }
                            ),
                        tint = Neutral700
                    )
                }

                MyMealTime(
                    // TODO: mealTimes 데이터 받아오기
                )

                Column(
                    modifier = Modifier
                        .padding(top = 32.dp)
                ) {
                    InfoRow(infoTitle = stringResource(R.string.notice)) {
                        // TODO: 공지사항 화면으로 이동
                    }
                    InfoRow(infoTitle = stringResource(R.string.customer_service)) {
                        // TODO: 고객센터 화면으로 이동
                    }
                    InfoRow(infoTitle = stringResource(R.string.app_review)) {
                        // TODO: 앱 리뷰 화면으로 이동
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
        if (bottomSheetVisible) {
            MyBottomModal(
                onDismissRequest = { bottomSheetVisible = false },
                onChangePassword = {
                    showCurrentPasswordModal = true
                    bottomSheetVisible = false
                },
                onLogout = {
                    showLogoutModal = true
                    bottomSheetVisible = false
                },
                onDeleteAccount = {
                    showDeleteAccountModal = true
                    bottomSheetVisible = false
                }
            )
        }

        // 비밀번호 변경 모달
        if (showCurrentPasswordModal) {
            MyCurrentPasswordModal(
                onDismiss = { showCurrentPasswordModal = false },
                onConfirm = {
                    showNewPasswordModal = true
                    showCurrentPasswordModal = false
                }
            )
        }

        // 새 비밀번호 입력 모달
        if (showNewPasswordModal) {
            MyNewPasswordModal(
                onDismiss = { showNewPasswordModal = false },
                onConfirm = {
                    // TODO: 비밀번호 변경 로직 추가
                    showNewPasswordModal = false
                }
            )
        }

        // 로그아웃 모달
        if (showLogoutModal) {
            LogoutModal(
                onDismiss = { showLogoutModal = false },
                onConfirm = {
                    // TODO: 로그아웃 로직 추가
                    showLogoutModal = false
                }
            )
        }

        // 계정 삭제 모달
        if (showDeleteAccountModal) {
            DeleteAccountModal(
                onDismiss = { showDeleteAccountModal = false },
                onConfirm = {
                    // TODO: 계정 삭제 로직 추가
                    showDeleteAccountModal = false
                }
            )
        }
    }
}

@Composable
fun InfoRow(
    infoTitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(start = 20.dp, end = 30.dp)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = infoTitle,
            style = OurMenuTypography().pretendard_600_16,
            color = Neutral900
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    MyScreen()
}

