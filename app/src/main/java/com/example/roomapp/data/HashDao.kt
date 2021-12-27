package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HashDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPassword(password: Password)

    @Query("SELECT * FROM password_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Password>>

}