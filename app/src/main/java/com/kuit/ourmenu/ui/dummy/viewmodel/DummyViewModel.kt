package com.kuit.ourmenu.ui.dummy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.kuit.ourmenu.data.repository.DummyRepository
import com.kuit.ourmenu.ui.dummy.state.DummyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyRepository: DummyRepository
) : ViewModel() {

    private var _dummyUiState = MutableStateFlow(DummyState())
    val dummyUiState: StateFlow<DummyState> =
        _dummyUiState.asStateFlow() // 명시적 타입 선언 안 해도 됨. or asStateFlow() 안붙여도됨


    init {
        getDummyData()
    }

    private fun getDummyData() {
        viewModelScope.launch {
            dummyRepository.getDummyData().fold(
                onSuccess = {
                    _dummyUiState.update {
                        it.copy(dummyString = it.dummyString)
                    }
                },
                onFailure = { error ->
                    Log.e("DummyViewModel", "getDummyData: $error")
                }
            )
        }
    }

}
