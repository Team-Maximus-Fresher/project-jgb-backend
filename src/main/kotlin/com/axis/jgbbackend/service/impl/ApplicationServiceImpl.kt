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
class ApplicationServiceImpl: ApplicationService {

    @Autowired
    private var applicationRepo: ApplicationRepo? = null

    override fun getAllPersonalLoanApplications(): Flux<PersonalApplication?>? {
        return applicationRepo?.findAll()
    }

    override fun getPersonalApplicationOfACustomer(customer_id: String): Mono<ResponseEntity<PersonalApplication?>?>? {
        return customer_id?.let {
            applicationRepo?.findByCustomerId(it)
                ?.map { personalApplication -> ResponseEntity.ok(personalApplication) }
                ?.defaultIfEmpty(ResponseEntity.notFound().build())
        }
    }

    override fun savePersonalApplication(personalApplication: PersonalApplication?): Mono<PersonalApplication?>? {
        return applicationRepo?.save(personalApplication as PersonalApplication)
    }
}