package com.axis.jgbbackend.service

import com.axis.jgbbackend.exception.ApplicationNotFoundException
import com.axis.jgbbackend.model.AutoApplication
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.AutoApplicationRepo
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

    val plCompleteFile: File = ResourceUtils.getFile("src/test/resources/test-files-input/pl-complete.json")
    val plCompleteContent = String(Files.readAllBytes(plCompleteFile.toPath()))
    val mapper: ObjectMapper = ObjectMapper()
    val personalApplication: PersonalApplication = mapper.readValue(plCompleteContent, PersonalApplication::class.java)

    val plCompleteOutputFile: File = ResourceUtils.getFile("src/test/resources/test-files-output/pl-complete-output.json")
    val plCompleteOutputContent = String(Files.readAllBytes(plCompleteOutputFile.toPath()))

    val autoCompleteFile: File = ResourceUtils.getFile("src/test/resources/test-files-input/auto-complete.json")
    val autoCompleteContent = String(Files.readAllBytes(autoCompleteFile.toPath()))
    val autoMapper1: ObjectMapper = ObjectMapper()
    val autoApplication: AutoApplication = autoMapper1.readValue(autoCompleteContent, AutoApplication::class.java)

    val autoCompleteOutputFile: File = ResourceUtils.getFile("src/test/resources/test-files-output/pl-complete-output.json")
    val autoCompleteOutputContent = String(Files.readAllBytes(autoCompleteOutputFile.toPath()))

    private val personalApplicationRepo = mockk<PersonalApplicationRepo> {
        every { findByProductCodeAndCustomerId(any(), any())} returns Flux.just(personalApplication)
        every{ findByApplicationIdAndProductCode(any(), any())} returns Mono.just(personalApplication)
        every { findByProductCodeAndMobileNumber(any(), any())} returns Flux.just(personalApplication)
    }

    private val autoApplicationRepo = mockk<AutoApplicationRepo> {
        every { findByProductCodeAndCustomerId(any(), any())} returns Flux.just(autoApplication)
        every{ findByApplicationIdAndProductCode(any(), any())} returns Mono.just(autoApplication)
        every { findByProductCodeAndMobileNumber(any(), any())} returns Flux.just(autoApplication)
    }

    private val mappingTemplate = mockk<MappingTemplate> {
        every { filterData(personalApplication)} returns Json(plCompleteOutputContent)
    }

    private val applicationService = ApplicationServiceImpl(personalApplicationRepo, autoApplicationRepo, mappingTemplate)

    private val autoMappingTemplate1 = mockk<MappingTemplate> {
        every { filterData(autoApplication)} returns Json(autoCompleteOutputContent)
    }
    private val autoApplicationService1 = ApplicationServiceImpl(personalApplicationRepo, autoApplicationRepo, autoMappingTemplate1)

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId() {
        applicationService.getApplicationOfByProductCodeAndCustomerId(
            productCode = "PERSONAL",
            customerId = "840000016"
        ).block()

        verify {
            personalApplicationRepo.findByProductCodeAndCustomerId(
                productCode = personalApplication.productCode.toString(),
                customerId = personalApplication.customerId.toString()
            )
        }
    }

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerIdForAutoApplication() {
        autoApplicationService1.getApplicationOfByProductCodeAndCustomerId(
            productCode = "FOUR_WHEELER_PERSONAL",
            customerId = "840001793"
        ).block()

        verify {
            autoApplicationRepo.findByProductCodeAndCustomerId(
                productCode = autoApplication.productCode.toString(),
                customerId = autoApplication.customerId.toString()
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
            personalApplicationRepo.findByApplicationIdAndProductCode(
                productCode = personalApplication.productCode.toString(),
                applicationReferenceId = personalApplication.applicationReferenceId.toString()
            )
        }

    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeForAutoApplication() {
        autoApplicationService1.getApplicationByApplicationReferenceIdAndProductCode(
            productCode = "FOUR_WHEELER_PERSONAL",
            applicationReferenceId = "MLP000000001116"
        ).block()

        verify {
            autoApplicationRepo.findByApplicationIdAndProductCode(
                productCode = autoApplication.productCode.toString(),
                applicationReferenceId = autoApplication.applicationReferenceId.toString()
            )
        }

    }

    @Test
    fun testGetApplicationOfByProductCodeAndMobileNumber() {
        applicationService.getApplicationOfByProductCodeAndMobileNumber(
            productCode = "PERSONAL",
            mobileNumber = "9910009906"
        ).block()

        verify {
            personalApplicationRepo.findByProductCodeAndMobileNumber(
                productCode = personalApplication.productCode.toString(),
                mobileNumber = personalApplication.mlpCustomerIdentifier?.getValue("mobileNumber").toString()
            )
        }
    }

    @Test
    fun testGetApplicationOfByProductCodeAndMobileNumberForAutoApplication() {
        autoApplicationService1.getApplicationOfByProductCodeAndMobileNumber(
            productCode = "FOUR_WHEELER_PERSONAL",
            mobileNumber = "9876543210"
        ).block()

        verify {
            autoApplicationRepo.findByProductCodeAndMobileNumber(
                productCode = autoApplication.productCode.toString(),
                mobileNumber = autoApplication.mlpCustomerIdentifier?.getValue("mobileNumber").toString()
            )
        }
    }

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerIdNotFound() {
        every { personalApplicationRepo.findByProductCodeAndCustomerId("PERSONAL", "84000001")} returns Flux.empty()

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
        every { personalApplicationRepo.findByApplicationIdAndProductCode("PERSONAL", "MLP0001")} returns Mono.empty()

        val applications = applicationService.getApplicationByApplicationReferenceIdAndProductCode(
            productCode = "PERSONAL",
            applicationReferenceId = "MLP0001"
        )

        StepVerifier
            .create(applications)
            .expectError(ApplicationNotFoundException::class.java)
            .verify()
    }
}