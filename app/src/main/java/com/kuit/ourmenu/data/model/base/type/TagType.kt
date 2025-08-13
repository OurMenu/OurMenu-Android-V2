package com.kuit.ourmenu.data.model.base.type

enum class TagType(val apiValue: String, val displayName: String) {
    KOREA("KOREA", "한식"),
    CHINA("CHINA", "중식"),
    JAPAN("JAPAN", "일식"),
    WESTERN("WESTERN", "양식"),
    ASIA("ASIA", "아시안"),
    RICE("RICE", "밥"),
    BREAD("BREAD", "빵"),
    NOODLE("NOODLE", "면"),
    MEAT("MEAT", "고기"),
    FISH("FISH", "생선"),
    DESSERT("DESSERT", "디저트"),
    CAFE("CAFE", "카페"),
    FAST_FOOD("FAST_FOOD", "패스트푸드"),
    SPICY("SPICY", "매콤함"),
    SWEET("SWEET", "달달함"),
    COOL("COOL", "시원함"),
    HOT("HOT", "뜨끈함"),
    HOT_SPICY("HOT_SPICY", "얼큰함"),
    SOLO("SOLO", "혼밥"),
    BUSINESS("BUSINESS", "비즈니스 미팅"),
    PROMISE("PROMISE", "친구 약속"),
    DATE("DATE", "데이트"),
    BUY_FOOD("BUY_FOOD", "밥약"),
    ORGANIZATION("ORGANIZATION", "단체");

    override fun toString(): String = displayName

    companion object {
        fun fromDisplayName(displayName: String): TagType? =
            entries.firstOrNull { it.displayName == displayName }

        fun toApiValues(tags: List<TagType>): List<String> =
            tags.map { it.apiValue }
    }
}