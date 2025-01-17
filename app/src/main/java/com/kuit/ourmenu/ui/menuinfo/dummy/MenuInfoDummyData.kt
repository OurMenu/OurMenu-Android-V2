package com.kuit.ourmenu.ui.menuinfo.dummy

import androidx.annotation.DrawableRes
import com.kuit.ourmenu.R

data class MenuInfoDummyData(
    @DrawableRes val imgRes: List<Int>,
    val menuTitle: String,
    val menuPrice: Int,
    val store: String,
    val menuFolderList: List<String>
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
            "화산라멘 멘야마쯔리 홍대점",
            listOf(
                "메뉴판1",
                "메뉴판22",
                "메뉴판333",
                "메뉴판4444",
                "메뉴판55555",
                "메뉴판666666",
                "메뉴판7777777",
                "메뉴판88888888",
                "메뉴판999999999"
            )
        )
    }
}