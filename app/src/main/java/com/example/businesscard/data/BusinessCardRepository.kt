package com.example.businesscard.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao): BusinessCardDao {

    override fun getAll(): LiveData<List<BusinessCard>> {
        return dao.getAll()
    }

    override suspend fun insert(businessCard: BusinessCard): Unit = runBlocking{
        launch {
            dao.insert(businessCard)
        }
    }

}