package com.kuit.ourmenu.dummy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.dummy.data.DummyData
import com.kuit.ourmenu.dummy.repository.DummyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyRepository: DummyRepository
) : ViewModel() {

    private val _dummyData: MutableStateFlow<DummyData> = MutableStateFlow(DummyData())
    val dummyData: StateFlow<DummyData> = _dummyData.asStateFlow() // asStateFlow() 왜 달아야하는진 모름

    init {
        fetchDummyData()
    }

    private fun fetchDummyData() {
        viewModelScope.launch {
            dummyRepository.getDummyData()
                .catch { e -> println("Error: $e") }
                .collect { dummyData ->
                    _dummyData.value = dummyData
                }
        }
    }

}