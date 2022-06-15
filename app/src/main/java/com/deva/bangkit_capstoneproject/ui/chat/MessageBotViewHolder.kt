package com.deva.bangkit_capstoneproject.ui.chat

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.databinding.CardMessageBotBinding

class MessageBotViewHolder(private val binding: CardMessageBotBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message: MessageModel) {
        with(message) {
            val text = this.message +
                    if (payload != null) {
                        ":\n" + payload.mapIndexed { index, item ->
                            "${index + 1} $item"
                        }.joinToString("\n")
                    } else ""

            binding.message.text = text
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(id.toLong())
        }
    }
}