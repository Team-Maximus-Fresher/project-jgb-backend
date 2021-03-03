package com.axis.jgbbackend

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("Loan Tracking APIs")
            .description("These APIs will be used to track the status of an application for a customer.")
            .version("V1.0").build()
    }

    /*@Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.axis.jgbbackend.controller"))
            .paths(PathSelectors.any())
            .build()
    }*/
}
