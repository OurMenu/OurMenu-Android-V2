package com.kuit.ourmenu.ui.onboarding.state

data class CacheState(
    val menuFolderIcons: List<String> = emptyList(),
    val menuPins: List<String> = emptyList(),
    val homeImgs : List<String> = emptyList(),
    val tags: List<String> = emptyList()
)

