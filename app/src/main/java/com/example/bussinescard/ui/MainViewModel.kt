package com.example.bussinescard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bussinescard.data.BusinessCard
import com.example.bussinescard.data.BusinessCardReporsitory
import java.lang.IllegalArgumentException

class MainViewModel(private val businessCardReporsitory: BusinessCardReporsitory): ViewModel() {

    fun insert(businessCard: BusinessCard){
        businessCardReporsitory.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>>{
        return businessCardReporsitory.getAll()
    }

class MainViewModelFactory(private val repository: BusinessCardReporsitory) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }else{
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}