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
    private val applicationService: ApplicationService
    /*@Autowired
    private val repo: ApplicationRepo*/
) {
    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/products/{productCode}/customers/{customerId}")
    fun getApplicationOfByProductCodeAndCustomerId(@PathVariable productCode: String, @PathVariable customerId: String): ResponseEntity<String> {
        return applicationService.getApplicationOfByProductCodeAndCustomerId(productCode, customerId)
    }

    @ApiOperation(value = "Get applications of a particular customer by application reference id")
    @GetMapping("/{applicationReferenceId}/products/{productCode}")
    fun getApplicationByApplicationReferenceIdAndProductCode(@PathVariable applicationReferenceId: String, @PathVariable productCode: String): ResponseEntity<String> {
        return applicationService.getApplicationByApplicationReferenceIdAndProductCode(applicationReferenceId, productCode)
    }

    /*@ApiOperation(value = "Get applications of a particular customer by application reference id")
    @PostMapping()
    fun save(@RequestBody app: PersonalApplication): Mono<PersonalApplication> {
        return repo.save(app)
    }*/

}