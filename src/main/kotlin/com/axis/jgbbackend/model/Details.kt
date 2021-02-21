package com.axis.jgbbackend.model

data class Details(
    val emi: Int?=null,
    val interestRate: Double?=null,
    val loanAmount: Int?=null,
    val tenure: Int?=null,
    val tenureLabel: String?=null
)