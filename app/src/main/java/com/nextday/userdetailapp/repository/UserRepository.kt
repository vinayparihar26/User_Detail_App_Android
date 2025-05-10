package com.nextday.userdetailapp.repository

import com.nextday.userdetailapp.api.ApiService
import com.nextday.userdetailapp.database.UserDao
import com.nextday.userdetailapp.model.User
import com.nextday.userdetailapp.model.UserResponse
import retrofit2.Response


class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    suspend fun addUserToFavorites(user: User) {
        userDao.insert(user)
    }

    suspend fun removeUserFromFavorites(user: User) {
        userDao.delete(user)
    }

    fun getAllFavorites() = userDao.getAllFavorites()

    suspend fun getUsersFromApi(): Response<UserResponse> {
        return apiService.getUsers()
    }
}
