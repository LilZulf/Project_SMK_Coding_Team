package com.github.smkcoding.mubarak.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.smkcoding.mubarak.dao.BookmarkDao
import com.github.smkcoding.mubarak.model.TbSurahModel

@Database(entities = arrayOf(TbSurahModel::class), version = 1,
    exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun myFriendDao(): BookmarkDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
// same time.
        @Volatile
        private var INSTANCE: BookmarkDatabase? = null
        fun getDatabase(context: Context): BookmarkDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    "db_bookmark"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}