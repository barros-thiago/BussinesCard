package com.example.bussinescard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bussinescard.App
import com.example.bussinescard.R
import com.example.bussinescard.data.BusinessCard
import com.example.bussinescard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()
    }

    private fun insertListener() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirmar.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.txtNome.editText?.text.toString() ,
                telefone = binding.txtTelefone.editText?.text.toString(),
                email = binding.txtEmail.editText?.text.toString(),
                empresa = binding.txtEmpresa.editText?.text.toString(),
                fundoPersonalizado = binding.txtCor.editText?.text.toString()
                )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_sucess,Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}