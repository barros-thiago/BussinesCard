package com.example.bussinescard.data


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardReporsitory(private val dao: BusinessCardDao) {

    fun insert(businessCard: BusinessCard)= runBlocking{
        launch(Dispatchers.IO){
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()
}