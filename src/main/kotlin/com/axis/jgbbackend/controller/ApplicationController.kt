package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.service.ApplicationService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/applications")
class ApplicationController(applicationService: ApplicationService) {

    private var applicationService: ApplicationService? = null

    init {
        this.applicationService = applicationService
    }

    @ApiOperation(value = "Get all applications")
    @GetMapping
    fun getAllPersonalLoanApplications(): Flux<PersonalApplication?>? {
        return applicationService?.getAllPersonalLoanApplications()
    }

    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/products/{productCode}/customers/{customerId}")
    fun getApplicationOfACustomer(@PathVariable productCode: String, @PathVariable customerId: String): Mono<ResponseEntity<PersonalApplication?>?>? {
        //return applicationService?.getApplicationOfACustomer(productCode, customerId)
        return applicationService?.getApplicationOfACustomer(productCode, customerId)
            ?.map { application -> ResponseEntity.ok(application) }
            ?.defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @ApiOperation(value = "Get applications of a particular customer by application reference id")
    @GetMapping("/{applicationReferenceId}")
    fun getApplicationOfACustomerByApplicationReferenceId(@PathVariable applicationReferenceId: String): Mono<ResponseEntity<PersonalApplication?>?>? {
        return applicationService?.getApplicationOfACustomerByApplicationReferenceId(applicationReferenceId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonalApplication(@RequestBody personalApplication: PersonalApplication?): Mono<PersonalApplication?>? {
        return applicationService?.savePersonalApplication(personalApplication)
    }

    /*@PutMapping("/{customer_id}")
    fun updateProduct(
        @PathVariable(value = "customer_id") customer_id: String?,
        @RequestBody personalApplication: PersonalApplication
    ): Mono<ResponseEntity<PersonalApplication?>?>? {
        return applicationService?.findByCustomerId(customer_id!!)
            ?.flatMap { existingApplication ->
                existingApplication.customerName = personalApplication.customerName
                applicationService?.save(existingApplication)
            }
            ?.map { updateApplication -> ResponseEntity.ok(updateApplication) }
            ?.defaultIfEmpty(ResponseEntity.notFound().build())
    }*/
}