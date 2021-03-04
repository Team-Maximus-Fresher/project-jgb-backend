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
        var application = templateEngine.process("pl-complete", context)
        application.replace("[\n\r]+", "\n")
        application.replace("\n[ \t]*\n", "\n")
        return ResponseEntity.ok().body(application)
    }
}