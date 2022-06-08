package com.deva.bangkit_capstoneproject.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.deva.bangkit_capstoneproject.LoginActivity
import com.deva.bangkit_capstoneproject.R
import com.deva.bangkit_capstoneproject.core.di.Injection
import com.deva.bangkit_capstoneproject.ui.chat.ChatActivity
import com.deva.bangkit_capstoneproject.databinding.ActivityMainBinding
import com.deva.bangkit_capstoneproject.ui.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatRepository = Injection.provideRepository(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(chatRepository))[MainViewModel::class.java]

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.logo_traveloka)
        }

        binding.btnChat.setOnClickListener {
            val i = Intent(this, ChatActivity::class.java)
            startActivity(i)
        }

        binding.btnSignOut.setOnClickListener {
            signOut()
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
                it.token?.let { token -> viewModel.saveToken(token) }
            }
        }
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}