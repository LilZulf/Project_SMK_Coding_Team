package com.github.smkcoding.mubarak.repo

import androidx.lifecycle.LiveData
import com.github.smkcoding.mubarak.dao.BookmarkDao
import com.github.smkcoding.mubarak.model.TbSurahModel

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {
    // Room executes all queries on a separate thread.
// Observed LiveData will notify the observer when the data has changed.
    val allMyFriend: LiveData<List<TbSurahModel>> =
        bookmarkDao.getAllMyFriend()
    suspend fun insert(myFriend: TbSurahModel) {
        bookmarkDao.insert(myFriend)
    }
    suspend fun insertAll(myFriends: List<TbSurahModel>) {
        bookmarkDao.insertAll(myFriends)
    }
    suspend fun deleteAll() {
        bookmarkDao.deleteAll()
    }
    suspend fun update(myFriend: TbSurahModel) {
        bookmarkDao.update(myFriend)
    }
    suspend fun delete(myFriend: TbSurahModel) {
        bookmarkDao.delete(myFriend)
    }
}
