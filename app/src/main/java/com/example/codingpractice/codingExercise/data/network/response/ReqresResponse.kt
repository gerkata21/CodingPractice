package com.example.codingpractice.codingExercise.data.network.response

import com.example.codingpractice.codingExercise.domain.model.Reqres

data class ReqresResponse(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

fun ReqresResponse.domain() = Reqres(
    data = data.map {
        com.example.codingpractice.codingExercise.domain.model.Data(
            avatar = it.avatar,
            email = it.email,
            first_name = it.first_name,
            id = it.id,
            last_name = it.last_name
        )
    },
    page = page,
    per_page = per_page,
    support = com.example.codingpractice.codingExercise.domain.model.Support(
        text = support.text,
        url = support.url
    ),
    total = total,
    total_pages = total_pages
)