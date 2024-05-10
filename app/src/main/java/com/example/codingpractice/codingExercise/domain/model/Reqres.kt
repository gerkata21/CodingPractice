package com.example.codingpractice.codingExercise.domain.model

data class Reqres(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)