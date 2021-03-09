package com.axis.jgbbackend.util

import com.axis.jgbbackend.model.PersonalApplication
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.util.ResourceUtils
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine
import springfox.documentation.spring.web.json.Json
import java.io.File
import java.nio.file.Files

class MappingTemplateTest {

    val plCompleteFile: File = ResourceUtils.getFile("src/test/resources/test-files-input/pl-personal.json")
    val plCompleteContent = String(Files.readAllBytes(plCompleteFile.toPath()))
    val mapper: ObjectMapper = ObjectMapper()
    val personalApplication: PersonalApplication = mapper.readValue(plCompleteContent, PersonalApplication::class.java)

    val plCompleteOutputFile: File = ResourceUtils.getFile("src/test/resources/test-files-output/pl-complete-output.json")
    val plCompleteOutputContent = String(Files.readAllBytes(plCompleteOutputFile.toPath()))


    var templateEngine = mockk<SpringWebFluxTemplateEngine>{
        every { process(any<String>(), any()) } returns plCompleteOutputContent
    }

    val mappingTemplate = MappingTemplate(templateEngine)

    @Test
    fun testFilterData() {
        val output = mappingTemplate.filterData(personalApplication)
        Assertions.assertEquals(Json(plCompleteOutputContent).value(), output.value())
    }
}