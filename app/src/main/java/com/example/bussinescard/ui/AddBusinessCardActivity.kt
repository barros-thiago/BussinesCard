package com.example.bussinescard.ui

import android.graphics.Color
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

        binding.csb.setOnColorChangeListener { progress, color ->
            val sharp = "#"
            binding.txtCor.editText?.setText(sharp.plus(Integer.toHexString(color)))
        }

        binding.btnConfirmar.setOnClickListener {
            try {
                if (binding.txtCor.editText?.text?.isBlank() == false){
                    val color = Color.parseColor(binding.txtCor.editText?.text.toString().uppercase())

                    val businessCard = BusinessCard(
                        nome = binding.txtNome.editText?.text.toString() ,
                        telefone = binding.txtTelefone.editText?.text.toString(),
                        instagram = binding.txtInstagram.editText?.text.toString(),
                        email = binding.txtEmail.editText?.text.toString(),
                        empresa = binding.txtEmpresa.editText?.text.toString(),
                        endereco = binding.txtEndereco.editText?.text.toString(),
                        fundoPersonalizado = binding.txtCor.editText?.text.toString().uppercase()
                    )
                    mainViewModel.insert(businessCard)
                    Toast.makeText(this, R.string.label_show_sucess,Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, R.string.label_show_unknow_color,Toast.LENGTH_SHORT).show()
                }

            } catch (iae: IllegalArgumentException) {
                Toast.makeText(this, R.string.label_show_unknow_color,Toast.LENGTH_SHORT).show()
            }
        }
    }

}