package com.kuit.ourmenu.data.model.home.response

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val answer: String = "",
    val answerRecommendMenus: List<RecommendMenuList> = emptyList(),
    val tag: String = "",
    val tagRecommendMenus: List<RecommendMenuList> = emptyList(),
    val otherRecommendMenus: List<RecommendMenuList> = emptyList(),
)

@Serializable
data class RecommendMenuList(
    val menuId: Int,
    val menuName: String,
    val menuPrice: Int,
    val menuImgUrl: String,
)