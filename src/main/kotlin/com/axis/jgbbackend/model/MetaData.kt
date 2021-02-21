package com.axis.jgbbackend.model

data class MetaData(
    val demogData: DemogData,
    val loginMethod: String,
    val remoteAddress: String
)