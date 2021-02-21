package com.axis.jgbbackend.model

data class ActiveSavingsAccounts(
    val accounts: List<Account>?=null,
    val stepStatus: String?=null,
    val uniqueIdentifier: String?=null
)