package com.axis.jgbbackend.model

data class LoanParameters(
    val loanAmount: LoanAmount,
    val stepStatus: String,
    val tenure: TenureXXXX,
    val uniqueIdentifier: String
)