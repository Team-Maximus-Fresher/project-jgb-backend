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


    override fun getApplicationOfACustomer(productCode: String, customerId: String): Mono<PersonalApplication> {
        return applicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
    }

    override fun getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication> {
        return applicationRepo.findByApplicationIdAndProductCode(applicationReferenceId, productCode)
    }
}