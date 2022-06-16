package com.deva.bangkit_capstoneproject.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deva.bangkit_capstoneproject.core.data.local.entity.MessageEntity

@Dao
interface ChatDao {

    @Query("SELECT * FROM chats")
    fun getAllChat(): LiveData<List<MessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(message: MessageEntity)

    @Query("DELETE FROM chats")
    fun resetChat()
}