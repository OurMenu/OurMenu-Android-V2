package com.kuit.ourmenu.ui.menuinfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.menuinfo.response.MenuInfoResponse
import com.kuit.ourmenu.data.repository.MenuInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuInfoViewModel @Inject constructor(
    private val menuInfoRepository: MenuInfoRepository
) : ViewModel() {
    private val _menuInfo = MutableStateFlow(MenuInfoResponse())
    val menuInfo = _menuInfo.asStateFlow()

    private val _menuId = MutableStateFlow(0)
    val menuId = _menuId.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getMenuInfo(
        menuId: Int
    ) {
        _menuId.value = menuId

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            menuInfoRepository.getMenuInfo(menuId)
                .fold(
                    onSuccess = { response ->
                        if (response != null) {
                            _menuInfo.value = response
                        }
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "메뉴 정보를 불러오는 중 오류가 발생했습니다."
                    }
                )

            _isLoading.value = false
        }
    }
}