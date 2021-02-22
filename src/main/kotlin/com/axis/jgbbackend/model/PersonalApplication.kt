package com.axis.jgbbackend.model

import org.springframework.data.annotation.Id

data class PersonalApplication(
    @Id
    val applicationReferenceId: String,
    val applicationDate: String? = null,
    val customerId: String? = null,
    val customerType: String? = null,
    val state: String? = null,
    val offerType: String? = null,
    val productCode: String? = null,
    val journeyCode: String? = null,
    val applicationStateLogs: List<ApplicationStateLog>? = null,
    val activeSavingsAccounts: ActiveSavingsAccounts? = null
)
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
