package com.kuit.ourmenu.ui.home.dummy

import com.kuit.ourmenu.R

data class HomeDummyData(
    val imageRes: Int,
    val name: String,
    val store: String,
) {

    companion object {
        val dummyData = listOf(
            HomeDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
            HomeDummyData(
                imageRes = 0,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
            HomeDummyData(
                imageRes = 0,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
        )
    }
}