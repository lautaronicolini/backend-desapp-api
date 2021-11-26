package ar.edu.unq.desapp.grupoK.backenddesappapi.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
     override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowedOrigins("*")
                .allowedHeaders("*")
        }

    }
