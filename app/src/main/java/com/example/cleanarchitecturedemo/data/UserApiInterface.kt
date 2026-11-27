package com.example.cleanarchitecturedemo.data

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

internal fun createUserApi(): UserApiInterface = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(UserApiInterface::class.java)

interface UserApiInterface {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}

data class UserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
)
