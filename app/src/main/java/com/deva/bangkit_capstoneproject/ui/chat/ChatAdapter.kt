package com.deva.bangkit_capstoneproject.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.R
import com.deva.bangkit_capstoneproject.databinding.CardMessageBotBinding
import com.deva.bangkit_capstoneproject.databinding.CardMessageUserBinding
import com.deva.bangkit_capstoneproject.response.Message
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ChatAdapter(
    options: FirebaseRecyclerOptions<Message>,
    private val currentUser: String?
) : FirebaseRecyclerAdapter<Message, RecyclerView.ViewHolder>(options) {

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        val sender = message.name

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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, model: Message) {
        if (holder is MessageUserViewHolder) {
            holder.bind(model)
        } else if (holder is MessageBotViewHolder) {
            holder.bind(model)
        }
    }

    companion object {
        private const val USER = 100
        private const val BOT = 200
    }
}