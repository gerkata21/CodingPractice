package com.example.codingpractice.codingExercise

import com.example.codingpractice.codingExercise.data.network.ReqresAPI
import com.example.codingpractice.codingExercise.domain.ReqresUseCase
import com.example.codingpractice.codingExercise.domain.ReqresUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ReqresModule {
    @Binds
    @Singleton
    abstract fun bindsReqresUseCase(
        reqresUseCaseImpl: ReqresUseCaseImpl
    ): ReqresUseCase


    companion object {
        @Provides
        fun provideReqresApi(
            retrofit: Retrofit
        ): ReqresAPI = retrofit.create(ReqresAPI::class.java)

        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}