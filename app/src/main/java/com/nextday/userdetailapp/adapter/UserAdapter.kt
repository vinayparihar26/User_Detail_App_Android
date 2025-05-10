package com.nextday.userdetailapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nextday.userdetailapp.R
import com.nextday.userdetailapp.databinding.ItemUserBinding
import com.nextday.userdetailapp.model.User

class UserAdapter(
    private var userList: List<User>,
    private var favoriteList: List<User>,
    private val onFavoriteClick: (User, Boolean) -> Unit,
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        val isFavorite = favoriteList.any { it.id == user.id }

        with(holder.binding) {
            tvName.text = user.first_name
            tvLName.text=user.last_name
            tvEmail.text = user.email

            Glide.with(ivAvatar.context).load(user.avatar).into(ivAvatar)

            ivFavorite.setImageResource(
                if (isFavorite) R.drawable.ic_heart_filled else R.drawable.heartoutline
            )

            ivFavorite.setOnClickListener {
                onFavoriteClick(user, !isFavorite)
            }
        }
    }

    override fun getItemCount(): Int = userList.size

    fun updateUserList(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()
    }

    fun updateFavoriteList(newFavorites: List<User>) {
        favoriteList = newFavorites
        notifyDataSetChanged()
    }
}