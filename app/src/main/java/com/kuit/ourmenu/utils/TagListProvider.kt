package com.kuit.ourmenu.utils

import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.base.type.TagType

object TagListProvider {
    val categoryTagList = listOf(
        R.drawable.ic_tag_rice to TagType.RICE,
        R.drawable.ic_tag_rice to TagType.BREAD,
        R.drawable.ic_tag_rice to TagType.NOODLE,
        R.drawable.ic_tag_rice to TagType.MEAT,
        R.drawable.ic_tag_rice to TagType.FISH,
        R.drawable.ic_tag_rice to TagType.DESSERT,
        R.drawable.ic_tag_rice to TagType.CAFE,
        R.drawable.ic_tag_rice to TagType.FAST_FOOD
    )

    val nationalityTagList = listOf(
        R.drawable.ic_tag_rice to TagType.KOREA,
        R.drawable.ic_tag_rice to TagType.CHINA,
        R.drawable.ic_tag_rice to TagType.JAPAN,
        R.drawable.ic_tag_rice to TagType.WESTERN,
        R.drawable.ic_tag_rice to TagType.ASIA
    )

    val tasteTagList = listOf(
        R.drawable.ic_tag_rice to TagType.SPICY,
        R.drawable.ic_tag_rice to TagType.SWEET,
        R.drawable.ic_tag_rice to TagType.COOL,
        R.drawable.ic_tag_rice to TagType.HOT,
        R.drawable.ic_tag_rice to TagType.HOT_SPICY
    )

    val occasionTagList = listOf(
        R.drawable.ic_tag_rice to TagType.SOLO,
        R.drawable.ic_tag_rice to TagType.BUSINESS,
        R.drawable.ic_tag_rice to TagType.PROMISE,
        R.drawable.ic_tag_rice to TagType.DATE,
        R.drawable.ic_tag_rice to TagType.BUY_FOOD,
        R.drawable.ic_tag_rice to TagType.ORGANIZATION
    )
}