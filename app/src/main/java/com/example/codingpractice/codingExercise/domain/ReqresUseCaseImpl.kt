package com.example.codingpractice.codingExercise.domain

import com.example.codingpractice.codingExercise.data.network.ReqresAPI
import com.example.codingpractice.codingExercise.data.network.response.domain
import com.example.codingpractice.codingExercise.domain.model.Reqres
import javax.inject.Inject

class ReqresUseCaseImpl @Inject constructor(
    private val reqresAPI: ReqresAPI
) : ReqresUseCase {
    override suspend fun execute(): Reqres {
        val response = reqresAPI.getReqresApi()

        return if (response.isSuccessful) {
            response.body()?.domain() ?: run {
                throw Exception("Api Empty Response")
            }
        } else {
            throw Exception("Api Failure")
        }
    }
}