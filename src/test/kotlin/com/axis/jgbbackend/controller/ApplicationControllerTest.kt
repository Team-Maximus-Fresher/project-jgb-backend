package com.axis.jgbbackend.controller

import com.axis.jgbbackend.exception.ApplicationNotFoundException
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.service.ApplicationService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import springfox.documentation.spring.web.json.Json
import java.io.File
import java.nio.file.Files


@WebFluxTest(ApplicationController::class)
class ApplicationControllerTest {

    val plCompleteFile: File = ResourceUtils.getFile("src/main/resources/test-files/pl-complete.json")
    val plCompleteContent = String(Files.readAllBytes(plCompleteFile.toPath()))
    val mapper: ObjectMapper = ObjectMapper()
    val personalApplication: PersonalApplication = mapper.readValue(plCompleteContent, PersonalApplication::class.java)

    val plCompleteOutputFile: File = ResourceUtils.getFile("src/main/resources/test-files/pl-complete-output.json")
    val plCompleteOutputContent = String(Files.readAllBytes(plCompleteOutputFile.toPath()))

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var service: ApplicationService

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId() {
        val expectedProduct: PersonalApplication = personalApplication
        Mockito.`when`(service.getApplicationOfByProductCodeAndCustomerId(expectedProduct.productCode.toString(),
            expectedProduct.customerId.toString()
        )).thenReturn(Mono.just(mutableListOf(Json(plCompleteOutputContent))))
        client.get()
            .uri("/v1/loan-application/products/{productCode}/customers/{customerId}/applications", expectedProduct.productCode, expectedProduct.customerId)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(JSONObject::class.java)
            .hasSize(1)

        Mockito.verify(service, times(1)).getApplicationOfByProductCodeAndCustomerId(expectedProduct.productCode.toString(), expectedProduct.customerId.toString())
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCode() {
        val expectedProduct: PersonalApplication = personalApplication
        val requiredOutput = Json(plCompleteOutputContent)
        Mockito.`when`(service.getApplicationByApplicationReferenceIdAndProductCode(expectedProduct.productCode.toString(), expectedProduct.applicationReferenceId.toString()
        )).thenReturn(Mono.just(requiredOutput))
        client.get()
            .uri("/v1/loan-application/products/{productCode}/applications/{applicationReferenceId}", expectedProduct.productCode.toString(), expectedProduct.applicationReferenceId.toString())
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .json(plCompleteOutputContent)

        //.json(jacksonObjectMapper().writeValueAsString(output))
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeNotFound() {
        every { service.getApplicationByApplicationReferenceIdAndProductCode("PERSONAL", "MLP0001")} returns Mono.empty()

        val applications = service.getApplicationByApplicationReferenceIdAndProductCode(
            productCode = "PERSONAL",
            applicationReferenceId = "MLP0001"
        )

        StepVerifier
            .create(applications)
            .expectError(ApplicationNotFoundException::class.java)
            .verify()
    }
}