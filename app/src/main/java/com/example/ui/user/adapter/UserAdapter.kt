package com.example.ui.user.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.data.models.User

class UserAdapter(private val userListener: UserListener,
                private val clickItem : (data: User)-> Unit
                  ) :
    PagingDataAdapter<User, UserViewHolder>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.gender == newItem.gender
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent, userListener, clickItem)
    }
}