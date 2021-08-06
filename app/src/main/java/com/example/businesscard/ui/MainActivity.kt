package com.example.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityMainBinding
import com.example.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { BusinessCardAdapter() }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.rvCards.adapter = adapter
        getAllBusinessCard()

        binding.btnAdd.setOnClickListener { newCard() }

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun newCard() {
        val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
        startActivity(intent)
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, { businessCardList ->
            adapter.submitList(businessCardList)
        })
    }
}