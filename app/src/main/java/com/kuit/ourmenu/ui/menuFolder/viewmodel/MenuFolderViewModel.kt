package com.kuit.ourmenu.ui.menuFolder.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderResponse
import com.kuit.ourmenu.data.repository.MenuFolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuFolderViewModel @Inject constructor(
    private val menuFolderRepository: MenuFolderRepository
) : ViewModel() {

    private val _menuFolders = MutableStateFlow<List<MenuFolderResponse>>(emptyList())
    val menuFolders = _menuFolders.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getMenuFolders()
    }

    fun getMenuFolders() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            menuFolderRepository.getMenuFolders()
                .fold(
                    onSuccess = { response ->
                        if (response != null) {
                            _menuFolders.value = response.sortedBy { it.index }
                            Log.d("test", _menuFolders.value.toString())
                        }
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "메뉴 폴더를 불러오는 중 오류가 발생했습니다."
                        Log.d("test2", _error.value.toString())
                    }
                )

            _isLoading.value = false
        }
    }
}