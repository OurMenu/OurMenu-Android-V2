package com.kuit.ourmenu.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.keyframes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AnimationUtil {

    fun shakeAnimation(
        offset: Animatable<Float, AnimationVector1D>,
        coroutineScope: CoroutineScope,
        durationMillis: Int = 800
    ) {
        val shakeKeyframes: AnimationSpec<Float> = keyframes {
            val easing = FastOutLinearInEasing

            // 좀 더 랜덤한 흔들림 값을 설정
            listOf(-8f, 8f, -6f, 6f, -4f, 4f, -2f, 2f, 0f).forEachIndexed { index, x ->
                x at (durationMillis / 9 * (index + 1)) using easing
            }
        }

        coroutineScope.launch {
            offset.animateTo(
                targetValue = 0f,
                animationSpec = shakeKeyframes,
            )
        }
    }
}