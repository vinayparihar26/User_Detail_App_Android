package com.nextday.userdetailapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextday.userdetailapp.repository.UserRepository
import com.nextday.userdetailapp.model.User
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    val favoriteList: LiveData<List<User>> = repository.getAllFavorites()

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userResponse = repository.getUsersFromApi()

                if (userResponse != null) {
                    // Handle successful response
                    _userList.postValue(userResponse.body()!!.data)
                } else {
                    // Handle failure response (status code is not 200)
                    println("Error: API request failed or returned no data")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addToFavorites(user: User) {
        viewModelScope.launch {
            repository.addUserToFavorites(user)
        }
    }

    fun removeFromFavorites(user: User) {
        viewModelScope.launch {
            repository.removeUserFromFavorites(user)
        }
    }
}

