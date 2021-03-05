package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.service.ApplicationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.ResourceLoader
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@WebFluxTest(ApplicationController::class)
class ApplicationControllerTest(
    @Autowired
    var resourceLoader: ResourceLoader
) {

    var personalApplication: PersonalApplication = PersonalApplication("MLP000000000014",null,
        "2021-02-13", null,null,null,null,"840000016",
        "ETB",null,null,null,null,null,null,null,null,null,null,"PERSONAL_ETB_PA",null,
        null,null,null,null,null,null,null,"PA",null,null,null,null,"PERSONAL",null,null,null,null,null,
        "COMPLETE", null, null, null, null)

    val output = "{\n" +
            "  \"applicationReferenceId\": \"MLP000000000014\",\n" +
            "  \"customerId\": \"840000016\",\n" +
            "  \"productCode\": \"PERSONAL\",\n" +
            "  \"journeyCode\": \"PERSONAL_ETB_PA\",\n" +
            "  \"applicationStateLogs\": [\n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "  ],\n" +
            "  \"state\": \"COMPLETE\"\n" +
            "}"

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var service: ApplicationService

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId() {
        val expectedProduct: PersonalApplication = personalApplication
        Mockito.`when`(service.getApplicationOfByProductCodeAndCustomerId(expectedProduct.productCode.toString(),
            expectedProduct.customerId.toString()
        )).thenReturn(Mono.just(listOf(output)))
        client.get()
            .uri("/v1/loan-application/products/{productCode}/customers/{customerId}/applications", expectedProduct.productCode, expectedProduct.customerId)
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(String::class.java)
            .isEqualTo<WebTestClient.ListBodySpec<String>>(listOf(output))


        //Mockito.verify(service, times(1)).getApplicationOfByProductCodeAndCustomerId(expectedProduct.productCode.toString(), expectedProduct.customerId.toString())
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCode() {
        val expectedProduct: PersonalApplication = personalApplication
        println(expectedProduct.applicationReferenceId)
        Mockito.`when`(service.getApplicationByApplicationReferenceIdAndProductCode(expectedProduct.productCode.toString(), expectedProduct.applicationReferenceId.toString()
        )).thenReturn(Mono.just(output))
        client.get()
            .uri("/v1/loan-application/products/{productCode}/applications/{applicationReferenceId}", expectedProduct.productCode.toString(), expectedProduct.applicationReferenceId.toString())
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String::class.java)
            .isEqualTo<Nothing>(output)

        //Mockito.verify(service, times(1)).getApplicationByApplicationReferenceIdAndProductCode(expectedProduct.productCode.toString(), expectedProduct.applicationReferenceId.toString())
    }

    /*@Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeNotFound() {
        val expectedProduct: PersonalApplication = personalApplication
        println(expectedProduct.applicationReferenceId)
        Mockito.`when`(service.getApplicationByApplicationReferenceIdAndProductCode("123", "123"))
            .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No application(s) found.")
        )

        client.get()
            .uri("products/{productCode}/applications/{applicationReferenceId}", "123", "123")
            .exchange()
            .expectStatus().isNotFound

        Mockito.verify(service, times(1)).getApplicationByApplicationReferenceIdAndProductCode("123", "123")
    }*/
}