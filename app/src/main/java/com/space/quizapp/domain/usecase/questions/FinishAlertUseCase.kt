package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class FinishAlertUseCase : QuizBaseUseCase<FinishAlertRequest, FinishAlertResponse?>() {
    override suspend fun invoke(params: FinishAlertRequest?): FinishAlertResponse? {
        return params!!.finalScore?.let { finalScore ->
            params.maxScore?.let { maxScore ->
                when (finalScore.toFloat() / maxScore) {
                    1f -> FinishAlertResponse("\uD83E\uDD29", true)
                    in 0f..0.2f -> FinishAlertResponse("\uD83E\uDD72", false)
                    in 0.2f..0.4f -> FinishAlertResponse("\uD83E\uDD78", false)
                    in 0.4f..0.6f -> FinishAlertResponse("\uD83E\uDDD0", false)
                    else -> FinishAlertResponse("\uD83E\uDD79", true)
                }
            }
        }
    }
}

data class FinishAlertRequest(val finalScore: Int?, val maxScore: Int?)
data class FinishAlertResponse(val emoji: String, val isCongratsVisible: Boolean)