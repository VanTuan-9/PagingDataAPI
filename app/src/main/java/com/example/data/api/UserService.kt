package com.example.data.api

import com.example.data.models.UserPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    // APi đang load suspend . Mình đang hy vọng nó trả về cho mình 1 ModelApi , khi code = 200
    @GET("character")
    suspend fun getPage(@Query("page") page: Int): UserPageResponse
}