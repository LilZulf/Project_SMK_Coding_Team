package com.github.smkcoding.mubarak.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.smkcoding.mubarak.dao.BookmarkDao
import com.github.smkcoding.mubarak.db.BookmarkDatabase
import com.github.smkcoding.mubarak.model.TbSurahModel
import com.github.smkcoding.mubarak.repo.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel : ViewModel() {
    lateinit var repository: BookmarkRepository
    lateinit var allMyFriends: LiveData<List<TbSurahModel>>
    fun init(context: Context) {
        val myFriendDao = BookmarkDatabase.getDatabase(context).myFriendDao()
        repository = BookmarkRepository(myFriendDao)
        allMyFriends = repository.allMyFriend
    }
    fun delete(myFriend: TbSurahModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myFriend)
    }

    fun deleteAll() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(myFriends: List<TbSurahModel>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            repository.insertAll(myFriends)
        }
    fun addData(myFriend: TbSurahModel) =
        viewModelScope . launch (Dispatchers. IO ) {
            repository .insert(myFriend)
        }

}
