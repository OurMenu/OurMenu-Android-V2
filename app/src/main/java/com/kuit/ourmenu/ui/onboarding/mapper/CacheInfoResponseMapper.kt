package com.kuit.ourmenu.ui.onboarding.mapper

import com.kuit.ourmenu.data.model.cache.response.CacheInfoResponse
import com.kuit.ourmenu.ui.onboarding.state.CacheState

fun CacheInfoResponse.toState() = CacheState(
    homeImgs = homeImgs.map { it.homeImgUrl },
    menuFolderIcons = menuFolderIcons.map { it.menuFolderIconUrl },
    menuPins = menuPins.map { it.menuPinAddImgUrl },
    tags = tags.map { it.tag }
)
