package com.primo.feed

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import appModule
import com.primo.data.converter.FeedConverter
import com.primo.database.Feed
import com.primo.database.FeedDao
import org.koin.core.context.startKoin

@Database(entities = [Feed::class], version = 1, exportSchema = false)
@TypeConverters(FeedConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }


        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "vee-primo"
        ).build()
    }
}