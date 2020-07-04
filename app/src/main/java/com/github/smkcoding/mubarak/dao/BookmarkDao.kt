package com.github.smkcoding.mubarak.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.smkcoding.mubarak.model.TbSurahModel

@Dao
interface BookmarkDao {
    @Query("SELECT * from tb_bookmark")
    fun getAllMyFriend(): LiveData<List<TbSurahModel>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFriend: TbSurahModel)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(myFriends: List<TbSurahModel>)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myFriend: TbSurahModel)
    @Delete()
    suspend fun delete(myFriend: TbSurahModel)
    @Query("DELETE FROM tb_bookmark")
    suspend fun deleteAll()

}