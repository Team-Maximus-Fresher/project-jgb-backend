package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.service.ApplicationService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/applications")
class ApplicationController(
    @Autowired
    private val applicationService: ApplicationService
)
{
    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/products/{productCode}/customers/{customerId}")
    fun getApplicationOfACustomer(@PathVariable productCode: String, @PathVariable customerId: String): PersonalApplication? {
        return applicationService.getFilteredApplicationOfACustomer(productCode, customerId)
        /*return applicationService.getApplicationOfACustomer(productCode, customerId)
            .map { application -> ResponseEntity.ok(application) }
            .defaultIfEmpty(ResponseEntity.notFound().build())*/
    }

    @ApiOperation(value = "Get applications of a particular customer by application reference id")
    @GetMapping("/{applicationReferenceId}/products/{productCode}")
    fun getApplicationOfACustomerByApplicationReferenceId(@PathVariable applicationReferenceId: String, @PathVariable productCode: String): Mono<PersonalApplication> {
        return applicationService.getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId, productCode)
    }
}