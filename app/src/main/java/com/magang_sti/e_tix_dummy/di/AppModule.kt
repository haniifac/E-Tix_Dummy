package com.magang_sti.e_tix_dummy.di

import com.magang_sti.e_tix_dummy.data.APIService
import com.magang_sti.e_tix_dummy.ui.login.UserRepository
import com.magang_sti.e_tix_dummy.ui.login.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun getService(): APIService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://63c7e2bae52516043f469834.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun userRepo() = UserRepository(getService())

//    @Provides
//    fun userVM() : UserViewModel{
//        return UserViewModel(userRepo())
//    }
}