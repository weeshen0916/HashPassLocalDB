package com.example.roomapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password_table")
data class Password(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val password: String,
    val hashpass: String,
    val hashtype: String
)