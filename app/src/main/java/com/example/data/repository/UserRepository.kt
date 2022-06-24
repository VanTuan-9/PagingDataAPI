package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.UserService
import com.example.data.models.User
import com.example.data.api.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class UserRepository {

    private val apiInterface: UserService by lazy {
        RetrofitInstance.apiInterface()
    }


    @ExperimentalPagingApi
    suspend fun loadDatas(): Flow<PagingData<User>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                UserPagingSource(apiInterface)
            }).flow
    }

    companion object{
        val instance by lazy {
            UserRepository()
        }
    }
}