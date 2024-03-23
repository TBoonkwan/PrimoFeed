package com.primo.database.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query


@Dao

interface FeedDao {

    @Query("SELECT EXISTS(SELECT * FROM feed)")
    fun hasFeed(): Boolean
    @Query("SELECT * FROM feed")
    suspend fun getFeed(): List<Feed>
    @Query("SELECT * FROM feed WHERE id IN (:feedId)")
    suspend fun getFeedById(feedId: Int): Feed

    @Insert
    suspend fun insertFeed(feed: Feed)
    @Query("SELECT * FROM profile")
    suspend fun getProfile(): List<Profile>
    @Query("SELECT * FROM profile WHERE id IN (:profileId)")
    suspend fun getProfileById(profileId : Int): Profile

    @Insert
    suspend fun insertProfile(profile: Profile)
}

@Entity
data class Feed(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "profile_id") val profileId: Int? = null,
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "pubDate") var pubDate: String?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "categories") var categories: List<String> = listOf(),
    @ColumnInfo(name = "link") var link: String?,
    @ColumnInfo(name = "author") var author: String?
)


@Entity
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "image") val image: String?,
)