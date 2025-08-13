package com.kuit.ourmenu.ui.menuFolder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.base.type.SortOrderType
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderDetailResponse
import com.kuit.ourmenu.data.repository.MenuFolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuFolderDetailViewModel @Inject constructor(
    private val menuFolderRepository: MenuFolderRepository,
): ViewModel() {
    private val _menuFolderDetail = MutableStateFlow(MenuFolderDetailResponse())
    val menuFolderDetail = _menuFolderDetail.asStateFlow()

    private val _menuFolderId = MutableStateFlow<Long>(0)
    val menuFolderId = _menuFolderId.asStateFlow()

    private val _sortOrder = MutableStateFlow(SortOrderType.TITLE_ASC)
    val sortOrder = _sortOrder.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getMenuFolderDetail(
        menuFolderId: Long,
        sortOrder: SortOrderType = _sortOrder.value
    ) {
        _menuFolderId.value = menuFolderId
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            menuFolderRepository.getMenuFolderDetail(menuFolderId, sortOrder.apiValue)
                .fold(
                    onSuccess = { response ->
                        if (response != null) {
                            _menuFolderDetail.value = response
                            _menuFolderId.value = menuFolderId
                            _sortOrder.value = sortOrder
                        }
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "메뉴 폴더를 불러오는 중 오류가 발생했습니다."
                    }
                )

            _isLoading.value = false
        }
    }

    fun updateSortOrder(sortOrderType: SortOrderType, menuFolderId: Long) {
        if (_sortOrder.value != sortOrderType) {
            _sortOrder.value = sortOrderType
            getMenuFolderDetail(menuFolderId, sortOrderType)
        }
    }
}