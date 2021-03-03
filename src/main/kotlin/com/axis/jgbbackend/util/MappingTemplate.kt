package com.axis.jgbbackend.util

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine


@Component
class MappingTemplate(
    @Autowired
    private var templateEngine: SpringWebFluxTemplateEngine
) {
    fun filterData(application: PersonalApplication): ResponseEntity<String> {
        val context = Context()
        val dbObject = application
        context.setVariable("props", dbObject)
        return ResponseEntity.ok().body(templateEngine.process("pl-complete", context))
    }
}