package com.traveloka.chatbot.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.traveloka.chatbot.ui.LoginActivity
import com.traveloka.chatbot.R
import com.traveloka.chatbot.core.data.Result
import com.traveloka.chatbot.ui.chat.ChatActivity
import com.traveloka.chatbot.databinding.ActivityMainBinding
import com.traveloka.chatbot.core.ui.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.logo_traveloka)
        }


        binding.btnSignOut.setOnClickListener {
            signOut()
            viewModel.deleteCacheChat()
        }

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }else {
            firebaseUser.getIdToken(true).addOnSuccessListener {
                Log.d("BEARER_TOKEN", "onCreate: ${it.token}")
                it.token?.let { bearerToken ->
                    viewModel.createUser(bearerToken)
                    token = bearerToken
                }
            }
        }

        binding.btnChat.setOnClickListener {
            viewModel.createGroup(token).observe(this) {
                when(it) {
                    is Result.Success -> {
                        val i = Intent(this, ChatActivity::class.java)
                        startActivity(i)
                    }
                    is Result.Loading -> {

                    }
                    is Result.Error -> {
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}