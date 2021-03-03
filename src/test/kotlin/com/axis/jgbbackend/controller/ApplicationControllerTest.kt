package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.service.ApplicationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest(ApplicationController::class)
class ApplicationControllerTest {

    var personalApplication: PersonalApplication = PersonalApplication("MLP000000000014",null,
        "2021-02-13", null,null,null,null,"840000016",
        "ETB",null,null,null,null,null,null,null,null,null,null,"PERSONAL_ETB_PA",null,
        null,null,null,null,null,null,null,"PA",null,null,null,null,"PERSONAL",null,null,null,null,null,
        "COMPLETE", null, null, null, null
    )

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var service: ApplicationService

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId() {
        val expectedProduct: PersonalApplication = personalApplication
        Mockito.`when`(service.getApplicationOfByProductCodeAndCustomerId(expectedProduct.productCode.toString(),
            expectedProduct.customerId.toString()
        )).thenReturn(ResponseEntity.ok().body(expectedProduct.toString()))
        client.get()
            .uri("/applications/products/{productCode}/customers/{customerId}", expectedProduct.productCode, expectedProduct.customerId)
            .exchange()
            .expectStatus().isOk

        Mockito.verify(service, times(1)).getApplicationOfByProductCodeAndCustomerId(expectedProduct.productCode.toString(), expectedProduct.customerId.toString())
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCode() {
        val expectedProduct: PersonalApplication = personalApplication
        println(expectedProduct.applicationReferenceId)
        Mockito.`when`(service.getApplicationByApplicationReferenceIdAndProductCode(expectedProduct.applicationReferenceId.toString(),
            expectedProduct.productCode.toString()
        )).thenReturn(ResponseEntity.ok().body(expectedProduct.toString())
        )
        client.get()
            .uri("/applications/{applicationReferenceId}/products/{productCode}", expectedProduct.applicationReferenceId, expectedProduct.productCode)
            .exchange()
            .expectStatus().isOk

        Mockito.verify(service, times(1)).getApplicationByApplicationReferenceIdAndProductCode(expectedProduct.applicationReferenceId.toString(), expectedProduct.productCode.toString())
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeNotFound() {
        val expectedProduct: PersonalApplication = personalApplication
        println(expectedProduct.applicationReferenceId)
        Mockito.`when`(service.getApplicationByApplicationReferenceIdAndProductCode("123", "123"))
            .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No application(s) found.")
        )

        client.get()
            .uri("/applications/{applicationReferenceId}/products/{productCode}", "123", "123")
            .exchange()
            .expectStatus().isNotFound

        Mockito.verify(service, times(1)).getApplicationByApplicationReferenceIdAndProductCode("123", "123")
    }
}