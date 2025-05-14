package com.kuit.ourmenu.ui.menuFolder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderAllResponse
import com.kuit.ourmenu.data.model.menuFolder.response.SortOrderType
import com.kuit.ourmenu.data.model.menuFolder.response.TagType
import com.kuit.ourmenu.data.repository.MenuFolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuFolderAllViewModel @Inject constructor(
    private val menuFolderRepository: MenuFolderRepository,
) : ViewModel() {
    private val _menuFolderAll = MutableStateFlow<List<MenuFolderAllResponse>>(emptyList())
    val menuFolderAll = _menuFolderAll.asStateFlow()

    private val _sortOrder = MutableStateFlow(SortOrderType.TITLE_ASC)
    val sortOrder = _sortOrder.asStateFlow()

    private val _selectedTags = MutableStateFlow<List<String>>(emptyList())
    val selectedTags = _selectedTags.asStateFlow()

    private val _minPrice = MutableStateFlow<Long?>(null)
    val minPrice = _minPrice.asStateFlow()

    private val _maxPrice = MutableStateFlow<Long?>(null)
    val maxPrice = _maxPrice.asStateFlow()

    private val _page = MutableStateFlow<Int?>(null)
    val page = _page.asStateFlow()

    private val _size = MutableStateFlow(10)
    val size = _size.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchMenuFolderAll()
    }

    /** 정렬 필터 변경 */
    fun updateSortOrder(newSortOrder: SortOrderType) {
        if (_sortOrder.value != newSortOrder) {
            _sortOrder.value = newSortOrder
            fetchMenuFolderAll()
        }
    }

    /** 태그 필터 변경 */
    fun updateTags(tags: List<TagType>) {
        _selectedTags.value = TagType.toApiValues(tags)
        fetchMenuFolderAll()
    }

    /** 가격 필터 변경 */
    fun updatePriceRange(min: Long?, max: Long?) {
        _minPrice.value = min
        _maxPrice.value = max
        fetchMenuFolderAll()
    }

    /** 페이징 변경 */
    fun updatePagination(newPage: Int?, newSize: Int) {
        _page.value = newPage
        _size.value = newSize
        fetchMenuFolderAll()
    }

    private fun fetchMenuFolderAll() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            menuFolderRepository.getMenuFolderAll(
                tags = _selectedTags.value,
                minPrice = _minPrice.value,
                maxPrice = _maxPrice.value,
                page = _page.value,
                size = _size.value,
                sortOrder = _sortOrder.value.apiValue
            ).fold(
                onSuccess = { response ->
                    _menuFolderAll.value = response ?: emptyList()
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "메뉴 폴더를 불러오는 중 오류가 발생했습니다."
                }
            )

            _isLoading.value = false
        }
    }
}