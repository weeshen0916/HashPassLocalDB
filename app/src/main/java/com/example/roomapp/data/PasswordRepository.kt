package com.example.roomapp.data

import androidx.lifecycle.LiveData

class PasswordRepository(private val hashDao: HashDao) {

    val readAllData: LiveData<List<Password>> = hashDao.readAllData()

    suspend fun addPassword(password: Password){
        hashDao.addPassword(password)
    }

}