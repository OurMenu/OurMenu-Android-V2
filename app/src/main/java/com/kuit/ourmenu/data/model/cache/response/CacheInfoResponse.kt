package com.kuit.ourmenu.data.model.cache.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CacheInfoResponse(
    @SerialName("homeImgs")
    val homeImgs: List<HomeImg>,
    @SerialName("menuFolderIcons")
    val menuFolderIcons: List<MenuFolderIcon>,
    @SerialName("menuPins")
    val menuPins: List<MenuPin>,
    @SerialName("tags")
    val tags: List<Tag>
) {
    @Serializable
    data class HomeImg(
        @SerialName("homeImg")
        val homeImg: String,
        @SerialName("homeImgUrl")
        val homeImgUrl: String
    )

    @Serializable
    data class MenuFolderIcon(
        @SerialName("menuFolderIcon")
        val menuFolderIcon: String,
        @SerialName("menuFolderIconUrl")
        val menuFolderIconUrl: String
    )

    @Serializable
    data class MenuPin(
        @SerialName("menuPin")
        val menuPin: String,
        @SerialName("menuPinAddDisableImgUrl")
        val menuPinAddDisableImgUrl: String?,
        @SerialName("menuPinAddImgUrl")
        val menuPinAddImgUrl: String,
        @SerialName("menuPinMapImgUrl")
        val menuPinMapImgUrl: String?
    )

    @Serializable
    data class Tag(
        @SerialName("orangeTagImgUrl")
        val orangeTagImgUrl: String,
        @SerialName("tag")
        val tag: String,
        @SerialName("whiteTagImgUrl")
        val whiteTagImgUrl: String
    )
}