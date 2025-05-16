package com.kuit.ourmenu.ui.onboarding.model

import com.kuit.ourmenu.ui.onboarding.state.KakaoState

data class LandingUiState(
    var kakaoState: KakaoState = KakaoState.Default,
    val error: String = "",
)
