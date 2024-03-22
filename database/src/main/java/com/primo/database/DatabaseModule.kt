package com.primo.database

import android.app.Application
import androidx.room.Room
import com.primo.database.dao.FeedDao
import com.primo.database.database.FeedDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun provideFeedDatabase(application: Application): FeedDatabase = Room.databaseBuilder(
    application, FeedDatabase::class.java, "vee-primo"
).build()

fun provideFeedDao(feedDatabase: FeedDatabase): FeedDao = feedDatabase.feedDao()

val databaseModule = module {
    single { provideFeedDatabase(application = androidApplication()) }
    single { provideFeedDao(feedDatabase = get()) }
}