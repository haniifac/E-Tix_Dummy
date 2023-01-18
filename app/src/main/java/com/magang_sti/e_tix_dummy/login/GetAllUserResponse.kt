package com.magang_sti.e_tix_dummy.login

import com.google.gson.annotations.SerializedName

data class GetAllUserResponse(

	@field:SerializedName("GetAllUserResponse")
	val getAllUserResponse: List<UsersItem>
)

data class UsersItem(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("username")
	val username: String
)
