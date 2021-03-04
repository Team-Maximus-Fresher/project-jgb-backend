package com.axis.jgbbackend.model

import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("personalApplication")
data class PersonalApplication(
    val applicationReferenceId: String? = null,
    val activeSavingsAccounts: MutableMap<*,*>? = null,
    val applicationDate: String? = null,
    val applicationStateLogs: List<MutableMap<*,*>>? = null,
    val channelAttribution: MutableMap<*,*>? = null,
    val cibilCheckConsent: MutableMap<*,*>? = null,
    val customerEditable: MutableMap<*,*>? = null,
    val customerId: String? = null,
    val customerType: String? = null,
    val demogDetails: MutableMap<*,*>? = null,
    val disbursementDetails: MutableMap<*,*>? = null,
    val feedback: MutableMap<*,*>? = null,
    val finnoneAppId: MutableMap<*,*>? = null,
    val fraudDetails: MutableMap<*,*>? = null,
    val gtmParams: MutableMap<*,*>? = null,
    val hunterResult: MutableMap<*,*>? = null,
    val incomeDetails: MutableMap<*,*>? = null,
    val insuranceDetails: MutableMap<*,*>? = null,
    val insurancePremium: MutableMap<*,*>? = null,
    val journeyCode: String? = null,
    val knockOff: MutableMap<*,*>? = null,
    val kycResponse: MutableMap<*,*>? = null,
    val loanOffer: MutableMap<*,*>? = null,
    val loanParameters: MutableMap<*,*>? = null,
    val mailingAddressPincodeMapping: MutableMap<*,*>? = null,
    val maxServiceableEMI: MutableMap<*,*>? = null,
    val metaData: MutableMap<*,*>? = null,
    val mlpCustomerIdentifier: MutableMap<*,*>? = null,
    val offerType: String? = null,
    val otpValidation: MutableMap<*,*>? = null,
    val personalWhatsAppOptIn: MutableMap<*,*>? = null,
    val preApprovedOffer: MutableMap<*,*>? = null,
    val preferredDisbursementAccount: MutableMap<*,*>? = null,
    val productCode: String? = null,
    val repaymentData: MutableMap<*,*>? = null,
    val sangamResult: MutableMap<*,*>? = null,
    val selectedLoanDetails: MutableMap<*,*>? = null,
    val selectedMailingAddress: MutableMap<*,*>? = null,
    val source: MutableMap<*,*>? = null,
    val state: String? = null,
    val submissionTime: String? = null,
    val termAndConditionPdf: MutableMap<*,*>? = null,
    val updatedEmailAddress: MutableMap<*,*>? = null,
    val utmParams: MutableMap<*,*>? = null
)
