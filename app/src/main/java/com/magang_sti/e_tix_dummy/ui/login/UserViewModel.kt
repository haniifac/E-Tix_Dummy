package com.magang_sti.e_tix_dummy.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepo : UserRepository) : ViewModel() {
    fun getAllUsers() : LiveData<List<UsersItem>?> = userRepo.getAllUsers()
}