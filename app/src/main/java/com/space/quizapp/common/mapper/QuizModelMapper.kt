package com.space.quizapp.common.mapper

interface QuizDomainMapper<MODEL, DOMAIN> {
    fun toDomain(model: MODEL): DOMAIN
}

interface QuizEntityMapper<ENTITY, DOMAIN> : QuizDomainMapper<ENTITY, DOMAIN> {
    override fun toDomain(model: ENTITY): DOMAIN
    fun toEntity(model: DOMAIN): ENTITY
}

interface QuizDtoMapper<DTO, DOMAIN> : QuizDomainMapper<DTO, DOMAIN> {
    override fun toDomain(model: DTO): DOMAIN
}

interface QuizUiMapper<UI, DOMAIN> : QuizDomainMapper<UI, DOMAIN> {
    override fun toDomain(model: UI): DOMAIN
    fun toUi(model: DOMAIN): UI

}
