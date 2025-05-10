package com.nextday.userdetailapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class UserEntity(
    @PrimaryKey val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)