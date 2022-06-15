package com.deva.bangkit_capstoneproject.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class MessageEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "chatId")
    var chatId: String,

    @ColumnInfo(name = "message")
    var message: String,

    @ColumnInfo(name = "user")
    var user: String,

    @ColumnInfo(name = "tag")
    var tag: String?
)