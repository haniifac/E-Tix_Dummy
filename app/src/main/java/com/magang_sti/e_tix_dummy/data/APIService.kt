package com.magang_sti.e_tix_dummy.data

import com.magang_sti.e_tix_dummy.login.GetAllUserResponse
import com.magang_sti.e_tix_dummy.login.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("users")
    fun getALlUsers(): Call<List<UsersItem>>
}