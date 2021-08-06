package com.example.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.data.BusinessCardRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(private val repository: BusinessCardRepository): ViewModel() {

    fun insert(businessCard: BusinessCard) = viewModelScope.launch {
        repository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return repository.getAll()
    }
}

class MainViewModelFactory(private val repository: BusinessCardRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }

}