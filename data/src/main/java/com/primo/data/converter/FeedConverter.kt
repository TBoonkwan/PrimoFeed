package com.primo.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.primo.core.fromJson
import com.primo.model.FeedResponse

class FeedConverter {
    @TypeConverter
    fun fromStringArrayList(value: List<FeedResponse>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<FeedResponse> {
        return try {
            Gson().fromJson<List<FeedResponse>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}