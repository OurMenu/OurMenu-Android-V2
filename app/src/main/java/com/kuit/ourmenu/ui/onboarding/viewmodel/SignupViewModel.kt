package com.kuit.ourmenu.ui.onboarding.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.ui.onboarding.model.MealTimeState
import com.kuit.ourmenu.ui.onboarding.state.LoginState
import com.kuit.ourmenu.ui.onboarding.state.SignupState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

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

    private val _signupState: MutableStateFlow<SignupState> = MutableStateFlow(SignupState.Default)
    val signupState: StateFlow<SignupState> = _signupState.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

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

    /* Api Section */
    fun sendEmail() {
        viewModelScope.launch {
            authRepository.sendEmail(email.value)
                .fold(
                    onSuccess = {
                        _signupState.value = SignupState.Success
                    },
                    onFailure = { error ->
                        _signupState.value = SignupState.Error
                        _error.value = error.message

                        delay(1000)
                        _signupState.value = SignupState.Default
                    }
                )
        }
    }

}