package com.nextday.userdetailapp.api

import com.nextday.userdetailapp.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    suspend fun getUsers(): Response<UserResponse>
}
