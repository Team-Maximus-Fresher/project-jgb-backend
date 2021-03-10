package com.axis.jgbbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoanOffer(
    val uniqueIdentifier: String? = null,
    val stepStatus: String? = null,
    val details: MutableMap<String, Any>? = null,
    val breakdown: List<Breakdown>? = null
)
