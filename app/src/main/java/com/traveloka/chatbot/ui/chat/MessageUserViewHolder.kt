package com.traveloka.chatbot.ui.chat

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.traveloka.chatbot.databinding.CardMessageUserBinding
import com.traveloka.chatbot.core.domain.model.MessageModel

class MessageUserViewHolder(private val binding: CardMessageUserBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message: MessageModel) {
        with(message) {
            binding.message.text = this.message
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(this.id.toLong())
        }
    }
}