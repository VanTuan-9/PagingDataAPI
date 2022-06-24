package com.example.ui.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.models.User
import com.example.views.databinding.UserItemBinding

class UserViewHolder(
    private val binding: UserItemBinding,
    private val userListener: UserListener,

    private val clickItem: (data: User) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(results: User) {
        updateLayout(results)
        initAction(results)
    }

    private fun updateLayout(results: User) {
        binding.name.text = results.name
        binding.gender.text = results.gender
        Glide.with(itemView.context).load(results.image).into(binding.image)
    }

    private fun initAction(results: User) {
        binding.root.setOnClickListener {
            userListener.onClick(results)
        }
        binding.root.setOnLongClickListener {
            clickItem.invoke(results)
            true
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            userListener: UserListener,
            clickItem: (data: User) -> Unit
        ): UserViewHolder {
            val binding =
                UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserViewHolder(binding, userListener, clickItem)
        }
    }
}