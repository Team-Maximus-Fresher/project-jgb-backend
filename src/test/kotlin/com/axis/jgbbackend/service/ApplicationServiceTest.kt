package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.util.MappingTemplate
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.doThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import org.thymeleaf.ITemplateEngine
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@AutoConfigureMockMvc
@SpringBootTest
class ApplicationServiceTest(
    @Autowired var applicationService: ApplicationService
)
{

    //@InjectMocks
    //lateinit var springWebFluxTemplateEngine: SpringWebFluxTemplateEngine

    @Autowired
    lateinit var templateEngine: ITemplateEngine

    @MockBean
    lateinit var applicationRepo: ApplicationRepo

    @MockBean
    lateinit var mappingTemplate: MappingTemplate

    val personalApplication =
        PersonalApplication("MLP000000000014",null,
            "2021-02-13", null,null,null,null,"840000016",
            "ETB",null,null,null,null,null,null,null,null,null,null,"PERSONAL_ETB_PA",null,
            null,null,null,null,null,null,null,"PA",null,null,null,null,"PERSONAL",null,null,null,null,null,
            "COMPLETE", null, null, null, null
        )

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

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId()
    {
        var requiredOutput:Flux<String> = Flux.just(output)
        applicationRepo.save(personalApplication)
        val productCode = "PERSONAL"
        val customerId="840000016"
        Mockito.`when`(applicationRepo.findByProductCodeAndCustomerId("PERSONAL", "840000016")).thenReturn(Flux.just())
        var applications = applicationService.getApplicationOfByProductCodeAndCustomerId(productCode, customerId)
        Mockito.verify(applicationRepo, Mockito.times(1)).findByProductCodeAndCustomerId(productCode, customerId)
        //assertEquals(requiredOutput, application)
    }

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId1()
    {
        val application: Flux<PersonalApplication> = Flux.just(personalApplication)
        var requiredOutput:Flux<String> = Flux.just(output).switchIfEmpty(Mono.error(NoSuchElementException("Application not found")))
        applicationRepo.save(personalApplication)
        val productCode = "PERSONAL"
        val customerId="840000016"
        Mockito.`when`(applicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
            .map { mappingTemplate.filterData(personalApplication) }).thenReturn(requiredOutput)
        var applications = applicationService.getApplicationOfByProductCodeAndCustomerId(productCode, customerId)
        var app = applicationRepo.findByProductCodeAndCustomerId(productCode, customerId).map{mappingTemplate.filterData(personalApplication)}
        //println(app.blockFirst())
        Mockito.verify(applicationRepo, Mockito.times(2)).findByProductCodeAndCustomerId(productCode, customerId)
    }

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerIdNotFound()
    {
        var requiredOutput = Flux.just(output)
        applicationRepo.save(personalApplication)
        val productCode = "PERSONA"
        val customerId="840000016"
        doThrow(NoSuchElementException("Application not found")).`when`(applicationRepo).findByProductCodeAndCustomerId("PERSONA", "840000016")
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCode()
    {
        var requiredOutput = ResponseEntity.ok().body(output)
        applicationRepo.save(personalApplication)
        val productCode = "PERSONAL"
        val applicationReferenceId="MLP000000000014"
        //doThrow(NoSuchElementException("Application not found")).`when`(applicationRepo).findByProductCodeAndCustomerId("PERSONA", "MLP000000000014")
        Mockito.`when`(applicationRepo.findByApplicationIdAndProductCode(productCode, applicationReferenceId)).thenReturn(Mono.just(personalApplication))
        var application = applicationService.getApplicationByApplicationReferenceIdAndProductCode("PERSONAL", "MLP000000000014")
        Mockito.verify(applicationRepo, Mockito.times(1)).findByApplicationIdAndProductCode(productCode, applicationReferenceId)
        //assertEquals(requiredOutput, application)
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeNotFound()
    {
        var requiredOutput = ResponseEntity.ok().body(output)
        applicationRepo.save(personalApplication)
        val productCode = "PERSONAL"
        val applicationReferenceId="MLP000000000014"
        doThrow(NoSuchElementException("Application not found")).`when`(applicationRepo).findByProductCodeAndCustomerId("PERSONA", "MLP000000000014")
    }
}

