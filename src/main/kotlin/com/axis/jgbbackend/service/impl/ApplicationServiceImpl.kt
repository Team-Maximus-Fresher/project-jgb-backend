package com.axis.jgbbackend.service.impl

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ApplicationServiceImpl(applicationRepo: ApplicationRepo): ApplicationService {

    private var applicationRepo: ApplicationRepo? = null

    init {
        this.applicationRepo = applicationRepo
    }

    override fun getAllPersonalLoanApplications(): Flux<PersonalApplication?>? {
        return applicationRepo?.findAll()
    }

    override fun getApplicationOfACustomer(productCode: String, customerId: String): Mono<PersonalApplication>? {
        return applicationRepo?.findByProductCodeAndCustomerId(productCode, customerId)
        /*return applicationRepo?.findByProductCodeAndCustomerId(productCode, customerId)
                ?.map { application -> ResponseEntity.ok(application) }
                ?.defaultIfEmpty(ResponseEntity.notFound().build())*/
    }

    override fun getApplicationOfACustomerByApplicationReferenceId(applicationReferenceId: String): Mono<ResponseEntity<PersonalApplication?>?>? {
        return applicationRepo?.findByApplicationReferenceId(applicationReferenceId)
            ?.map { application -> ResponseEntity.ok(application) }
            ?.defaultIfEmpty(ResponseEntity.notFound().build())
    }

    override fun savePersonalApplication(personalApplication: PersonalApplication?): Mono<PersonalApplication?>? {
        return applicationRepo?.save(personalApplication as PersonalApplication)
    }
}