package com.deva.bangkit_capstoneproject.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.deva.bangkit_capstoneproject.ui.LoginActivity
import com.deva.bangkit_capstoneproject.R
import com.deva.bangkit_capstoneproject.core.data.Result
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
            viewModel.createGroup().observe(this) {
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
                it.token?.let { token -> viewModel.createUser(token) }
            }
        }
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}