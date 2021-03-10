package com.axis.jgbbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class HunterResult (
    val uniqueIdentifier: String? = null,
    val stepStatus: String? = null,
    val status: String? = null,
    val check: String? = null
)