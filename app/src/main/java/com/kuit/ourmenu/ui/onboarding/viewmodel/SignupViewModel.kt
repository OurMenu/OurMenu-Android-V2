package com.kuit.ourmenu.ui.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignupViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _domain = MutableStateFlow("")
    val domain = _domain.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _codes = MutableStateFlow(mutableListOf("", "", "", "", "", ""))
    val codes: StateFlow<List<String>> = _codes

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updateDomain(domain: String) {
        _domain.value = domain
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateCode(index: Int, code: String) {
        _codes.value[index] = code
    }

}