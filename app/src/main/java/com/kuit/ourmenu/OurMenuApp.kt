package com.kuit.ourmenu

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OurMenuApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
        /*
        * 이 부분을 주석 해제해서 keyHash 값 읽으시면 됩니다.
        * val keyHash = Utility.getKeyHash(this)
        * Log.d("KeyHash", keyHash)
        * */

    }
}