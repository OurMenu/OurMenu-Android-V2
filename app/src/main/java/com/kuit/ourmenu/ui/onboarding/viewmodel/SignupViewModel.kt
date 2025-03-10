package com.kuit.ourmenu.ui.onboarding.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.kuit.ourmenu.ui.onboarding.model.MealTimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _domain = MutableStateFlow("")
    val domain = _domain.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _codes = MutableStateFlow(mutableListOf("", "", "", "", "", ""))
    val codes: StateFlow<List<String>> = _codes.asStateFlow()

    private val _mealTimes = MutableStateFlow(
        mutableStateListOf(
            *List(18) {
                MealTimeState(mealTime = "${it + 6}:00")
            }.toTypedArray()
        )
    )
    val mealTimes: StateFlow<List<MealTimeState>> = _mealTimes.asStateFlow()

    private val _selectedTimes = MutableStateFlow(mutableStateListOf<String>())
    val selectedTimes: StateFlow<List<String>> = _selectedTimes.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updateDomain(domain: String) {
        _domain.value = domain
    }

    fun updateCode(index: Int, code: String) {
        _codes.value[index] = code
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun addSelectedTime(index: Int, selectedTime: String) {
        _selectedTimes.value.add(selectedTime)
        _mealTimes.value[index] = _mealTimes.value[index].copy(selected = true)
    }

    fun removeSelectedTime(index: Int, selectedTime: String) {
        _selectedTimes.value.remove(selectedTime)
        _mealTimes.value[index] = _mealTimes.value[index].copy(selected = false)
    }

}