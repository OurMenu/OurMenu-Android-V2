package com.kuit.ourmenu.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
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

    fun shakeErrorInputField(
        shakeOffset: Animatable<Float, AnimationVector1D>,
        message: String,
        snackbarHostState: SnackbarHostState,
        scope: CoroutineScope,
    ) {
        shakeAnimation(
            offset = shakeOffset,
            coroutineScope = scope,
        )
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    fun shakeErrorInputFieldWithFocus(
        shakeOffset: Animatable<Float, AnimationVector1D>,
        focusRequester: FocusRequester,
        message: String,
        snackbarHostState: SnackbarHostState,
        scope: CoroutineScope,
    ) {
        scope.launch {
            focusRequester.requestFocus()
            delay(800)
        }
        shakeAnimation(
            offset = shakeOffset,
            coroutineScope = scope,
        )
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }
}