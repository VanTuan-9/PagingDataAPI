package com.example.ui.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.views.databinding.UserStateItemBinding

class UserStateViewHolder(
    private val binding: UserStateItemBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(loadState: LoadState) {
        binding.retryBtn.isVisible = loadState is LoadState.Error
        binding.loading.isVisible = loadState is LoadState.Loading
        binding.retryBtn.setOnClickListener {
            retry.invoke()
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): UserStateViewHolder {
            val binding =
                UserStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserStateViewHolder(binding, retry)
        }
    }
}