package com.deva.bangkit_capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.deva.bangkit_capstoneproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.logo_traveloka)
        }

        binding.btnChat.setOnClickListener {
            val i = Intent(this,ChatActivity::class.java)
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_login -> {
                val i = Intent(this,LoginActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.menu_register -> {
                val i = Intent(this,RegisterActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}