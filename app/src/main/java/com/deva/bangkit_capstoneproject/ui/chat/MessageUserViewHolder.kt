package com.deva.bangkit_capstoneproject.ui.chat

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.databinding.CardMessageUserBinding
import com.deva.bangkit_capstoneproject.core.data.remote.response.Message

class MessageUserViewHolder(private val binding: CardMessageUserBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(message : Message) {
        binding.message.text = message.text
        if (message.timestamp != null)  {
            binding.timestamp.text = DateUtils.getRelativeTimeSpanString(message.timestamp)
        }
    }
}