package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.ActiveSavingsAccounts
import com.axis.jgbbackend.model.ApplicationStateLog
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import junit.framework.TestCase.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@SpringBootTest
class ApplicationServiceTest {
    @MockBean
    var applicationRepo:ApplicationRepo?=null

    @Test
    fun getAllPersonalLoanApplicationsTest()
    {
       val expectedList=applicationRepo?.findAll()
        Mockito.`when`(
                applicationRepo
                        ?.findAll()

        ).thenReturn(expectedList)
        Mockito.verify(applicationRepo, times(1))?.findAll();
    }

    @Test
    fun getPersonalApplicationOfACustomerTest()
    {
        val expectedProduct=applicationRepo?.findByCustomerId("abc")
        Mockito.`when`(
                applicationRepo
                        ?.findByCustomerId("abc")
        ).thenReturn(expectedProduct)
        Mockito.verify(applicationRepo, times(1))?.findByCustomerId("abc");

    }




}

