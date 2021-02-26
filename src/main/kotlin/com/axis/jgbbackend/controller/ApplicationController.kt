package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/applications")
class ApplicationController(
    @Autowired
    private val applicationService: ApplicationService,
        @Autowired
private var applicationRepo: ApplicationRepo

) {
    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/products/{productCode}/customers/{customerId}")
    fun getApplicationOfACustomer(@PathVariable productCode: String, @PathVariable customerId: String): PersonalApplication? {
        return applicationService.getApplicationOfACustomer(productCode, customerId)
         }

    @ApiOperation(value = "Get applications of a particular customer by application reference id")
    @GetMapping("/{applicationReferenceId}/products/{productCode}")
    fun getApplicationOfACustomerByApplicationReferenceId(@PathVariable applicationReferenceId: String, @PathVariable productCode: String): Mono<PersonalApplication> {
        return applicationService.getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId, productCode)
    }

}