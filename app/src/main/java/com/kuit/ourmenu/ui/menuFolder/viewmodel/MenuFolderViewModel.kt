package com.kuit.ourmenu.ui.menuFolder.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.menuFolder.request.MenuFolderIndexRequest
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderList
import com.kuit.ourmenu.data.repository.MenuFolderRepository
import com.kuit.ourmenu.utils.dragndrop.move
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuFolderViewModel @Inject constructor(
    private val menuFolderRepository: MenuFolderRepository
) : ViewModel() {

    private val _menuFolders = MutableStateFlow<List<MenuFolderList>>(emptyList())
    val menuFolders = _menuFolders.asStateFlow()

    private val _menuCount = MutableStateFlow(0)
    val menuCount = _menuCount.asStateFlow()

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
                            _menuFolders.value = response.menuFolders.sortedBy { it.index }
                            _menuCount.value = response.menuCount
                        }
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "메뉴 폴더를 불러오는 중 오류가 발생했습니다."
                    }
                )

            _isLoading.value = false
        }
    }

    fun deleteMenuFolder(menuFolderId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            menuFolderRepository.deleteMenuFolder(menuFolderId.toLong())
                .fold(
                    onSuccess = {
                        getMenuFolders() // Refresh the list after deletion
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "메뉴 폴더 삭제 중 오류가 발생했습니다."
                    }
                )

            _isLoading.value = false
        }
    }

    fun updateMenuFolderList(from: Int, to: Int) {
        val newMenuFolders = _menuFolders.value.toMutableList()
        newMenuFolders.move(from, to)
        _menuFolders.update { newMenuFolders }
    }

    fun patchMenuFolders(fromId: Int, to: Int) {

        val toIndex = to.coerceAtMost(_menuFolders.value.size - 1)

        val index = _menuFolders.value[toIndex].index

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            menuFolderRepository.updateMenuFolderIndex(
                fromId.toLong(),
                MenuFolderIndexRequest(toIndex)
            )
                .fold(
                    onSuccess = {
                        getMenuFolders() // Refresh the list after patching
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "메뉴 폴더 순서 변경 중 오류가 발생했습니다."
                    }
                )

            _isLoading.value = false
        }
    }
}