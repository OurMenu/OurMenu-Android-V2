package com.kuit.ourmenu.ui.menuinfo.dummy

import androidx.annotation.DrawableRes
import com.kuit.ourmenu.R

data class MenuInfoDummyData(
    @DrawableRes val imgRes: Int,
) {
    companion object {
        private val dummyData = MenuInfoDummyData(
            imgRes = R.drawable.img_dummy_pizza,
        )
        val dummyPageDataList = listOf(
            dummyData,
            dummyData,
            dummyData
        )
    }
}