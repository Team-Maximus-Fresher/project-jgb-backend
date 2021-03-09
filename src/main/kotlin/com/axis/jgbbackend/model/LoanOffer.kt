package com.axis.jgbbackend.model

data class LoanOffer(
    val uniqueIdentifier: String? = null,
    val stepStatus: String? = null,
    val details: MutableMap<String, Any>? = null,
    val breakdown: List<Breakdown>? = null
)
