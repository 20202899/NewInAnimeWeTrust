package com.carlos.silva.inanimewetrust.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "anime")
data class AnimeEntity (
    @PrimaryKey(autoGenerate = true)
    val videoId: Int,
    val title: String,
    val timer: String,
    val imagePath: String,
    val link: String
)