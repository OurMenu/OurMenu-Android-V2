package com.kuit.ourmenu.ui.addmenu.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddMenuSearchViewModel : ViewModel() {
    // 최근 검색 결과를 저장하는 StateFlow
    private val _recentSearchResults = MutableStateFlow<List<Boolean>>(emptyList())
    val recentSearchResults: StateFlow<List<Boolean>> = _recentSearchResults
    //실제 검색 결과를 저장하는 StateFlow
    private val _searchResulst = MutableStateFlow<List<Boolean>>(emptyList())
    val searchResults: StateFlow<List<Boolean>> = _searchResulst

    init {
        getRecentSearchResults()
        //확인용, 이후에는 제거
        getSearchResults()
    }

    fun getRecentSearchResults() {
        _recentSearchResults.value = listOf(false, false, false, false, true)

    }

    fun getSearchResults(){
        _searchResulst.value = listOf()
    }
}