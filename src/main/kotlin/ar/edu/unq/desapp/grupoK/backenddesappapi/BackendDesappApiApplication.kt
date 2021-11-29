package ar.edu.unq.desapp.grupoK.backenddesappapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
class BackendDesappApiApplication{}

fun main(args: Array<String>) {
	runApplication<BackendDesappApiApplication>(*args)
}
