package com.deva.bangkit_capstoneproject.ui.chat

import android.text.format.DateUtils
import android.text.method.LinkMovementMethod
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.databinding.CardMessageBotBinding

class MessageBotViewHolder(private val binding: CardMessageBotBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message: MessageModel) {
        with(message) {
            binding.message.text = this.message
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(id.toLong())
        }
    }
}