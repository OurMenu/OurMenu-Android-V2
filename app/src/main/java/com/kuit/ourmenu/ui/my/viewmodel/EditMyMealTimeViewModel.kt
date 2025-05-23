package com.kuit.ourmenu.ui.my.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMyMealTimeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditMyMealTimeUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedTime(index: Int) {
        _uiState.update {
            val selected = it.mealTimes[index].selected
            val currentSelectedTimes = it.selectedTimes.toMutableList()
            val mealTime = it.mealTimes[index].mealTime

            it.copy(
                mealTimes = it.mealTimes.toMutableList()
                    .apply {
                        this[index].selected = !selected && currentSelectedTimes.size < 4
                    }.toList(),
                selectedTimes =
                    run {
                        if (!selected && currentSelectedTimes.size < 4) {
                            currentSelectedTimes.add(mealTime)
                        } else currentSelectedTimes.remove(mealTime)
                        currentSelectedTimes.toList()
                    },
            )
        }
    }

    fun setSelectedTimes(selectedTimes: List<Int>) {
        _uiState.update {
            it.copy(
                selectedTimes = selectedTimes,
                mealTimes = it.mealTimes.map { mealTime ->
                    mealTime.copy(selected = selectedTimes.contains(mealTime.mealTime))
                }
            )
        }
    }

    fun changeMealTime() {
        viewModelScope.launch {
            userRepository.updateMealTimes(
                newMealTimes = _uiState.value.selectedTimes
            ).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(isSuccess = true)
                    }
                },
                onFailure = {
                    Log.e(
                        "EditMyMealTimeViewModel", "changeMealTime: ${it.message}", it
                    )
                }
            )
        }
    }

}