package com.deva.bangkit_capstoneproject.ui.chat

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.databinding.CardMessageUserBinding
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel

class MessageUserViewHolder(private val binding: CardMessageUserBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message: MessageModel) {
        with(message) {
            binding.message.text = this.message
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(this.id.toLong())
        }
    }
}