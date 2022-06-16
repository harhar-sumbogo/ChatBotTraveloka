package com.traveloka.chatbot.ui.chat

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.traveloka.chatbot.core.domain.model.MessageModel
import com.traveloka.chatbot.databinding.CardMessageBotBinding

class MessageBotViewHolder(private val binding: CardMessageBotBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message: MessageModel) {
        with(message) {
            binding.message.text = this.message
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(id.toLong())
        }
    }
}