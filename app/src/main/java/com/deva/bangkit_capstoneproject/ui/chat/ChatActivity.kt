package com.deva.bangkit_capstoneproject.ui.chat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deva.bangkit_capstoneproject.R
import com.deva.bangkit_capstoneproject.core.data.Result
import com.deva.bangkit_capstoneproject.core.di.Injection
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.databinding.ActivityChatBinding
import com.deva.bangkit_capstoneproject.ui.LoginActivity
import com.deva.bangkit_capstoneproject.ui.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter: ChatListAdapter
    private var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatRepository = Injection.provideRepository(this)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(chatRepository))[ChatViewModel::class.java]

        supportActionBar?.apply {
            title = "  Traveloka Bot"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.traveloka_chatbot_logo_30)
        }

        hideSendButton(true)

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.messageEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isEmpty()) {
                    hideSendButton(true)
                } else {
                    hideSendButton(false)
                }
            }
        })

        binding.sendButton.setOnClickListener {
            val message = binding.messageEditText.text.toString()
            viewModel.sendMessage(
                MessageModel(
                    user = firebaseUser?.displayName.toString(),
                    message = message,
                    tag = tag
                ).also {
                    adapter.addChat(it)
                }
            ).observe(this) {
                when (it) {
                    is Result.Success -> {
                        with(it) {
                            adapter.addChat(data)
                            tag = data.tag
                        }
                    }
                    is Result.Error -> {

                    }
                    is Result.Loading -> {

                    }
                }
            }
            binding.messageEditText.setText("")
        }

        val manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        binding.rvChat.layoutManager = manager

        adapter = ChatListAdapter(firebaseUser?.displayName)

        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                (binding.rvChat.layoutManager as LinearLayoutManager).scrollToPosition(adapter
                    .itemCount - 1)
            }
        })

        binding.rvChat.adapter = adapter
    }

    private fun hideSendButton(isTextEmpty: Boolean) {
        if (isTextEmpty) {
            binding.sendButton.visibility = View.GONE
        } else {
            binding.sendButton.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}