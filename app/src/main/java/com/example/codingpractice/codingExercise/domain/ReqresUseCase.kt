package com.example.codingpractice.codingExercise.domain

import com.example.codingpractice.codingExercise.domain.model.Reqres

interface ReqresUseCase {
    suspend fun execute(): Reqres
}