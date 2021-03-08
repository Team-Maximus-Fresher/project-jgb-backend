package com.axis.jgbbackend.util

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine
import springfox.documentation.spring.web.json.Json


@Component
class MappingTemplate(
    @Autowired
    private var templateEngine: SpringWebFluxTemplateEngine
) {
    fun filterData(application: PersonalApplication): Json {
        val context = Context()
        val dbObject = application
        context.setVariable("props", dbObject)
        return Json(templateEngine.process("pl-complete", context).toString())
    }
}