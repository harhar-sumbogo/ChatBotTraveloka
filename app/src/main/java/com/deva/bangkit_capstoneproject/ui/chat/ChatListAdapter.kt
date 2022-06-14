package com.deva.bangkit_capstoneproject.ui.chat

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.R
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.databinding.CardMessageBotBinding
import com.deva.bangkit_capstoneproject.databinding.CardMessageUserBinding

class ChatListAdapter(
    private val currentUser: String?
): ListAdapter<MessageModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    private val chatList = ArrayList<MessageModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addChat(messageModel: MessageModel) {
        chatList.add(messageModel)
        submitList(chatList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        val sender = message.user

        return if (sender == currentUser) {
            USER
        } else {
            BOT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == USER) {
            val view = inflater.inflate(R.layout.card_message_user, parent, false)
            MessageUserViewHolder(CardMessageUserBinding.bind(view))
        } else {
            val view = inflater.inflate(R.layout.card_message_bot, parent, false)
            MessageBotViewHolder(CardMessageBotBinding.bind(view))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)

        if (holder is MessageUserViewHolder) {
            holder.bind(model)
        } else if (holder is MessageBotViewHolder) {
            holder.bind(model)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MessageModel>() {
            override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
                Log.d("DIFF_CALLBACK", "areItemsTheSame: ${oldItem == newItem}")
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
                Log.d("DIFF_CALLBACK", "areItemsTheSame: ${oldItem.id == newItem.id}")
                return oldItem.id == newItem.id
            }
        }

        private const val USER = 100
        private const val BOT = 200
    }
}