package com.axis.jgbbackend.model

data class InsuranceDetails(
    val opted: Boolean,
    val stepStatus: String,
    val uniqueIdentifier: String
)