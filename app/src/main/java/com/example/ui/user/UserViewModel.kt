package com.example.ui.user

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.data.models.User
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserViewModel : ViewModel() {
    private var apiInterface: UserRepository = UserRepository.instance

    @ExperimentalPagingApi
    suspend fun loadData(): Flow<PagingData<User>>{
        return apiInterface.loadDatas()
    }
}