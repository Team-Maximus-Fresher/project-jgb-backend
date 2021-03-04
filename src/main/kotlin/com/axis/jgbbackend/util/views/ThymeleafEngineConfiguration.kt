package com.axis.jgbbackend.util.views

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver

@Configuration
class ThymeleafEngineConfiguration {
    @Bean
    fun templateEngine(): SpringWebFluxTemplateEngine {
        val templateEngine = SpringWebFluxTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        return templateEngine
    }

    @Bean
    fun templateResolver(): ITemplateResolver {
        val templateResolver = ClassLoaderTemplateResolver()
        templateResolver.prefix = "/templates/"
        templateResolver.suffix = ".json"
        templateResolver.templateMode = TemplateMode.TEXT
        templateResolver.characterEncoding = "UTF8"
        //templateResolver.checkExistence = true
        //templateResolver.isCacheable = true
        //templateResolver.cacheTTLMs = cacheTimeoutTTL?.toLong() ?: 60000

        return templateResolver
    }

}