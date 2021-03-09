package com.axis.jgbbackend.service

import com.axis.jgbbackend.exception.ApplicationNotFoundException
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.PersonalApplicationRepo
import com.axis.jgbbackend.service.impl.ApplicationServiceImpl
import com.axis.jgbbackend.util.MappingTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import springfox.documentation.spring.web.json.Json
import java.io.File
import java.nio.file.Files

class ApplicationServiceImplTest {

    val plCompleteFile: File = ResourceUtils.getFile("src/test/resources/test-files-input/pl-personal.json")
    val plCompleteContent = String(Files.readAllBytes(plCompleteFile.toPath()))
    val mapper: ObjectMapper = ObjectMapper()
    val personalApplication: PersonalApplication = mapper.readValue(plCompleteContent, PersonalApplication::class.java)

    val plCompleteOutputFile: File = ResourceUtils.getFile("src/test/resources/test-files-output/pl-complete-output.json")
    val plCompleteOutputContent = String(Files.readAllBytes(plCompleteOutputFile.toPath()))

    val plCompleteMobileLoginFile: File = ResourceUtils.getFile("src/test/resources/test-files-input/pl-complete-mobile.json")
    val plCompleteMobileLoginContent = String(Files.readAllBytes(plCompleteMobileLoginFile.toPath()))
    val personalApplication1: PersonalApplication = mapper.readValue(plCompleteMobileLoginContent, PersonalApplication::class.java)

    val plCompleteMobileLoginOutputFile: File = ResourceUtils.getFile("src/test/resources/test-files-output/pl-complete-mobile-output.json")
    val plCompleteMobileLoginOutputContent = String(Files.readAllBytes(plCompleteMobileLoginOutputFile.toPath()))

    private val applicationRepo = mockk<PersonalApplicationRepo> {
        every { findByProductCodeAndCustomerId(any(), any())} returns Flux.just(personalApplication)
        every{ findByApplicationIdAndProductCode(any(), any())} returns Mono.just(personalApplication)
        every { findByProductCodeAndMobileNumber(any(), any())} returns Flux.just(personalApplication1)
    }

    private val mappingTemplate = mockk<MappingTemplate> {
        every { filterData(personalApplication)} returns Json(plCompleteOutputContent)
    }

    private val applicationService = ApplicationServiceImpl(applicationRepo, mappingTemplate)

    private val mappingTemplate1 = mockk<MappingTemplate> {
        every { filterData(personalApplication1)} returns Json(plCompleteMobileLoginOutputContent)
    }
    private val applicationService1 = ApplicationServiceImpl(applicationRepo, mappingTemplate1)

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId() {
        applicationService.getApplicationOfByProductCodeAndCustomerId(
            productCode = "PERSONAL",
            customerId = "840000016"
        ).block()

        verify {
            applicationRepo.findByProductCodeAndCustomerId(
                productCode = personalApplication.productCode.toString(),
                customerId = personalApplication.customerId.toString()
            )
        }

    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCode() {
        applicationService.getApplicationByApplicationReferenceIdAndProductCode(
            productCode = "PERSONAL",
            applicationReferenceId = "MLP000000000014"
        ).block()

        verify {
            applicationRepo.findByApplicationIdAndProductCode(
                productCode = personalApplication.productCode.toString(),
                applicationReferenceId = personalApplication.applicationReferenceId.toString()
            )
        }

    }

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerIdNotFound() {
        every { applicationRepo.findByProductCodeAndCustomerId("PERSONAL", "84000001")} returns Flux.empty()

        val applications = applicationService.getApplicationOfByProductCodeAndCustomerId(
            productCode = "PERSONAL",
            customerId = "84000001"
        )

        StepVerifier
            .create(applications)
            .expectError(ApplicationNotFoundException::class.java)
            .verify()
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeNotFound() {
        every { applicationRepo.findByApplicationIdAndProductCode("PERSONAL", "MLP0001")} returns Mono.empty()

        val applications = applicationService.getApplicationByApplicationReferenceIdAndProductCode(
            productCode = "PERSONAL",
            applicationReferenceId = "MLP0001"
        )

        StepVerifier
            .create(applications)
            .expectError(ApplicationNotFoundException::class.java)
            .verify()
    }

    @Test
    fun testGetApplicationOfByProductCodeAndMobileNumber() {
        applicationService1.getApplicationOfByProductCodeAndMobileNumber(
            productCode = "PERSONAL",
            mobileNumber = "9910009906"
        ).block()

        verify {
            applicationRepo.findByProductCodeAndMobileNumber(
                productCode = personalApplication1.productCode.toString(),
                mobileNumber = personalApplication1.mlpCustomerIdentifier?.getValue("mobileNumber").toString()
            )
        }
    }
}