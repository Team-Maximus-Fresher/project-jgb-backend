package com.axis.jgbbackend.util

import com.axis.jgbbackend.model.CustomerApplication
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
    fun filterData(application: CustomerApplication): Json {
        val context = Context()
        val dbObject = application
        context.setVariable("props", dbObject)
        if(application.productCode == "PERSONAL")
         return Json(templateEngine.process("pl-personal", context).toString())
        return Json(templateEngine.process("pl-auto", context).toString())
    }
}