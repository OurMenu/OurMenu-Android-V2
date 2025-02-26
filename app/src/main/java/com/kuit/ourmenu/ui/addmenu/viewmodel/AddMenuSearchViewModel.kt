package com.kuit.ourmenu.ui.addmenu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddMenuSearchViewModel : ViewModel() {
    // 최근 검색 결과를 저장
    private val _recentSearchResults = MutableStateFlow<List<Boolean>>(emptyList())
    val recentSearchResults: StateFlow<List<Boolean>> = _recentSearchResults

    //실제 검색 결과를 저장
    private val _searchResulst = MutableStateFlow<List<Boolean>>(emptyList())
    val searchResults: StateFlow<List<Boolean>> = _searchResulst

    //식당 정보
    private val _storeInfo = MutableStateFlow(AddMenuDummyStoreInfo())
    val storeInfo: StateFlow<AddMenuDummyStoreInfo> = _storeInfo

    init {
        getRecentSearchResults()
        //확인용, 이후에는 제거
        getSearchResults()
        getRestaurantInfo()
    }

    fun getRecentSearchResults() {
        viewModelScope.launch {
            _recentSearchResults.value = listOf(false, false, false, false, true)
        }
    }

    fun getSearchResults() {
        viewModelScope.launch {
            _searchResulst.value = listOf()
        }
    }

    fun getRestaurantInfo() {
        viewModelScope.launch {
            _storeInfo.value = AddMenuDummyStoreInfo(
                imgList = listOf(
                    R.drawable.img_dummy_pizza,
                    R.drawable.img_dummy_pizza,
                    R.drawable.img_dummy_pizza,
                ),
                name = R.string.our_ddeokbokki.toString(),
                address = R.string.resaturant_address.toString(),
                menuList = listOf(false, false, false, false, false, false, false, false)
            )
        }
    }

    fun updateSelectedMenu(index: Int) {
        Log.d("AddMenuViewModel", "index: $index")
        viewModelScope.launch {
            val currentState = _storeInfo.value.menuList[index]
            val updatedMenuList =
                if (currentState){
                    //선택된 아이템 클릭시 false로 다시 변경
                    _storeInfo.value.menuList.map {
                        false
                    }
                }else{
                    //클릭된 인덱스만 true, 나머지는 false
                    _storeInfo.value.menuList.mapIndexed { i, _ ->
                        i == index
                }
            }
//            Log.d("AddMenuViewModel", "updatedMenuList: $updatedMenuList")
            _storeInfo.value = _storeInfo.value.copy(menuList = updatedMenuList)
        }
    }
}

//이후에 dto 반영시 삭제 예정
data class AddMenuDummyStoreInfo(
    val imgList: List<Int> = emptyList(),
    val name: String = "",
    val address: String = "",
    val menuList: List<Boolean> = emptyList(),
)