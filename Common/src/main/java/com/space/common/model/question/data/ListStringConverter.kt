package com.space.common.model.question.data

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        return value?.split(";") ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(";")
    }
}
