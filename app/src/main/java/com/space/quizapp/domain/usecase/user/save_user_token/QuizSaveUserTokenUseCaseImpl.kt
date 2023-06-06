package com.space.quizapp.domain.usecase.user.save_user_token

class QuizSaveUserTokenUseCaseImpl : QuizSaveUserTokenUseCase() {
    override suspend fun invoke(token: String?) {
        repository.saveUserToken(token!!)
    }
}
