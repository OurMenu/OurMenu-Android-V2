package com.kuit.ourmenu.ui.my.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditMyMealTimeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EditMyMealTimeUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedTime(index: Int) {
        _uiState.update {
            val selected = it.mealTimes[index].selected
            it.copy(
                selectedTimes =
                    if (selected) it.selectedTimes.toMutableList() - it.mealTimes[index].mealTime
                    else it.selectedTimes.toMutableList() + it.mealTimes[index].mealTime,
                mealTimes = it.mealTimes.toMutableList()
                    .apply { this[index].selected = !selected }
            )
        }
    }

}