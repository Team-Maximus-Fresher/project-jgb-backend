package com.axis.jgbbackend.model

data class IncomeDetails(
    val netIncomeAmount: Int,
    val stepStatus: String,
    val uniqueIdentifier: String,
    val unit: String
)