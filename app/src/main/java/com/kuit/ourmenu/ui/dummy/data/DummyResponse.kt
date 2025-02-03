package com.kuit.ourmenu.ui.dummy.data

data class DummyResponse(
    val name: String = "",
    val description: String = "",
    val imageUrl: String = ""
)

fun DummyResponse.toDummyData() = DummyData(
    name = name,
    description = description,
    imageUrl = imageUrl
)