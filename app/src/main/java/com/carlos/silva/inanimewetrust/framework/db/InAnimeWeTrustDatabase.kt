package com.carlos.silva.inanimewetrust.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnimeEntity::class], version = 1)
abstract class InAnimeWeTrustDatabase : RoomDatabase() {

    abstract fun getDao(): AnimeDao

    companion object {
        @Volatile
        var INSTANCE: InAnimeWeTrustDatabase? = null

        fun getInstance(context: Context): InAnimeWeTrustDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    InAnimeWeTrustDatabase::class.java,
                    "database-inanimewetrust"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}