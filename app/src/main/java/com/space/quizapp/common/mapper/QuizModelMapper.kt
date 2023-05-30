package com.space.quizapp.common.mapper

interface QuizModelMapper<in MODEL_A, out MODEL_B> {
    operator fun invoke(model: MODEL_A): MODEL_B
}
