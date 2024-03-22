package com.primo.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.primo.database.converter.FeedConverter
import com.primo.database.dao.Feed
import com.primo.database.dao.FeedDao
import com.primo.database.dao.Profile

@Database(entities = [Profile::class, Feed::class], version = 1, exportSchema = false)
@TypeConverters(FeedConverter::class)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}
