package com.axis.jgbbackend.model

data class FraudDetails(
    val fraudIndicators: List<Any>,
    val fraudPresent: Boolean,
    val stepStatus: String,
    val uniqueIdentifier: String
)