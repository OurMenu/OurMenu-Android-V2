package com.kuit.ourmenu.ui.onboarding.state

data class CacheState(
    val menuFolderIcons: List<String> = emptyList(),
    val menuPinMaps: List<String> = emptyList(),
    val menuAdds: List<String> = emptyList(),
    val menuPinAddDisables: List<String> = emptyList(),
    val homeImgs: List<String> = emptyList(),
    val orangeTags: List<String> = emptyList(),
    val whiteTags: List<String> = emptyList()
)

