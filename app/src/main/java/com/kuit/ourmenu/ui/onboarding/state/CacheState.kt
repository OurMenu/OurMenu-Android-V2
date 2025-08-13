package com.kuit.ourmenu.ui.onboarding.state

import com.kuit.ourmenu.data.model.cache.response.CacheInfoResponse

data class CacheState(
    val menuFolderIcons: List<String> = emptyList(),
    val menuPinMaps: List<String> = emptyList(),
    val menuAdds: List<String> = emptyList(),
    val menuPinAddDisables: List<String> = emptyList(),
    val homeImgs: List<String> = emptyList(),
    val orangeTags: List<String> = emptyList(),
    val whiteTags: List<String> = emptyList()
)

fun CacheInfoResponse.toState() = CacheState(
    homeImgs = homeImgs.map { it.homeImgUrl },
    menuFolderIcons = menuFolderIcons.map { it.menuFolderIconUrl },
    menuAdds = menuPins.mapNotNull { it.menuPinAddImgUrl },
    menuPinMaps = menuPins.map { it.menuPinMapImgUrl },
    menuPinAddDisables = menuPins.mapNotNull { it.menuPinAddDisableImgUrl },
    orangeTags = tags.map { it.orangeTagImgUrl },
    whiteTags = tags.map { it.whiteTagImgUrl }
)


