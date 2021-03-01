package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.Application
import com.axis.jgbbackend.model.PersonalOfferLanding
import com.axis.jgbbackend.model.Product
import com.axis.jgbbackend.model.ReqData
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.repository.PersonalOfferLandingRepo
import com.axis.jgbbackend.service.ApplicationService
import com.axis.jgbbackend.service.impl.ProductService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import javax.xml.crypto.KeySelector

@RestController
@RequestMapping("/applications")
class ApplicationController(
    @Autowired
    private val applicationService: ApplicationService,
        @Autowired
private var applicationRepo: ApplicationRepo,
    @Autowired
    private var offer: PersonalOfferLandingRepo,

) {

    @ApiOperation(value = "Get applications of a particular customer by product code and customer id")
    @GetMapping("/purpose/{_id}")
    fun getApplicationOfACustomer(@PathVariable _id: String): ReqData {
        //return applicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
        return applicationService.getFilteredData(_id)
        /*return applicationService.getApplicationOfACustomer(productCode, customerId)
            .map { application -> ResponseEntity.ok(application) }
            .defaultIfEmpty(ResponseEntity.notFound().build())*/
    }
    @ApiOperation(value = "Get all applications")
    @GetMapping
    fun getAllPersonalLoanApplications(): Flux<Application?>? {
        return applicationRepo?.findAll()
    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun savePersonalApplication(@RequestBody application: Application?): Mono<Application?>? {
//        return applicationRepo?.save(application as Application)
//    }
    @ApiOperation(value = "Get all products")
    @GetMapping("/offerlanding")
    fun getAlloffers(): Flux<PersonalOfferLanding> {
        return offer.findAll()

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonalApplication(@RequestBody personalOfferLanding:PersonalOfferLanding?): Mono<PersonalOfferLanding?>? {
        return offer?.save(personalOfferLanding as PersonalOfferLanding)
    }
    @ApiOperation(value = "offers")
    @GetMapping("/ById/{_id}")
    fun getoffer(@PathVariable _id: String): Mono<PersonalOfferLanding> {

        return offer.findBy_id(_id)
            }

}