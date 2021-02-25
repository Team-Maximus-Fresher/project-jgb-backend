package com.axis.jgbbackend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class PersonalApplication {
    @Id
    lateinit var applicationReferenceId: String
    lateinit var applicationDate: String
    lateinit var customerId: String
    lateinit var customerType: String
    lateinit var state: String
    lateinit var offerType: String
    lateinit var productCode: String
    lateinit var journeyCode: String
    lateinit var applicationStateLogs: MutableList<MutableMap<String, Any>>
    //lateinit var activeSavingsAccounts: ActiveSavingsAccounts

    constructor()

    constructor(
        applicationReferenceId: String,
        applicationDate: String,
        customerId: String,
        customerType: String,
        state: String,
        offerType: String,
        productCode: String,
        journeyCode: String,
        applicationStateLogs: MutableList<MutableMap<String, Any>>
        //applicationStateLogs: List<ApplicationStateLog>,
        //activeSavingsAccounts: ActiveSavingsAccounts
    ) {
        this.applicationReferenceId = applicationReferenceId
        this.applicationDate = applicationDate
        this.customerId = customerId
        this.customerType = customerType
        this.state = state
        this.offerType = offerType
        this.productCode = productCode
        this.journeyCode = journeyCode
        this.applicationStateLogs = applicationStateLogs
        //this.activeSavingsAccounts = activeSavingsAccounts
    }

    override fun toString(): String {
        return "PersonalApplication(applicationReferenceId=$applicationReferenceId, applicationDate=$applicationDate, customerId=$customerId, customerType=$customerType, state=$state, offerType=$offerType, productCode=$productCode, journeyCode=$journeyCode, applicationStateLogs=$applicationStateLogs)"
    }


}

/*
data class PersonalApplication(
    val activeSavingsAccounts: ActiveSavingsAccounts,
    val applicationDate: String,
    val applicationReferenceId: String,
    val applicationStateLogs: List<ApplicationStateLog>,
    val channelAttribution: ChannelAttribution,
    val cibilCheckConsent: CibilCheckConsent,
    val customerEditable: CustomerEditable,
    val customerId: String,
    val customerType: String,
    val demogDetails: DemogDetails,
    val disbursementDetails: DisbursementDetails,
    val feedback: Feedback,
    val finnoneAppId: FinnoneAppId,
    val fraudDetails: FraudDetails,
    val gtmParams: GtmParams,
    val hunterResult: HunterResult,
    val incomeDetails: IncomeDetails,
    val insuranceDetails: InsuranceDetails,
    val insurancePremium: InsurancePremium,
    val journeyCode: String,
    val knockOff: KnockOff,
    val kycResponse: KycResponse,
    val loanOffer: LoanOffer,
    val loanParameters: LoanParameters,
    val mailingAddressPincodeMapping: MailingAddressPincodeMapping,
    val maxServiceableEMI: MaxServiceableEMI,
    val metaData: MetaData,
    val mlpCustomerIdentifier: MlpCustomerIdentifier,
    val offerType: String,
    val otpValidation: OtpValidation,
    val personalWhatsAppOptIn: PersonalWhatsAppOptIn,
    val preApprovedOffer: PreApprovedOffer,
    val preferredDisbursementAccount: PreferredDisbursementAccount,
    val productCode: String,
    val repaymentData: RepaymentData,
    val sangamResult: SangamResult,
    val selectedLoanDetails: SelectedLoanDetails,
    val selectedMailingAddress: SelectedMailingAddress,
    val source: Source,
    val state: String,
    val submissionTime: String,
    val termAndConditionPdf: TermAndConditionPdf,
    val updatedEmailAddress: UpdatedEmailAddress,
    val utmParams: UtmParams
)*/
