package com.kuit.ourmenu.data.model.home.response

import kotlinx.serialization.Serializable

@Serializable
data class HomeQuestionResponse(
    val question: String,
    val answers: List<Answer>
)

@Serializable
data class Answer(
    val answer: String,
    val answerImgUrl: String
)