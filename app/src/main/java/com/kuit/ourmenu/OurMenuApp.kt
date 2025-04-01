package com.kuit.ourmenu

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.memory.MemoryCache
import coil3.util.DebugLogger
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OurMenuApp : Application(), SingletonImageLoader.Factory {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
        /*
        * 이 부분을 주석 해제해서 keyHash 값 읽으시면 됩니다.
        * val keyHash = Utility.getKeyHash(this)
        * Log.d("KeyHash", keyHash)
        * */

    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .logger(DebugLogger())
            .memoryCache(
                MemoryCache.Builder()
                    .maxSizePercent(context, 0.35)
                    .build()
            )
            .build()
    }
}