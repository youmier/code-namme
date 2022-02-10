package com.thoughtworks.codename.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CodeNameCard(
    var id: String?,
    val type: Type?,
    val data: Data?,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Data(
    val word: String?,
    val color: String?,
)

data class Card(
    var id: String?,
    var color: String?,
    var word: String?,
    var trans: String?,
)

enum class Type {
    coverCard, wordCard
}