package com.example.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ActivityAddBusinessCardBinding
import com.example.businesscard.databinding.ActivityMainBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBusinessCardBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddBusinessCardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener { goToHome() }
        binding.btnConfirm.setOnClickListener { saveBusinessCard() }
    }

    private fun goToHome() {
        finish()
    }

    private fun saveBusinessCard() {
        val businessCard = BusinessCard(
            name = binding.tilName.editText?.text.toString(),
            company = binding.tilCompany.editText?.text.toString(),
            phone = binding.tilPhone.editText?.text.toString(),
            email = binding.tilMail.editText?.text.toString(),
            backgroundCustom = binding.tilColor.editText?.text.toString()
        )
        mainViewModel.insert(businessCard)
        Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_LONG).show()
        finish()
    }
}