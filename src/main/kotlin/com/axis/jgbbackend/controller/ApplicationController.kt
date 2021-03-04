package com.axis.jgbbackend.controller

import com.axis.jgbbackend.service.ApplicationService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("http://localhost:3000")
class ApplicationController(
    @Autowired
    private val applicationService: ApplicationService
) {
    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/products/{productCode}/customers/{customerId}/applications")
    fun getApplicationOfByProductCodeAndCustomerId(@PathVariable productCode: String, @PathVariable customerId: String): ResponseEntity<String> {
        return applicationService.getApplicationOfByProductCodeAndCustomerId(productCode, customerId)
    }

    @ApiOperation(value = "Get applications of a particular customer by application reference id")
    @GetMapping("products/{productCode}/applications/{applicationReferenceId}")
    fun getApplicationByApplicationReferenceIdAndProductCode(@PathVariable applicationReferenceId: String, @PathVariable productCode: String): ResponseEntity<String> {
        return applicationService.getApplicationByApplicationReferenceIdAndProductCode(applicationReferenceId, productCode)
    }
}