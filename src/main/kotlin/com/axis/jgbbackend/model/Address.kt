package com.axis.jgbbackend.model

data class Address(
    val addressLineFive: String?=null,
    val addressLineFour: String?=null,
    val addressLineOne: String?=null,
    val addressLineThree: String?=null,
    val addressLineTwo: String?=null,
    val city: String?=null,
    val country: String?=null,
    val pinCode: Int?=null,
    val state: String?=null
)