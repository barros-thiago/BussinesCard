package com.example.bussinescard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao  {

    @Query("SELECT * from BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bussinesCard: BusinessCard)

    @Query("DELETE FROM BusinessCard WHERE ID = :id")
    suspend fun deleteBusinessCard(id: Int?)
}