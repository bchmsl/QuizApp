package com.space.quizapp.domain.usecase.user.read_user_token

class QuizReadUserTokenUseCaseImpl : QuizReadUserTokenUseCase() {
    override suspend fun invoke(params: Unit?): String {
        return repository.getUserToken()
    }
}
