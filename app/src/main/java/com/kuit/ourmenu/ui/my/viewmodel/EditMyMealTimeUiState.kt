package com.kuit.ourmenu.ui.my.viewmodel

import com.kuit.ourmenu.ui.common.model.MealTime


data class EditMyMealTimeUiState(
    val mealTimes: List<MealTime> = List(18) {
        MealTime(mealTime = "${it + 6}:00")
    },
    val selectedTimes: List<String> = emptyList(),
)

