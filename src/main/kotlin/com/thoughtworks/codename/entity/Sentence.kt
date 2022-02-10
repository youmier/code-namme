package com.thoughtworks.codename.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class Sentence(
    val trans: String = "",
)