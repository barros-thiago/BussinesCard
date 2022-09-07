package com.example.bussinescard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bussinescard.App
import com.example.bussinescard.data.BusinessCard
import com.example.bussinescard.databinding.ActivityMainBinding
import com.example.bussinescard.util.Image


class MainActivity : AppCompatActivity() {
    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinesCard()
        insertListener()
    }

    private fun insertListener(){
        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity,AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = {card ->
            Image.share(this@MainActivity, card)
        }

        adapter.listenerDelete = {
            mainViewModel.deleteItem(it)
        }
    }

    private fun getAllBusinesCard(){
        mainViewModel.getAll().observe(this) {businessCard ->
            adapter.submitList(businessCard)
        }
    }
}