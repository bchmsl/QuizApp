package com.space.common.mapper

interface QuizDomainMapper<MODEL, DOMAIN> {
    fun toDomain(model: MODEL): DOMAIN
    fun toDomainList(list: List<MODEL>): List<DOMAIN> {
        return list.map { toDomain(it) }
    }
}

interface QuizEntityMapper<ENTITY, DOMAIN> : QuizDomainMapper<ENTITY, DOMAIN> {
    override fun toDomain(model: ENTITY): DOMAIN
    fun toEntity(model: DOMAIN): ENTITY
    fun toEntityList(list: List<DOMAIN>): List<ENTITY> {
        return list.map { toEntity(it) }
    }
}

interface QuizDtoMapper<DTO, DOMAIN> : QuizDomainMapper<DTO, DOMAIN> {
    override fun toDomain(model: DTO): DOMAIN
    override fun toDomainList(list: List<DTO>): List<DOMAIN> {
        return super.toDomainList(list)
    }
}

interface QuizUiMapper<UI, DOMAIN> : QuizDomainMapper<UI, DOMAIN> {
    override fun toDomain(model: UI): DOMAIN
    fun toUi(model: DOMAIN): UI
    fun toUiList(list: List<DOMAIN>): List<UI> {
        return list.map { toUi(it) }
    }

}
