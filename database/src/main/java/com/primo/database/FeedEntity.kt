package com.primo.database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import com.primo.model.FeedResponse

@Dao
interface FeedDao {
    @Query("SELECT * FROM feed")
    suspend fun getAll(): List<Feed>

    @Insert
    suspend fun insertAll(vararg users: Feed)
}

@Entity
data class Feed(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "feeds") val feed: List<FeedResponse>?,
)