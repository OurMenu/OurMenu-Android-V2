package com.kuit.ourmenu.data.model.menuFolder.response

enum class SortOrderType(val apiValue: String, val displayName: String) {
    TITLE_ASC("TITLE_ASC", "이름순"),
//    TITLE_DESC("TITLE_DESC", "이름역순"),
    CREATED_AT_ASC("CREATED_AT_ASC", "등록순"),
//    CREATED_AT_DESC("CREATED_AT_DESC", "최신순"),
//    PRICE_ASC("PRICE_ASC", "낮은 가격순");
    PRICE_DESC("PRICE_DESC", "가격순");

    override fun toString(): String = displayName
}