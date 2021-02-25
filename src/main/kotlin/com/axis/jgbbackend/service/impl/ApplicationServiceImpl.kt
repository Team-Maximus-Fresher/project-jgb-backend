package com.axis.jgbbackend.service.impl

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ApplicationServiceImpl(
    @Autowired
    private val applicationRepo: ApplicationRepo
) : ApplicationService {

    var filteredPlCompleteLogs: MutableList<String> = mutableListOf("PersonalOfferFetcher", "PersonalOfferLanding",
        "PersonalPaSangamCheck", "PersonalLoanOfferRequest", "Sanction", "OTPValidation", "KnockOff", "FinnoneAppIdTask", "DisbursementTask")

    override fun getFilteredApplicationOfACustomer(productCode: String, customerId: String): PersonalApplication? {
        var personalApplication: Mono<PersonalApplication> = getApplicationOfACustomer(productCode, customerId)
        var application = personalApplication.block()
        var stepsToBeDisplayed: MutableList<MutableMap<String, Any>> = mutableListOf()
        for(filteredLog in filteredPlCompleteLogs) {
            if(application != null) {
                for(log in application.applicationStateLogs) {
                    if(log.containsValue(filteredLog)) {
                        stepsToBeDisplayed.add(log)
                        break
                    }
                }
            }
        }
        application?.applicationStateLogs?.clear()
        application?.applicationStateLogs?.addAll(stepsToBeDisplayed)
        return application
    }


    override fun getApplicationOfACustomer(productCode: String, customerId: String): Mono<PersonalApplication> {
        return applicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
    }

    override fun getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication> {
        return applicationRepo.findByApplicationIdAndProductCode(applicationReferenceId, productCode)
    }
}