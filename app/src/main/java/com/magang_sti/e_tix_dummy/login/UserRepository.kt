package com.magang_sti.e_tix_dummy.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magang_sti.e_tix_dummy.data.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: APIService) {
    fun getAllUsers() : LiveData<List<UsersItem>?>{
        val users = MutableLiveData<List<UsersItem>?>()

        apiService.getALlUsers().enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        users.postValue(data)
                    }
                }else{
                    users.postValue(null)
                    Log.e("TAG", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                users.postValue(null)
                Log.d("Error", t.message.toString())
            }

        })
        return users
    }
}