package com.deva.bangkit_capstoneproject.ui.chat

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.databinding.CardMessageBotBinding
import com.deva.bangkit_capstoneproject.core.data.remote.response.Message

class MessageBotViewHolder(private val binding: CardMessageBotBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message : Message) {
        binding.message.text = message.text
        if (message.timestamp != null)  {
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(message.timestamp)
        }
    }
}