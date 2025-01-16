package com.kuit.ourmenu.ui.menuinfo.dummy

import androidx.annotation.DrawableRes
import com.kuit.ourmenu.R

data class MenuInfoDummyData(
    @DrawableRes val imgRes: List<Int>,
    val menuTitle: String,
    val menuPrice: Int,
    val store: String,
) {
    companion object {
        val dummyData = MenuInfoDummyData(
            listOf(
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
            ),
            "화산라멘",
            14000,
            "화산라멘 멘야마쯔리 홍대점")
    }
}