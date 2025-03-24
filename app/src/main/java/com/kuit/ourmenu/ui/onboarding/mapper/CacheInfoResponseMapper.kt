package com.kuit.ourmenu.ui.onboarding.mapper

import com.kuit.ourmenu.data.model.cache.response.CacheInfoResponse
import com.kuit.ourmenu.ui.onboarding.state.CacheState

fun CacheInfoResponse.toState() = CacheState(
    homeImgs = homeImgs.map { it.homeImgUrl },
    menuFolderIcons = menuFolderIcons.map { it.menuFolderIconUrl },
    menuAdds = menuPins.mapNotNull { it.menuPinAddImgUrl },
    menuPinMaps = menuPins.map { it.menuPinMapImgUrl },
    menuPinAddDisables = menuPins.mapNotNull { it.menuPinAddDisableImgUrl },
    orangeTags = tags.map { it.orangeTagImgUrl },
    whiteTags = tags.map { it.whiteTagImgUrl }
)
