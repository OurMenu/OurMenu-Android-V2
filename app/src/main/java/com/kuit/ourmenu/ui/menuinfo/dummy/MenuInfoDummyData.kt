package com.kuit.ourmenu.ui.menuinfo.dummy

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main

data class MenuInfoDummyData(
    @DrawableRes val imgRes: List<Int>,
    val menuTitle: String,
    val menuPrice: Int,
    val store: String,
    val menuFolderList: List<String>,
    val defaultTagList: List<MenuInfoTag> = listOf(),
    val customTagList: List<MenuInfoTag> = listOf(),
    val address: String,
    val memoTitle: String,
    val memoContent: String
) {
    companion object {
        private const val MENU_TITLE = "화산라멘"
        private const val STORE = "화산라멘 멘야마쯔리 홍대점"
        private const val ADDRESS = "서울 마포구 와우산로 112"
        private const val MEMO_TITLE = "매운 맛 선택 가능!"
        private const val MEMO_CONTENT = "* 덕유산 / ㅇㅇ산 / 한라산 \n" +
                "기본도 정말 매우니 조심하기"
        private const val TAG_NAME = "밥"

        val dummyData = MenuInfoDummyData(
            imgRes = listOf(
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
            ),
            menuTitle = MENU_TITLE,
            menuPrice = 14000,
            store = STORE,
            menuFolderList = listOf(
                "메뉴판1",
                "메뉴판22",
                "메뉴판333",
                "메뉴판4444",
                "메뉴판55555",
                "메뉴판666666",
                "메뉴판7777777",
                "메뉴판88888888",
                "메뉴판999999999"
            ),
            defaultTagList = listOf(
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
            ),
            customTagList = listOf(
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
                MenuInfoTag(
                    tagName = TAG_NAME,
                    tagIcon = R.drawable.ic_tag_rice,
                    isCustom = false,
                    containerColor = Neutral300,
                    contentColor = Neutral700
                ),
            ),
            address = ADDRESS,
            memoTitle = MEMO_TITLE,
            memoContent = MEMO_CONTENT

        )
    }
}

data class MenuInfoTag(
    val tagName: String,
    @DrawableRes val tagIcon: Int,
    var containerColor: Color,
    var contentColor: Color,
    val isCustom: Boolean,
) {
    init {
        if (isCustom) {
            contentColor = NeutralWhite
            containerColor = Primary500Main
        } else {
            contentColor = Neutral700
            containerColor = Neutral300
        }
    }
}