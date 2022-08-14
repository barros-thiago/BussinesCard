package com.example.bussinescard

import android.app.Application
import com.example.bussinescard.data.AppDatabase
import com.example.bussinescard.data.BusinessCardReporsitory

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardReporsitory(database.businessDao()) }
}