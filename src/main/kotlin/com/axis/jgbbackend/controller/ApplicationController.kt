package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/applications")
class ApplicationController {

    @Autowired
    private var applicationRepo: ApplicationRepo? = null

    @ApiOperation(value = "Get all applications")
    @GetMapping
    fun getAllPersonalLoanApplications(): Flux<PersonalApplication?>? {
        return applicationRepo?.findAll()
    }

    @ApiOperation(value = "Get applications of a particular customer")
    @GetMapping("/{customer_id}")
    fun getPersonalApplicationOfACustomer(@PathVariable customer_id: String): Mono<ResponseEntity<PersonalApplication?>?>? {
        return customer_id?.let {
            applicationRepo?.findByCustomerId(it)
                ?.map { personalApplication -> ResponseEntity.ok(personalApplication) }
                ?.defaultIfEmpty(ResponseEntity.notFound().build())
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonalApplication(@RequestBody personalApplication: PersonalApplication?): Mono<PersonalApplication?>? {
        return applicationRepo?.save(personalApplication as PersonalApplication)
    }

    /*@PutMapping("/{customer_id}")
    fun updateProduct(
        @PathVariable(value = "customer_id") customer_id: String?,
        @RequestBody personalApplication: PersonalApplication
    ): Mono<ResponseEntity<PersonalApplication?>?>? {
        return applicationRepo?.findByCustomerId(customer_id!!)
            ?.flatMap { existingApplication ->
                existingApplication.customerName = personalApplication.customerName
                applicationRepo?.save(existingApplication)
            }
            ?.map { updateApplication -> ResponseEntity.ok(updateApplication) }
            ?.defaultIfEmpty(ResponseEntity.notFound().build())
    }*/
}