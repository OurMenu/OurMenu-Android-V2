package com.kuit.ourmenu.data.model.home.response

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val answerImgUrl: String = "",
    val answerRecommendMenus: List<RecommendMenuList> = emptyList(),
    val tagRecommendImgUrl: String = "",
    val tagRecommendMenus: List<RecommendMenuList> = emptyList(),
    val otherRecommendImgUrl: String = "",
    val otherRecommendMenus: List<RecommendMenuList> = emptyList(),
)

@Serializable
data class RecommendMenuList(
    val menuId: Long = 0,
    val menuTitle: String = "",
    val storeName: String = "",
    val menuImgUrl: String = "",
)