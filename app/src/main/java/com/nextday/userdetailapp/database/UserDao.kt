package com.nextday.userdetailapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nextday.userdetailapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getAllFavorites(): LiveData<List<User>>

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE id = :id)")
    suspend fun isUserFavorite(id: Int): Boolean
}
