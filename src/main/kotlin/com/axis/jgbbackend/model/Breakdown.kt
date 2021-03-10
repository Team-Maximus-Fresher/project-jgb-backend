package com.axis.jgbbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Breakdown(
    val name: String? = null,
    val displayName: String? = null,
    val value: String? = null
)