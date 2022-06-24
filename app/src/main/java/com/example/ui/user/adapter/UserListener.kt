package com.example.ui.user.adapter

import com.example.data.models.User

interface UserListener {
    fun onClick(model: User)
}