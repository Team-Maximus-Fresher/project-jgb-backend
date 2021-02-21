package com.axis.jgbbackend.model

data class LoanOffer(
    val breakdown: List<BreakdownXX>,
    val details: DetailsXXX,
    val stepStatus: String,
    val uniqueIdentifier: String
)