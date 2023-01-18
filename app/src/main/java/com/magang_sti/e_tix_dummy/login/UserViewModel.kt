package com.magang_sti.e_tix_dummy.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepo : UserRepository) : ViewModel() {
    fun getAllUsers() : LiveData<List<UsersItem>?> = userRepo.getAllUsers()
}