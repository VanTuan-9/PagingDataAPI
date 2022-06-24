package com.example.ui.user.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class UserStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<UserStateViewHolder>() {
    override fun onBindViewHolder(holder: UserStateViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): UserStateViewHolder {
        return UserStateViewHolder.create(parent, retry)
    }
}