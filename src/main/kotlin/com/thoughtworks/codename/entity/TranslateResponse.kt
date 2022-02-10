package com.thoughtworks.codename.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class TranslateResponse(
    val sentences: List<Sentence>? = null,
)