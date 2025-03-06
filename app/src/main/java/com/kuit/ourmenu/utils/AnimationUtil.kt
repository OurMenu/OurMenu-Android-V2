package com.kuit.ourmenu.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.keyframes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AnimationUtil {
    private val shakeKeyframes: AnimationSpec<Float> = keyframes {
        durationMillis = 800
        val easing = FastOutLinearInEasing

        // generate 8 keyframes
        for (i in 1..8) {
            val x = when (i % 3) {
                0 -> 4f
                1 -> -4f
                else -> 0f
            }
            x at durationMillis / 10 * i with easing
        }
    }

    fun shakeAnimation(
        offset: Animatable<Float, AnimationVector1D>,
        coroutineScope: CoroutineScope,
    ) {
        coroutineScope.launch {
            offset.animateTo(
                targetValue = 0f,
                animationSpec = shakeKeyframes,
            )
        }
    }
}