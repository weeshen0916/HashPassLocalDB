package com.example.roomapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Password>>
    private val repository: PasswordRepository

    init {
        val hashDao = PasswordDatabase.getDatabase(application).hashDao()
        repository = PasswordRepository(hashDao)
        readAllData = repository.readAllData
    }

    fun addPassword(user: Password){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPassword(user)
        }
    }

}