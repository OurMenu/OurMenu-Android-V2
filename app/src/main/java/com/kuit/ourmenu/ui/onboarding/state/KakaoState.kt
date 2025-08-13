package com.kuit.ourmenu.ui.onboarding.state

sealed class KakaoState {
    data object Default: KakaoState()
    data object Loading : KakaoState()
    data object Login : KakaoState()
    data object Signup : KakaoState()
    data object Error : KakaoState()
}