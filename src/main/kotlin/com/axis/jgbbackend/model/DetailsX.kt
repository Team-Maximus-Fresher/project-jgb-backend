package com.axis.jgbbackend.model

data class DetailsX(
    val accountNumber: String?=null,
    val disbursedAmount: String?=null,
    val disbursementDate: String?=null,
    val emi: Int?=null,
    val interestRate: Double?=null,
    val loanAmount: Int?=null,
    val plrRate: Double?=null,
    val tenure: Int?=null,
    val tenureLabel: String?=null,
    val transactionId: String?=null
)