package com.aber.ac.uk.cs31620.assignment.datasource

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListToJsonConverter {
    private inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun serializeIntMutableList(value: MutableList<Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun deserializeIntMutableList(value: String): MutableList<Int> {
        return try {
            Gson().fromJson<MutableList<Int>>(value)
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}
