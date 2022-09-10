package com.example.bussinescard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bussinescard.data.BusinessCard
import com.example.bussinescard.databinding.ItemBusinessCardBinding


class BusinessCardAdapter : ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallBack()) {
    var listenerShare: (View)  -> Unit = {}
    var listenerDelete: (BusinessCard) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind (item: BusinessCard){
            binding.tvNome.text = item.nome
            binding.tvEmail.text = item.email
            binding.tvTelefone.text = item.telefone
            binding.tvInstagram.text = item.instagram
            binding.tvEndereco.text = item.endereco
            binding.tvNomeEmpresa.text = item.empresa
            if (item.fundoPersonalizado != "")
            { binding.cvContent.setBackgroundColor(Color.parseColor(item.fundoPersonalizado))
              binding.ibDelete.setBackgroundColor(Color.parseColor(item.fundoPersonalizado))}

            binding.cvContent.setOnClickListener {
                //Esconde o ibDelete, antes do print
                binding.ibDelete.visibility = View.GONE
                listenerShare(it)
                //Mostra o ibDelete, depois do print
                binding.ibDelete.visibility = View.VISIBLE
            }

            binding.ibDelete.setOnClickListener{
                listenerDelete(item)
            }
        }
    }
}


class DiffCallBack: DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem.id == newItem.id
}

