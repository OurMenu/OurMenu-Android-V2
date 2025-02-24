package com.kuit.ourmenu.data.model.dummy.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DummyRequest(
    @SerialName("dummyString") val dummyString: String
)
