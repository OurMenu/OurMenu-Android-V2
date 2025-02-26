package com.kuit.ourmenu.data.model.dummy.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DummyResponse(
    @SerialName("dummyString") val dummyString: String
)
