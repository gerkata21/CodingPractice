package com.example.codingpractice.codingExercise.data.network

import com.example.codingpractice.codingExercise.data.network.response.ReqresResponse
import retrofit2.Response
import retrofit2.http.GET

interface ReqresAPI {
    @GET("api/users")
    suspend fun getReqresApi(): Response<ReqresResponse>
}