package com.axis.jgbbackend.model

data class TermAndConditionPdf(
    val createdOn: String,
    val documentId: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)