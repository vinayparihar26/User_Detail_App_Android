package com.nextday.userdetailapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nextday.userdetailapp.R
import com.nextday.userdetailapp.api.RetrofitClient
import com.nextday.userdetailapp.adapter.UserAdapter
import com.nextday.userdetailapp.database.UserDatabase
import com.nextday.userdetailapp.viewmodel.UserViewModelFactory
import com.nextday.userdetailapp.databinding.ActivityMainBinding
import com.nextday.userdetailapp.repository.UserRepository
import com.nextday.userdetailapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private val apiService = RetrofitClient.apiService

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao =
            UserDatabase.getDatabase(application).userDao()

        binding.favListBtn.setOnClickListener {
            val intent = Intent(this, FavouriteActivity::class.java)
            Toast.makeText(this, "Clicked Favorites User List", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val userRepository = UserRepository(apiService, userDao)

        val viewModelFactory = UserViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        viewModel.fetchUsers()
        userAdapter =
            UserAdapter(userList = emptyList(), favoriteList = emptyList()) { user, isNowFavorite ->
                if (isNowFavorite) {
                    viewModel.addToFavorites(user)
                    Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.removeFromFavorites(user)
                    Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                }
            }
        recyclerView.adapter = userAdapter

        viewModel.userList.observe(this) { users ->
            userAdapter.updateUserList(users)
        }

        viewModel.favoriteList.observe(this) { favorites ->
            userAdapter.updateFavoriteList(favorites)
        }

    }
}