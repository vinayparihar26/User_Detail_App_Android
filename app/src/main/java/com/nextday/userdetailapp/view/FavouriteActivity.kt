package com.nextday.userdetailapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextday.userdetailapp.api.RetrofitClient
import com.nextday.userdetailapp.adapter.UserAdapter
import com.nextday.userdetailapp.database.UserDatabase
import com.nextday.userdetailapp.viewmodel.UserViewModelFactory
import com.nextday.userdetailapp.databinding.ActivityFavouriteBinding
import com.nextday.userdetailapp.repository.UserRepository
import com.nextday.userdetailapp.viewmodel.UserViewModel

class FavouriteActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var favAdapter: UserAdapter
    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.favoritesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiService = RetrofitClient.apiService
        val userDao = UserDatabase.getDatabase(application).userDao()
        val repository = UserRepository(apiService, userDao)
        val factory = UserViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        favAdapter =
            UserAdapter(userList = emptyList(), favoriteList = emptyList()) { user, isNowFavorite ->
                if (isNowFavorite) {
                    viewModel.addToFavorites(user)
                    Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.removeFromFavorites(user)
                    Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                }
            }

        recyclerView.adapter = favAdapter

        viewModel.favoriteList.observe(this) { favorites ->
            favAdapter.updateUserList(favorites)
            favAdapter.updateFavoriteList(favorites)
        }
    }
}