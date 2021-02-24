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
    lateinit var applicationStateLogs: List<ApplicationStateLog>
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
        applicationStateLogs: List<ApplicationStateLog>,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PersonalApplication

        if (applicationReferenceId != other.applicationReferenceId) return false
        if (applicationDate != other.applicationDate) return false
        if (customerId != other.customerId) return false
        if (customerType != other.customerType) return false
        if (state != other.state) return false
        if (offerType != other.offerType) return false
        if (productCode != other.productCode) return false
        if (journeyCode != other.journeyCode) return false
        if (applicationStateLogs != other.applicationStateLogs) return false
        //if (activeSavingsAccounts != other.activeSavingsAccounts) return false

        return true
    }



    override fun toString(): String {
        return "PersonalApplication(applicationReferenceId=$applicationReferenceId, applicationDate=$applicationDate, customerId=$customerId, customerType=$customerType, state=$state, offerType=$offerType, productCode=$productCode, journeyCode=$journeyCode, applicationStateLogs=$applicationStateLogs)"
    }

    override fun hashCode(): Int {
        var result = applicationReferenceId.hashCode()
        result = 31 * result + applicationDate.hashCode()
        result = 31 * result + customerId.hashCode()
        result = 31 * result + customerType.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + offerType.hashCode()
        result = 31 * result + productCode.hashCode()
        result = 31 * result + journeyCode.hashCode()
        result = 31 * result + applicationStateLogs.hashCode()
        return result
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
