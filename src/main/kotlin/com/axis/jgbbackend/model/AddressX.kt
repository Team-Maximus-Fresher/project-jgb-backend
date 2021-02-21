package com.axis.jgbbackend.model

data class AddressX(
    val addressLineFive: String,
    val addressLineFour: String,
    val addressLineOne: String,
    val addressLineThree: String,
    val addressLineTwo: String,
    val city: String,
    val country: String,
    val pinCode: Int,
    val state: String
)