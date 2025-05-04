package com.kuit.ourmenu.ui.searchmenu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kuit.ourmenu.data.model.map.response.CrawlingHistoryResponse
import com.kuit.ourmenu.data.repository.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchMenuViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {
    private val _searchHistory = MutableStateFlow<List<CrawlingHistoryResponse>?>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()

    suspend fun getCrawlingHistory() {
        val response = mapRepository.getCrawlingHistory()
        response.onSuccess {
            _searchHistory.value = it
        }.onFailure {
            // Handle error
            Log.d("SearchMenuViewModel", "Error fetching crawling history: ${it.message}")
        }
    }

    suspend fun getCrawlingStoreDetail(
        isCrawled: Boolean,
        storeId: String
    ) {
        val response = mapRepository.getCrawlingStoreDetail(
            isCrawled = isCrawled,
            storeId = storeId
        )

    }

    suspend fun getCrawlingStoreInfo(
        query: String,
        mapX: Double,
        mapY: Double
    ) {
        val response = mapRepository.getCrawlingStoreInfo(
            query = query,
            mapX = mapX,
            mapY = mapY
        )
    }
}