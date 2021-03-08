package com.axis.jgbbackend.controller

import com.axis.jgbbackend.service.ApplicationService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import springfox.documentation.spring.web.json.Json

@RestController
@RequestMapping("/v1/loan-application")
@CrossOrigin("http://localhost:3000")
class ApplicationController(
    @Autowired
    private val applicationService: ApplicationService
) {
    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/products/{productCode}/customers/{customerId}/applications")
    fun getApplicationOfByProductCodeAndCustomerId(@PathVariable productCode: String, @PathVariable customerId: String): Mono<MutableList<Json>> {
        return applicationService.getApplicationOfByProductCodeAndCustomerId(productCode, customerId)
    }

    @ApiOperation(value = "Get applications of a particular customer by product code and mobile number")
    @GetMapping("/products/{productCode}/customers/mobileNumber/{mobileNumber}/applications")
    fun getApplicationOfByProductCodeAndMobileNumber(@PathVariable productCode: String, @PathVariable mobileNumber: String): Mono<MutableList<Json>> {
        return applicationService.getApplicationOfByProductCodeAndMobileNumber(productCode, mobileNumber)
    }

    @ApiOperation(value = "Get applications of a particular customer by application reference id and product code")
    @GetMapping("products/{productCode}/applications/{applicationReferenceId}")
    fun getApplicationByApplicationReferenceIdAndProductCode(@PathVariable productCode: String, @PathVariable applicationReferenceId: String): Mono<Json> {
        return applicationService.getApplicationByApplicationReferenceIdAndProductCode(productCode, applicationReferenceId)
    }
}