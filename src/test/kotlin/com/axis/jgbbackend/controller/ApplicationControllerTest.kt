package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.util.*

//@Import(ApplicationServiceImpl::class)
@WebFluxTest(ApplicationController::class)
class ApplicationControllerTest {
    private lateinit var expectedList: List<PersonalApplication>

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var service: ApplicationService

    @MockBean
    lateinit var repository: ApplicationRepo

    @BeforeEach
    fun beforeEach() {
        val stateLog: MutableList<MutableMap<String, Any>> = mutableListOf()
        expectedList = Arrays.asList<PersonalApplication>(
            PersonalApplication("MLP000000000014",
                "2021-02-13", "840000016",
                "ETB", "COMPLETE", "PA", "PERSONAL","PERSONAL_ETB_PA",stateLog
            )
        )
    }

    /*@Test
    fun testGetApplicationOfACustomer() {
        val expectedProduct: PersonalApplication = expectedList[0]
        println(expectedProduct)
        Mockito.`when`(service.getApplicationOfACustomer(expectedProduct.productCode, expectedProduct.customerId)).thenReturn(
            Mono.just(expectedProduct)
        )
        client.get()
            .uri("/applications/products/{productCode}/customers/{customerId}", expectedProduct.productCode, expectedProduct.customerId)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.applicationReferenceId").isEqualTo("MLP000000000014")
            .jsonPath("$.productCode").isEqualTo("PERSONAL")
    }*/

    @Test
    fun testGetApplicationOfACustomerByApplicationReferenceId() {
        // personalApplication: PersonalApplication = PersonalApplication.builder().id(1).city("delhi").age(23).name("ABC").build()
        val expectedProduct: PersonalApplication = expectedList[0]
        println(expectedProduct.applicationReferenceId)
        Mockito.`when`(service.getApplicationOfACustomerByApplicationReferenceIdAndProductCode(expectedProduct.applicationReferenceId, expectedProduct.productCode)).thenReturn(
            Mono.just(expectedProduct))
        client.get()
            .uri("/applications/{applicationReferenceId}/products/{productCode}", expectedProduct.applicationReferenceId, expectedProduct.productCode)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.applicationReferenceId").isEqualTo("MLP000000000014")
            .jsonPath("$.productCode").isEqualTo("PERSONAL")
    }

    @Test
    fun testGetFilteredApplicationOfACustomer() {
        val expectedProduct: PersonalApplication = expectedList[0]
        println(expectedProduct)
        Mockito.`when`(service.getFilteredApplicationOfACustomer(expectedProduct.productCode, expectedProduct.customerId)).thenReturn(
            expectedProduct)
        client.get()
            .uri("/applications/products/{productCode}/customers/{customerId}", expectedProduct.productCode, expectedProduct.customerId)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.applicationReferenceId").isEqualTo("MLP000000000014")
            .jsonPath("$.productCode").isEqualTo("PERSONAL")
    }

    /*@Test
    fun testGetApplicationOfACustomerByApplicationReferenceId() {
        val expectedProduct: PersonalApplication = expectedList!![0]
        println(expectedProduct.applicationReferenceId)
        Mockito.`when`(service.getApplicationOfACustomerByApplicationReferenceId(expectedProduct.applicationReferenceId!!)).thenReturn(Mono.just(expectedProduct))
        client
            .get()
            .uri("/applications/{applicationReferenceId}", expectedProduct.applicationReferenceId)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(PersonalApplication::class.java)
            .isEqualTo(expectedProduct)
    }*/
}