package com.carlos.silva.inanimewetrust.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface AnimeDao {
    @Insert
    fun saveAnime(animeEntity: AnimeEntity)

    @Delete
    fun deleteAnime(animeEntity: AnimeEntity)
}