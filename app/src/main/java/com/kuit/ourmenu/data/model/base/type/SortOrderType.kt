package com.kuit.ourmenu.data.model.base.type

enum class
SortOrderType(val apiValue: String, val displayName: String) {
    TITLE_ASC("TITLE_ASC", "이름순"),
//    TITLE_DESC("TITLE_DESC", "이름역순"),
//    CREATED_AT_ASC("CREATED_AT_ASC", "최신순"),
    CREATED_AT_DESC("CREATED_AT_DESC", "등록순"),
    PRICE_ASC("PRICE_ASC", "가격순");
//    PRICE_DESC("PRICE_DESC", "높은 가격순");

    override fun toString(): String = displayName
}